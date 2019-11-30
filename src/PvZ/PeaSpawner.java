package PvZ;

import java.io.Serializable;

public class PeaSpawner implements Serializable, Updatable {
    GameState gameState;
    public PeaSpawner(GameState gameState){
        this.gameState = gameState;
    }
    @Override
    public void update() throws GameEventException {
        for (int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if (gameState.lawnGrid.plantsGrid[i][j] != null && gameState.lawnGrid.plantsGrid[i][j].isDead() == false && gameState.lawnGrid.plantsGrid[i][j] instanceof Shooter) {
                    Shooter shooter = (Shooter) gameState.lawnGrid.plantsGrid[i][j];
                    boolean enemypresent = false;
                    for(Object a: gameState.lawnGrid.zombieLanes[i]){
                        Zombie z = (Zombie) a;
                        if(gameState.lawnGrid.withinGrid(z.x, z.y) && z.x > shooter.x){
                            enemypresent = true;
                            break;
                        }
                    }
                    if(enemypresent){
                        if(shooter.update()){
                            gameState.addPea(shooter.x + 40, shooter.y + 5, i);
                        }
                    }
                }
            }
        }
    }
}
