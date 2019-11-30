package PvZ;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class PlantFactory implements Serializable {
    public Plant createPlant(String plantType){
        Plant toRet = null;
        if(plantType.equals("sunflower")){
            toRet = new Sunflower(0,0);
        }else if(plantType.equals("peashooter")){
            toRet = new Peashooter(0,0);
        }else if(plantType.equals("walnut")){
            toRet = new Walnut(0,0);
        }else if(plantType.equals("cherrybomb")){
            toRet = new Cherrybomb(0,0);
        }else if(plantType.equals("repeater")){
            toRet = new Repeater(0,0);
        }else if(plantType.equals("tripeater")){
            toRet = new Tripeater(0,0);
        }
        toRet.createGraphicObject();
        return toRet;
    }
}
