package PvZ;

import javafx.animation.AnimationTimer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ZombieMover implements Serializable, Updatable {
    GameState gameState;
    double zombieSpeed = 0.5;
    public ZombieMover(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void update() throws GameEventException{
        ArrayList<Zombie>[] zombies = gameState.lawnGrid.zombieLanes;
        Plant[][] plants = gameState.lawnGrid.plantsGrid;
        for(int laneNo = 0; laneNo < 5; laneNo++){
            int finalLaneNo = laneNo;
            AtomicBoolean lost = new AtomicBoolean(false);
            ArrayList[] zombiesToremove = new ArrayList[5];
            for(int i=0; i<5; i++){
                zombiesToremove[i] = new ArrayList<Zombie>();
            }
            for(Zombie zombie: zombies[laneNo]){
                double zomX = zombie.x;
                double zomY = zombie.y;
                if (gameState.lawnGrid.withinGrid(zomX, zomY)) {
                    int gridY = gameState.lawnGrid.getYindex(zomY);
                    int gridX = gameState.lawnGrid.getXindex(zomX, gridY);
                    if (plants[gridY][gridX] != null && (plants[gridY][gridX].isDead() == false)) {
                        System.out.println("GridY: " + gridY + ", GridX: " + gridX);
                        zombie.attack(plants[gridY][gridX]);
                    } else {
                        zombie.setX(zombie.x - zombieSpeed);
                    }
                } else if (zomX < gameState.lawnGrid.gridCoordinates[0][0][0] - 40) {
                    if (gameState.lawnGrid.lawnmowersUsed[finalLaneNo]) {
                        throw new GameLostException();
                    } else {
                        zombies[finalLaneNo].forEach(z -> {
                            z.die();
                            zombiesToremove[finalLaneNo].add(z);
                        });
                        //zombies[finalLaneNo].clear();
                        gameState.lawnGrid.useLawnmower(finalLaneNo);
                    }
                } else {
                    zombie.setX(zombie.x - zombieSpeed);
                }
            }
            for(int i=0; i<5; i++){
                for(Object z: zombiesToremove[i]){
                    zombies[i].remove(z);
                }
            }
        }
    }
}
