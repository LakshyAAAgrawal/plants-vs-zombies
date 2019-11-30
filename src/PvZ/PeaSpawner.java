package PvZ;

import java.io.Serializable;

public class PeaSpawner implements Serializable, Updatable {
    GameState gameState;
    public PeaSpawner(GameState gameState){
        this.gameState = gameState;
    }
    @Override
    public void update() throws GameEventException {

    }
}
