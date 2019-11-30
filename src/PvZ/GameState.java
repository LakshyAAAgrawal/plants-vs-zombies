package PvZ;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

enum MouseInputStates {
    PLANTSET, NORMAL, SHOVEL;
}

public class GameState implements Serializable {
    private transient AnchorPane baseAnchorPane;
    private int level;
    private PlantMenuItem activatedPlant;
    private int numSunTokens;
    LawnGrid lawnGrid;

    public GameState(AnchorPane mainAnchor, int level){
        this.baseAnchorPane = mainAnchor;
        this.level = level;
        this.lawnGrid = new LawnGrid(mainAnchor);
        this.numSunTokens = 5000;

        mainAnchor.setOnMouseClicked(e -> {
            if(mouseInputState == MouseInputStates.PLANTSET){
                if(lawnGrid.withinGrid(e.getSceneX(), e.getSceneY())){
                    if(activatedPlant != null && activatedPlant.clickable && numSunTokens >= activatedPlant.sunCost){
                        if(lawnGrid.addPlant(activatedPlant.getNewPlant(), e.getSceneX(), e.getSceneY())){
                            numSunTokens = numSunTokens - activatedPlant.sunCost;
                            activatedPlant.setInactive();
                            new AnimationTimer() {
                                int x = 0;
                                PlantMenuItem currPlant = activatedPlant;
                                @Override
                                public void handle(long now) {
                                    x++;
                                    if (x > 300) {
                                        currPlant.setActive();
                                        this.stop();
                                    }
                                }
                            }.start();
                        }
                    }else{
                        mouseInputState = MouseInputStates.NORMAL;
                        //activatedPlant.setActive();
                    }
                }else{
//                    mouseInputState = MouseInputStates.NORMAL;
//                    activatedPlant.setActive();
                }
            }
        });
    }
    private MouseInputStates mouseInputState = MouseInputStates.NORMAL;

    public void createGraphicObjects() throws URISyntaxException {
        createPlantBuyMenu();
    }

    private void createPlantBuyMenu() throws URISyntaxException{
        GridPane gridPane = new GridPane();
        MenuItemFactory menuItemFactory = new MenuItemFactory(this);
        if(level == 1){
            gridPane.add(menuItemFactory.createMenuItem("sunflower").displayImage, 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter").displayImage, 0, 1);
        }else if(level == 2){
            gridPane.add(menuItemFactory.createMenuItem("sunflower").displayImage, 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter").displayImage, 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut").displayImage, 0, 2);
        }else if(level == 3){
            gridPane.add(menuItemFactory.createMenuItem("sunflower").displayImage, 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter").displayImage, 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut").displayImage, 0, 2);
            gridPane.add(menuItemFactory.createMenuItem("cherrybomb").displayImage, 0, 3);
        }else if(level == 4){
            gridPane.add(menuItemFactory.createMenuItem("sunflower").displayImage, 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter").displayImage, 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut").displayImage, 0, 2);
            gridPane.add(menuItemFactory.createMenuItem("cherrybomb").displayImage, 0, 3);
            gridPane.add(menuItemFactory.createMenuItem("repeater").displayImage, 0, 4);
        }else{
            gridPane.add(menuItemFactory.createMenuItem("sunflower").displayImage, 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter").displayImage, 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut").displayImage, 0, 2);
            gridPane.add(menuItemFactory.createMenuItem("cherrybomb").displayImage, 0, 3);
            gridPane.add(menuItemFactory.createMenuItem("repeater").displayImage, 0, 4);
            gridPane.add(menuItemFactory.createMenuItem("tripeater").displayImage, 0, 5);
        }
        baseAnchorPane.getChildren().add(gridPane);
        gridPane.setLayoutX(14);
        gridPane.setLayoutY(14);
    }

    public void advance_one_frame() {

    }

    public void setPlantSetMode(PlantMenuItem plant) {
        this.mouseInputState = MouseInputStates.PLANTSET;
        this.activatedPlant = plant;
    }
}
