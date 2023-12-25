package yassineidr.com.server;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import yassineidr.com.server.JDBC.Classes.Employer;
import yassineidr.com.server.JDBC.DAOs.DAODepartement;
import yassineidr.com.server.JDBC.DAOs.DAOEmployer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class EmployerViewController implements Initializable {

    @FXML
    private TableView<Employer> TableEmp;
    @FXML
    private TableColumn<Employer, Integer> IdEmp;
    @FXML
    private TableColumn<Employer, String> NomEmp;
    @FXML
    private TableColumn<Employer, Integer> Age;
    @FXML
    private TableColumn<Employer, Float> Salaire;
    @FXML
    private TableColumn<Employer, Integer> RefDept;

    private ObservableList<Employer> employerList;


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
    private void populateTable() {
        DAOEmployer daoEmployer = new DAOEmployer();
        employerList = observableArrayList(daoEmployer.All());


        // Set cell value factories for each column
        IdEmp.setCellValueFactory(new PropertyValueFactory<>("IdEmp"));
        NomEmp.setCellValueFactory(new PropertyValueFactory<>("NomEmp"));
        Age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Salaire.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
        RefDept.setCellValueFactory(new PropertyValueFactory<>("RefDept"));

        TableEmp.setItems(employerList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

    public void deleteEmp() {
        // Get the selected employer from the table
        Employer selectedEmployer = TableEmp.getSelectionModel().getSelectedItem();

        if (selectedEmployer != null) {
            // Access the DAOEmployer instance
            DAOEmployer daoEmployer = new DAOEmployer();

            // Delete the selected employer using its ID (assuming getIdEmp() returns the ID)
            boolean deleted = daoEmployer.Delete(selectedEmployer.getIdEmp());

            if (deleted) {
                // If deletion is successful, remove the employer from the table's data source
                employerList.remove(selectedEmployer);
            } else {
                // Handle failure scenario (e.g., display an error message)
                // You can use dialogs or other UI components to notify the user about the failure.
                System.out.println("Failed to delete the employer.");
            }
        } else {
            // Handle the case when no employer is selected for deletion
            // Display a message to the user or perform any necessary action.
            System.out.println("Please select an employer to delete.");
        }
    }

    public void updateEmp(){

    }


}
