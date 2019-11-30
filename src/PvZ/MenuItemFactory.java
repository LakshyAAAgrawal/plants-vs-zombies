package PvZ;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;
import java.net.URISyntaxException;

public class MenuItemFactory implements Serializable {
    GameState gamestate;
    public MenuItemFactory(GameState gamestate){
        this.gamestate = gamestate;
    }
    public PlantMenuItem createMenuItem(String plantType) throws URISyntaxException {
        if(plantType.equals("sunflower")){
            return new PlantMenuItem(plantType, 50, gamestate);
        }else if(plantType.equals("peashooter")){
            return new PlantMenuItem(plantType, 100, gamestate);
        }else if(plantType.equals("walnut")){
            return new PlantMenuItem(plantType, 50, gamestate);
        }else if(plantType.equals("cherrybomb")){
            return new PlantMenuItem(plantType, 150, gamestate);
        }else if(plantType.equals("repeater")){
            return new PlantMenuItem(plantType, 200, gamestate);
        }else if(plantType.equals("tripeater")){
            return new PlantMenuItem(plantType, 250, gamestate);
        }
        return null;
    }
}
