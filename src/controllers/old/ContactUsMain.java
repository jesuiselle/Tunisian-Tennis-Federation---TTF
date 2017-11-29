package controllers.old;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.layout.AnchorPane;

public class ContactUsMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/contacteznous.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane, 1366, 768);

        ContactController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Tft");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
