package PvZ;

import java.io.Serializable;
import java.util.Random;

public class ZombieSpawner implements Serializable, Updatable {
    GameState gamestate;
    int durationBetweenZombies = 7;
    int counter;
    ZombieFactory zombieFactory;
    Random rand;
    public ZombieSpawner(GameState gamestate){
        this.gamestate = gamestate;
        this.counter = 0;
        zombieFactory = new ZombieFactory();
        rand = new Random();
    }
    public void update(){
        if(counter == 0){
            int laneNum = rand.nextInt(5);
            int zomNum;
            if(gamestate.level == 1 || gamestate.level == 2){
                zomNum = 1;
            }else{
                zomNum = rand.nextInt(gamestate.level - 1) + 1;
            }
            Zombie zombie = zombieFactory.createZombie(zomNum, laneNum, gamestate.lawnGrid.getZombieInitX(laneNum), gamestate.lawnGrid.getZombieInitY(laneNum));
            gamestate.lawnGrid.addZombie(zombie, laneNum);
            gamestate.getMainPane().getChildren().add(zombie.image);
        }
        counter = (counter + 1)%(durationBetweenZombies * 60);
    }
}
