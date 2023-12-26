package yassineidr.com.server;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

import static javafx.collections.FXCollections.observableArrayList;

public class EmployerController implements Initializable {

    @FXML
    private TextField NomEmpTxt;
    @FXML
    private TextField SalaireTxt;
    @FXML
    private TextField AgeTxt;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<Integer> RefDeptTxt;

    private int employerId; // Field to store the received employer ID

    public void setEmployerId(int employerId) {
        this.employerId = employerId; // Set the employer ID received from EmployerViewController
    }

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
    public void toEmp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/yassineidr/com/server/EmployerView.fxml"));
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

    public void AddEmp(ActionEvent e){
        try{
            String nomEmploye = NomEmpTxt.getText();
            float salaire = Float.parseFloat(SalaireTxt.getText());
            int age = Integer.parseInt(AgeTxt.getText());
            Integer referenceDept = RefDeptTxt.getValue();
            Employer emp = Employer.builder()
                    .NomEmp(nomEmploye)
                    .Salaire(salaire)
                    .Age(age)
                    .RefDept(referenceDept)
                    .build();
            DAOEmployer daoEmployer = new DAOEmployer();
            daoEmployer.Create(emp);
            toEmp(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Employer added with success");
            alert.showAndWait();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public void UpEmp(ActionEvent e){
        try{
            EmployerController ec = new EmployerController();
            String nomEmploye = NomEmpTxt.getText();
            float salaire = Float.parseFloat(SalaireTxt.getText());
            int age = Integer.parseInt(AgeTxt.getText());
            Integer referenceDept = RefDeptTxt.getValue();
            Employer emp = Employer.builder()
                    .IdEmp(employerId)
                    .NomEmp(nomEmploye)
                    .Salaire(salaire)
                    .Age(age)
                    .RefDept(referenceDept)
                    .build();
            
            DAOEmployer daoEmployer = new DAOEmployer();
            daoEmployer.Update(emp,employerId);
            ec.toEmp(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Employer updated with success");
            alert.showAndWait();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DAODepartement daoDepartement = new DAODepartement();
        listDept.addAll(daoDepartement.AllIds()) ;
        RefDeptTxt.getItems().addAll(listDept);


    }
}
