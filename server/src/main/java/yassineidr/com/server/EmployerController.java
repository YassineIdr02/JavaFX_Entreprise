package yassineidr.com.server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import yassineidr.com.server.JDBC.Classes.Departement;
import yassineidr.com.server.JDBC.Classes.Employer;
import yassineidr.com.server.JDBC.DAOs.DAODepartement;
import yassineidr.com.server.JDBC.DAOs.DAOEmployer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployerController implements Initializable {

    @FXML
    private TextField NomEmp;
    @FXML
    private TextField Salaire;
    @FXML
    private TextField Age;



    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<Integer> RefDept;

    private ArrayList<Integer> listDept = new ArrayList<>();

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

    public void AddEmp(){

        String nomEmploye = NomEmp.getText();
        float salaire = Float.parseFloat(Salaire.getText());
        int age = Integer.parseInt(Age.getText());
        Integer referenceDept = RefDept.getValue();
        Employer emp = Employer.builder()
                .NomEmp(nomEmploye)
                .Salaire(salaire)
                .Age(age)
                .RefDept(referenceDept)
                .build();
        DAOEmployer daoEmployer = new DAOEmployer();
        daoEmployer.Create(emp);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DAODepartement daoDepartement = new DAODepartement();
        listDept.addAll(daoDepartement.AllIds()) ;
        RefDept.getItems().addAll(listDept);


    }
}
