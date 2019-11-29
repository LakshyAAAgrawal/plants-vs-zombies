package PvZ;

import javafx.scene.layout.Pane;

public class LawnGrid {
    double[][][] gridCoordinates;
    Plant[][] plantsGrid;
    Pane mainPane;
    public LawnGrid(Pane mainPane){
        gridCoordinates = new double[][][]{
                {{200, 85}, {270, 85}, {328, 85}, {394, 85}, {455, 85}, {523, 85}, {588, 85}, {646, 85}, {708, 85}, {782, 85}},
                {{200, 180}, {270, 180}, {328, 180}, {394, 180}, {455, 180}, {523, 180}, {588, 180}, {646, 180}, {708, 180}, {782, 180}},
                {{200, 270}, {270, 270}, {328, 270}, {394, 270}, {455, 270}, {523, 270}, {588, 270}, {646, 270}, {708, 270}, {782, 270}},
                {{200, 383}, {270, 383}, {328, 383}, {394, 383}, {455, 383}, {523, 383}, {588, 383}, {646, 383}, {708, 383}, {782, 383}},
                {{200, 477}, {270, 477}, {328, 477}, {394, 477}, {455, 477}, {523, 477}, {588, 477}, {646, 477}, {708, 477}, {782, 477}},
                {{200, 578}, {270, 578}, {328, 578}, {394, 578}, {455, 578}, {523, 578}, {588, 578}, {646, 578}, {708, 578}, {782, 578}}
        };
        System.out.println(gridCoordinates.length + " " + gridCoordinates[0].length + " " + gridCoordinates[0][0].length);
        plantsGrid = new Plant[5][9];
        this.mainPane = mainPane;
    }
    public boolean withinGrid(double x, double y){
        if(x < gridCoordinates[0][0][0] ||
                x > gridCoordinates[0][9][0] ||
                y > gridCoordinates[5][9][1] ||
                y < gridCoordinates[0][0][1]){
            return false;
        }else{
            return true;
        }
    }

    public void addPlant(Plant newPlant, double sceneX, double sceneY) {
        System.out.println("Add Plant");
        newPlant.image.setFitWidth(100);
        newPlant.image.setFitWidth(100);
        int x_index, y_index;
        y_index = getYindex(sceneY);
        x_index = getXindex(sceneX, y_index);
        double x_co = (gridCoordinates[y_index][x_index][0] + gridCoordinates[y_index][x_index + 1][0])/2;
        double y_co = (gridCoordinates[y_index][x_index][1] + gridCoordinates[y_index + 1][x_index][1])/2;
        newPlant.image.setLayoutX(gridCoordinates[y_index][x_index][0]);
        newPlant.image.setLayoutY(gridCoordinates[y_index][x_index][1]);
        mainPane.getChildren().add(newPlant.image);
        plantsGrid[y_index][x_index] = newPlant;
    }

    public int getYindex(double y){
        int y_index = 0;
        while(gridCoordinates[y_index][0][1] < y)
            y_index++;
        return y_index - 1;
    }

    public int getXindex(double x, int y_index){
        int x_index = 0;
        while(gridCoordinates[y_index][x_index][0] < x)
            x_index++;
        return x_index - 1;
    }
}
