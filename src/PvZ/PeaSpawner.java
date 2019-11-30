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

                }
            }
        }
    }
}
