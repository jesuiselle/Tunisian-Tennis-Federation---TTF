package controllers.old;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.layout.AnchorPane;

public class LoginFBMain {
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Connexion.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane, 1366, 768);

        LoginController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Tft");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/
    public static void main(String[] args) {
        String accessToken = "CAACEdEose0cBADZCQqYTuZBckb8DxitEVP1oQxJ4Ok7WfzZClc9xbd7EX9u8f9CniDsah9c3RgJgCXYpaDPJBEfFZB6nOVTCGiZBFDZBZCcnZBCDeZCgpIJA9tOGruZChUW6lLA1QaCIt1xc6tHJSiY2RerkQukfd16odAhW08HbWa5A4KurSa15VogSljeOvtOOddkoAtofXnHNgkvonIefeva5ui6zM1eVnb7eZBlEOI4VgZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        User me = fbClient.fetchObject("me", User.class);
        System.out.println(me.getEmail());
    }

}
