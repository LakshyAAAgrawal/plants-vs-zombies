package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //System.out.println(root.getId());
        //Scene sc = new Scene(root);
        //System.out.println(System.getProperties());
        //sc.getStylesheets().addAll(this.getClass().getResource("style_screen1.css").toExternalForm());

        primaryStage.setTitle("FXML+JAVAFX");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
