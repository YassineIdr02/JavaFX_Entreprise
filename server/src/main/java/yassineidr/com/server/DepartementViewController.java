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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableRow;

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
    private TableColumn<Departement, Integer> NbrEmp;

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
    public  void toEmp(ActionEvent e) throws IOException {
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
        NbrEmp.setCellValueFactory(new PropertyValueFactory<>("NbrEmp"));

        TableDept.setItems(departementList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();

        TableDept.setRowFactory(tv -> {
            TableRow<Departement> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Departement selectedDepartement = row.getItem();
                    int departmentId = selectedDepartement.getIdDept();
                    System.out.println(departmentId);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/yassineidr/com/server/DepartementList.fxml"));
                        Parent root = loader.load();
                        EmployerViewController employerController = loader.getController();
                        employerController.setDepartementId(departmentId);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    public void deleteDept() {
        Departement selectedDepartement = TableDept.getSelectionModel().getSelectedItem();

        if (selectedDepartement != null) {
            DAODepartement daoDepartement = new DAODepartement();

            boolean deleted = daoDepartement.Delete(selectedDepartement.getIdDept());

            if (deleted) {
                departementList.remove(selectedDepartement);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Departement deleted with success");
                alert.showAndWait();
            } else {
                System.out.println("Failed to delete the departement.");
            }
        } else {
            System.out.println("Please select a departement to delete.");
        }
    }

    public void updateDept() {
        Departement selectedDepartement = TableDept.getSelectionModel().getSelectedItem();

        if (selectedDepartement != null) {
            int departementId = selectedDepartement.getIdDept();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/yassineidr/com/server/UpdateDepartement.fxml"));
                Parent root = loader.load();
                DepartementController updateController = loader.getController();
                updateController.setDepartementId(departementId);
                Stage stage = (Stage) ((Node) TableDept).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select an employer to update.");
        }
    }




}
