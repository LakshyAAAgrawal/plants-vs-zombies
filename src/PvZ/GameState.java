package PvZ;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

enum MouseInputStates implements Serializable{
    PLANTSET, NORMAL, SHOVEL;
}

public class GameState implements Serializable {
    private transient AnchorPane baseAnchorPane;
    int level;
    private PlantMenuItem activatedPlant;
    private int numSunTokens;
    transient Label timer, score;
    LawnGrid lawnGrid;
    int timerLeft;
    ZombieSpawner zombieSpawner;
    ZombieMover zombieMover;
    PeaSpawner peaSpawner;
    PeaMover peaMover;
    SunflowerSunSpawner sunflowerSunSpawner;
    ArrayList<Updatable> observers;
    private MouseInputStates mouseInputState = MouseInputStates.NORMAL;

    public GameState(AnchorPane mainAnchor, int level, Label timer, Label score, ImageView[] lawnmowers){
        this.baseAnchorPane = mainAnchor;
        this.level = level;
        this.lawnGrid = new LawnGrid(mainAnchor, lawnmowers);
        this.numSunTokens = 5000;
        this.timer = timer;
        this.score = score;
        this.timerLeft = ((level - 1)*30)*60 + 60*60;
        this.observers = new ArrayList<Updatable>();
        zombieSpawner = new ZombieSpawner(this);
        zombieMover = new ZombieMover(this);
        peaSpawner = new PeaSpawner(this);
        sunflowerSunSpawner = new SunflowerSunSpawner(this);
        peaMover = new PeaMover(this);
        observers.add(zombieSpawner);
        observers.add(zombieMover);
        observers.add(peaSpawner);
        observers.add(sunflowerSunSpawner);
        observers.add(peaMover);
        System.out.println("Init " + timerLeft);
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

    public void createGraphicObjects() throws URISyntaxException {
        createPlantBuyMenu();
        mouseInputState = MouseInputStates.NORMAL;
        activatedPlant = null;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 9; j++){
                Plant plant = lawnGrid.plantsGrid[i][j];
                if(plant != null && plant.image == null){
                    plant.createGraphicObject(baseAnchorPane);
                }
            }
            ArrayList zombies = lawnGrid.zombieLanes[i];
            for(Object zombie: zombies){
                Zombie z = (Zombie) zombie;
                if(z.image == null){
                    z.createGraphicObject(baseAnchorPane);
                }
            }
            ArrayList peas = lawnGrid.peaLanes[i];
            for(Object p: peas){
                Pea z = (Pea) p;
                if(z.image == null){
                    z.createGraphicObject(baseAnchorPane);
                }
            }
            if(lawnGrid.lawnmowersUsed[i]){
                lawnGrid.lawnmowers[i].setX(1000);
            }
        }
    }

    public Pane getMainPane(){
        return this.baseAnchorPane;
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

    public void advance_one_frame() throws GameEventException{
        score.setText(Integer.toString(numSunTokens));
        timer.setText(Integer.toString(timerLeft/60));
        timerLeft--;
        if(timerLeft <= 0) throw new GameWonException(numSunTokens);
        boolean won = false;
        for(Updatable observer: observers){
            observer.update();
        }
    }

    public void setPlantSetMode(PlantMenuItem plant) {
        this.mouseInputState = MouseInputStates.PLANTSET;
        this.activatedPlant = plant;
    }

    public void addObserver(Updatable u){
        observers.add(u);
    }

    public void addPea(double x, double y, int laneNum) {
        Pea pea = new Pea(x, y);
        pea.createGraphicObject();
        pea.image.setFitWidth(20);
        pea.image.setFitHeight(20);
        lawnGrid.peaLanes[laneNum].add(pea);
        baseAnchorPane.getChildren().add(pea.image);
        System.out.println("x " + x + ", y " + y);
        pea.setX(x);
        pea.setY(y);
    }

    public void setMainPane(AnchorPane gameScreenPane) {
        this.baseAnchorPane = gameScreenPane;
    }

    public void setTransientAttributes(AnchorPane gameScreenPane, Label timer, Label score, ImageView[] lawnmowers){
        this.baseAnchorPane = gameScreenPane;
        this.timer = timer;
        this.score = score;
        this.lawnGrid.lawnmowers = lawnmowers;
        this.lawnGrid.mainPane = gameScreenPane;
        baseAnchorPane.setOnMouseClicked(e -> {
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
}
