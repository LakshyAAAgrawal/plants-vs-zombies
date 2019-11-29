package PvZ;

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
    public GameState(AnchorPane mainAnchor, int level){
        this.baseAnchorPane = mainAnchor;
        this.level = level;
        mainAnchor.setOnMouseClicked(e -> {
            if(mouseInputState == MouseInputStates.PLANTSET){
                
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

    public void setPlantSetMode(Plant plant) {
        this.mouseInputState = MouseInputStates.PLANTSET;

    }
}
