package PvZ;

import java.io.Serializable;
import java.util.ArrayList;

public class PeaMover implements Serializable, Updatable {
    GameState gameState;
    public PeaMover(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void update() throws GameEventException {
        ArrayList[] peaLanes = gameState.lawnGrid.peaLanes;
        ArrayList[] peasToremove = new ArrayList[5];
        for(int i=0; i<5; i++){
            peasToremove[i] = new ArrayList<Pea>();
        }
        for(int i = 0; i < 5; i++){
            for(Object pea_: peaLanes[i]){
                Pea pea = (Pea) pea_;
                if(pea.isActive()){
                    boolean caughtInZombie = false;
                    for(Object zombie_: gameState.lawnGrid.zombieLanes[i]){
                        Zombie zombie = (Zombie) zombie_;
                        if(zombie.isDead()){
                            continue;
                        }
                        if((zombie.x - pea.x) > -20 && (zombie.x - pea.x) < 20){
                            caughtInZombie = true;
                            zombie.reduce_HP(pea.attackStat);
                            peasToremove[i].add(pea);
                            pea.setX(-100);
                            pea.setY(-200);
                            pea.isActive = false;
                            break;
                        }
                    }
                    if(!caughtInZombie){
                        if(pea.x > 900){
                            peasToremove[i].add(pea);
                            pea.setX(-100);
                            pea.setY(-200);
                            pea.isActive = false;
                        }else{
                            pea.setX(pea.x + 1);
                        }
                    }
                }
            }
        }
        for(int i=0; i<5; i++){
            for(Object z: peasToremove[i]){
                peaLanes[i].remove(z);
            }
        }
    }
}
