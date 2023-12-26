package yassineidr.com.server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import yassineidr.com.server.JDBC.Classes.Departement;
import yassineidr.com.server.JDBC.Classes.Employer;
import yassineidr.com.server.JDBC.DAOs.DAODepartement;
import yassineidr.com.server.JDBC.DAOs.DAOEmployer;

import java.io.IOException;

public class DepartementController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField NomDept;

    private int departementId; // Field to store the received employer ID

    public void setDepartementId(int departementId) {
        this.departementId = departementId; // Set the employer ID received from EmployerViewController
    }

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
    public void toDept(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/yassineidr/com/server/DepartementView.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toEmp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/yassineidr/com/server/EmployerView.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void AddDept(ActionEvent e){
        try{
            DepartementViewController dc = new DepartementViewController();
            String nomDepartement = NomDept.getText();
            Departement dept = Departement.builder()
                    .NomDept(nomDepartement)
                    .build();
            DAODepartement daoDepartement = new DAODepartement();
            daoDepartement.Create(dept);
            toDept(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Departement added with success");
            alert.showAndWait();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public void UpDept(ActionEvent e){
        try {
            String nomDepartement = NomDept.getText();
            Departement dept = Departement.builder()
                    .IdDept(departementId)
                    .NomDept(nomDepartement)
                    .build();
            DAODepartement daoDepartement = new DAODepartement();
            daoDepartement.Update(dept,departementId);
            DepartementViewController dc = new DepartementViewController();
            toDept(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Departement updated with success");
            alert.showAndWait();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }


}
