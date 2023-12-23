package yassineidr.com.server;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void toAddEmp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/yassineidr/com/server/AddEmployeeScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toAddDept(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/yassineidr/com/server/AddDepartementScene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
