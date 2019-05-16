package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent root = (Parent)fxmlLoader.load(); //FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Rafał Kulik, 175750");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        Controller controller = fxmlLoader.<Controller>getController();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
