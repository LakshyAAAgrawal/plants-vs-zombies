package PvZ;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.ArrayList;

public class LawnGrid implements Serializable {
    double[][][] gridCoordinates;
    Plant[][] plantsGrid;
    ArrayList[] zombieLanes;
    transient Pane mainPane;
    boolean lawnmowersUsed[];
    transient ImageView lawnmowers[];
    ArrayList[] peaLanes;

    public LawnGrid(Pane mainPane, ImageView[] lawnmowers){
        gridCoordinates = new double[][][]{
                {{200, 80}, {270, 80}, {328, 80}, {394, 80}, {455, 80}, {523, 80}, {588, 80}, {646, 80}, {708, 80}, {782, 80}},
                {{200, 180}, {270, 180}, {328, 180}, {394, 180}, {455, 180}, {523, 180}, {588, 180}, {646, 180}, {708, 180}, {782, 180}},
                {{200, 270}, {270, 270}, {328, 270}, {394, 270}, {455, 270}, {523, 270}, {588, 270}, {646, 270}, {708, 270}, {782, 270}},
                {{200, 383}, {270, 383}, {328, 383}, {394, 383}, {455, 383}, {523, 383}, {588, 383}, {646, 383}, {708, 383}, {782, 383}},
                {{200, 477}, {270, 477}, {328, 477}, {394, 477}, {455, 477}, {523, 477}, {588, 477}, {646, 477}, {708, 477}, {782, 477}},
                {{200, 578}, {270, 578}, {328, 578}, {394, 578}, {455, 578}, {523, 578}, {588, 578}, {646, 578}, {708, 578}, {782, 578}}
        };
        plantsGrid = new Plant[5][9];
        this.mainPane = mainPane;
        zombieLanes = new ArrayList[5];
        for(int i = 0; i < 5; i++){
            zombieLanes[i] = new ArrayList<Zombie>();
        }
        peaLanes = new ArrayList[5];
        for(int i = 0; i < 5; i++){
            peaLanes[i] = new ArrayList<Pea>();
        }
        lawnmowersUsed = new boolean[]{false, false, false, false, false};
        this.lawnmowers = lawnmowers;
    }
    public boolean withinGrid(double x, double y){
        if(x > gridCoordinates[0][0][0] &&
                x < gridCoordinates[0][9][0] &&
                y < gridCoordinates[5][9][1] &&
                y > gridCoordinates[0][0][1]){
            return true;
        }else{
            return false;
        }
    }

    public boolean addPlant(Plant newPlant, double sceneX, double sceneY) {
        System.out.println("Add Plant");
        newPlant.setDimension(60, 60);
        int x_index, y_index;
        y_index = getYindex(sceneY);
        x_index = getXindex(sceneX, y_index);
        if(plantsGrid[y_index][x_index] == null || plantsGrid[y_index][x_index].isDead()){
            double x_co = (gridCoordinates[y_index][x_index][0] + gridCoordinates[y_index][x_index + 1][0])/2;
            double y_co = (gridCoordinates[y_index][x_index][1] + gridCoordinates[y_index + 1][x_index][1])/2;
            newPlant.setX(gridCoordinates[y_index][x_index][0]);
            newPlant.setY(gridCoordinates[y_index][x_index][1]);
            mainPane.getChildren().add(newPlant.image);
            plantsGrid[y_index][x_index] = newPlant;
            return true;
        }else{
            return false;
        }
    }

    public int getYindex(double y){
        int y_index = 0;
        while(gridCoordinates[y_index][0][1] < y)
            y_index++;
        return y_index - 1;
    }

    public double getZombieInitX(int ln){
        return gridCoordinates[0][9][0] + 100;
    }

    public double getZombieInitY(int ln){
        return (gridCoordinates[ln][9][1] + 1);
    }

    public int getXindex(double x, int y_index){
        int x_index = 0;
        while(gridCoordinates[0][x_index][0] < x)
            x_index++;
        return x_index - 1;
    }

    public void addZombie(Zombie zombie, int laneNo) {
        zombieLanes[laneNo].add(zombie);
    }

    public void useLawnmower(int finalLaneNo) {
        lawnmowersUsed[finalLaneNo] = true;
        new AnimationTimer(){
            int x=0;
            @Override
            public void handle(long now) {
                x++;
                if(lawnmowers[finalLaneNo].getLayoutX()>900){
                    this.stop();
                }
                lawnmowers[finalLaneNo].setLayoutX(lawnmowers[finalLaneNo].getLayoutX()+10);
            }
        }.start();
    }
}
