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
import yassineidr.com.server.JDBC.Classes.Departement;
import yassineidr.com.server.JDBC.Classes.Employer;
import yassineidr.com.server.JDBC.DAOs.DAODepartement;
import yassineidr.com.server.JDBC.DAOs.DAOEmployer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class DepartementViewController implements Initializable {

    @FXML
    private TableColumn<Departement, Integer> IdDept;
    @FXML
    private TableColumn<Departement, String> NomDept;

    @FXML
    private TableView<Departement> TableDept;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ObservableList<Departement> departementList;

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
        DAODepartement daoDepartement = new DAODepartement();
        departementList = observableArrayList(daoDepartement.All());


        // Set cell value factories for each column
        IdDept.setCellValueFactory(new PropertyValueFactory<>("IdDept"));
        NomDept.setCellValueFactory(new PropertyValueFactory<>("NomDept"));


        TableDept.setItems(departementList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

}