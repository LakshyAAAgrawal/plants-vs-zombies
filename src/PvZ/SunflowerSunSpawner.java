package PvZ;

import java.io.Serializable;

public class SunflowerSunSpawner implements Serializable,Updatable {
    GameState gameState;
    public SunflowerSunSpawner(GameState gameState){
        this.gameState=gameState;
    }
    public void update(){
        for (int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if (gameState.lawnGrid.plantsGrid[i][j]!=null && gameState.lawnGrid.plantsGrid[i][j].isDead()==false && gameState.lawnGrid.plantsGrid[i][j] instanceof Sunflower) {
                    ((Sunflower)gameState.lawnGrid.plantsGrid[i][j]).update(gameState.getMainPane());
                }
            }
        }

    }
}
