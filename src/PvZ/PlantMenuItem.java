package PvZ;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URISyntaxException;

public class PlantMenuItem {
    ImageView displayImage;
    int sunCost;
    boolean clickable;
    String activeURL, inactiveURL, plantType;
    PlantFactory plantFactory;

    public PlantMenuItem(String plantType, int sunCost, GameState gamestate){
        this.activeURL = "menu_" + plantType + "1.png";
        this.inactiveURL = "fade_" + plantType + ".jpg";
        clickable = true;
        this.sunCost = sunCost;
        this.plantType = plantType;
        plantFactory = new PlantFactory();
        ImageView im = null;
        try {
            im = new ImageView(new Image(getClass().getResource(activeURL).toURI().toString()));
        }catch(URISyntaxException e){}
        im.setFitHeight(60);
        im.setFitWidth(60);
        im.setOnMouseClicked(e -> {
            if(clickable) {
                //Plant plant = plantFactory.createPlant(plantType);
                gamestate.setPlantSetMode(this);
                //clickable = false;
                try{
                    displayImage.setImage(new Image(getClass().getResource(inactiveURL).toURI().toString()));
                }catch(URISyntaxException ll){}
            }
        });
        displayImage = im;
    }
    public void setActive(){
        this.clickable = true;
        try{
            displayImage.setImage(new Image(getClass().getResource(activeURL).toURI().toString()));
        }catch(URISyntaxException ll){}
    }

    public void setInactive(){
        this.clickable = false;
        try{
            displayImage.setImage(new Image(getClass().getResource(inactiveURL).toURI().toString()));
        }catch(URISyntaxException ll){}
    }

    public Plant getNewPlant(){
        return plantFactory.createPlant(plantType);
    }
}