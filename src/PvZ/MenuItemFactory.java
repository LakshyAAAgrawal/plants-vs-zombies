package PvZ;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URISyntaxException;

public class MenuItemFactory {
    GameState gamestate;
    public MenuItemFactory(GameState gamestate){
        this.gamestate = gamestate;
    }
    public ImageView createMenuItem(String plantType) throws URISyntaxException {
        ImageView im = new ImageView(new Image(getClass().getResource("menu_" + plantType + "1.png").toURI().toString()));
        im.setFitHeight(60);
        im.setFitWidth(60);

        PlantFactory plantFactory = new PlantFactory();
        im.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Plant plant = plantFactory.createPlant(plantType);
                gamestate.setPlantSetMode()
            }
        });
    }
}
