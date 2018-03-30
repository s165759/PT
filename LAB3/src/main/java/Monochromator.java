import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Monochromator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/main.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("eisaburg ipułg yt");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
