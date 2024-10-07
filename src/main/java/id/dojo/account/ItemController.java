package id.dojo.account;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private Label appname;

    @FXML
    private Label email;

    @FXML
    private ImageView icon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItemInfo(String appname, String email, String icon) {
        this.appname.setText(appname);
        this.email.setText(email);
        this.icon.setImage(new Image(String.valueOf(HelloApplication.class.getResource(icon))));
    }
}
