package id.dojo.account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    double x,y = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(root));
//        stage.initStyle(StageStyle.UNDECORATED);
        BorderPane bpane = (BorderPane) root.lookup("#bpane");
        if (bpane != null) {
            bpane.prefWidthProperty().bind(root.getScene().widthProperty().multiply(0.5));
        }
        root.setOnMousePressed(evt->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        }); 
        root.setOnMouseDragged(evt->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}