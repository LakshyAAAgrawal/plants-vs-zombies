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

public class GameState implements Serializable {
    private transient AnchorPane baseAnchorPane;
    private int level;
    public GameState(AnchorPane mainAnchor, int level){
        this.baseAnchorPane = mainAnchor;
        this.level = level;
    }

    public void createGraphicObjects() throws URISyntaxException {
        createPlantBuyMenu();
    }

    private void createPlantBuyMenu() {
        GridPane gridPane = new GridPane();
        MenuItemFactory menuItemFactory = new MenuItemFactory();
        if(level == 1){
            gridPane.add(menuItemFactory.createMenuItem("sunflower"), 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter"), 0, 1);
        }else if(level == 2){
            gridPane.add(menuItemFactory.createMenuItem("sunflower"), 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter"), 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut"), 0, 2);
        }else if(level == 3){
            gridPane.add(menuItemFactory.createMenuItem("sunflower"), 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter"), 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut"), 0, 2);
            gridPane.add(menuItemFactory.createMenuItem("cherrybomb"), 0, 3);
        }else if(level == 4){
            gridPane.add(menuItemFactory.createMenuItem("sunflower"), 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter"), 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut"), 0, 2);
            gridPane.add(menuItemFactory.createMenuItem("cherrybomb"), 0, 3);
            gridPane.add(menuItemFactory.createMenuItem("repeater"), 0, 4);
        }else{
            gridPane.add(menuItemFactory.createMenuItem("sunflower"), 0, 0);
            gridPane.add(menuItemFactory.createMenuItem("peashooter"), 0, 1);
            gridPane.add(menuItemFactory.createMenuItem("walnut"), 0, 2);
            gridPane.add(menuItemFactory.createMenuItem("cherrybomb"), 0, 3);
            gridPane.add(menuItemFactory.createMenuItem("repeater"), 0, 4);
            gridPane.add(menuItemFactory.createMenuItem("tripeater"), 0, 5);
        }
        baseAnchorPane.getChildren().add(gridPane);
        gridPane.setLayoutX(14);
        gridPane.setLayoutY(14);
    }

    public void advance_one_frame() {

    }
}
