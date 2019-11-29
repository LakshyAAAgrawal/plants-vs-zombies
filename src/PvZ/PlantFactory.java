package PvZ;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlantFactory {
    public Plant createPlant(String plantName){
        if(plantType.equals("sunflower")){
            return new Sunflower();
        }else if(plantType.equals("peashooter")){
            return new Peashooter();
        }else if(plantType.equals("walnut")){
            return new Walnut();
        }else if(plantType.equals("cherrybomb")){
            return new Cherrybomb();
        }else if(plantType.equals("repeater")){
            return new Repeater;
        }else if(plantType.equals("tripeater")){
            return new Tripeater;
        }
    }
}
