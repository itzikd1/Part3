package View;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Option implements Initializable {
    public javafx.scene.control.Button exit;
//    public javafx.scene.control.Label text;
//    public javafx.scene.control.Menu Manu;

    public void close() {
        Platform.exit();
    }

    public void closew() {
        Stage s = (Stage) exit.getScene().getWindow();
        s.close();
    }
//
//    public void Manu(){
//        EventHandler<Event> temp = Manu.getOnShown();
//        System.out.println(temp);
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}