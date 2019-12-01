package PvZ;

import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SkySunSpawner implements Serializable,Updatable{
    GameState gameState;
    ArrayList<Sun> suns;
    private int t=0;
    private int sun_num=0;
    public SkySunSpawner(GameState gameState){
        this.gameState=gameState;
        suns = new ArrayList<Sun>();
        for (int i=0;i<9;i++){
            suns.add(new Sun());
            double x=-10;
            if (i==0){x=400;}
            else if(i==1){x=430;}
            else if(i==2){x=460;}
            else if(i==3){x=490;}
            else if(i==4){x=520;}
            else if(i==5){x=540;}
            else if(i==6){x=590;}
            else if(i==7){x=645;}
            else{x=715;}
            suns.get(i).setX(x);
            suns.get(i).setY(-213);
            gameState.getMainPane().getChildren().add(suns.get(i).image);
        }
    }

    public void update(){
        for(Sun sun: suns){
            if(sun.image == null) sun.createGraphicObject(gameState.getMainPane());
        }
        if (t==0 || t==350) {
            t = 0;
            sun_num = new Random().nextInt(9);
            suns.get(sun_num).setY(suns.get(sun_num).getY_1()+1);
        }
        t++;
        for (int i=0;i<9;i++) {
            suns.get(i).updateY();
            int finalI = i;
            suns.get(i).image.setOnMouseClicked(e -> {
                suns.get(finalI).setX(100);
                suns.get(finalI).setY(-213);
                System.out.println("Sun collected");
                gameState.update_sunTokens();
            });
        }
    }
}