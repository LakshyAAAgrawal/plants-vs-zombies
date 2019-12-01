package PvZ;

import java.io.Serializable;

public class CherryBombExecuter implements Updatable, Serializable {
    GameState gameState;
    public CherryBombExecuter(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void update() throws GameEventException {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 9; j++){
                if(gameState.lawnGrid.plantsGrid[i][j] != null &&
                        !gameState.lawnGrid.plantsGrid[i][j].isDead() &&
                        gameState.lawnGrid.plantsGrid[i][j] instanceof Cherrybomb){
                    Cherrybomb cb = (Cherrybomb) gameState.lawnGrid.plantsGrid[i][j];
                    System.out.println("Checking cb");
                    for(Object zombie_: gameState.lawnGrid.zombieLanes[i]){
                        Zombie zombie = (Zombie) zombie_;
                        if(zombie.isDead()){
                            continue;
                        }
                        if((zombie.x - cb.x) > -90 && (zombie.x - cb.x) < 90){
                            System.out.println("CB dead");
                            cb.die();
                            gameState.lawnGrid.plantsGrid[i][j] = null;
                            zombie.reduce_HP(cb.attackStat);
                            break;
                        }
                    }
                }
            }
        }
    }
}
