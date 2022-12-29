package com.example.exceldemo;

import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentController {
    public TableView<Student> tableView;
    public TableColumn<Student,String> colName;
    public TableColumn<Student,String> colGender;
    public TableColumn<Student,String> colPhone;
    public TableColumn<Student,String> colEmail;
    private ObservableList<Student> studentsList = FXCollections.observableArrayList();

    public void chooseStudent(ActionEvent actionEvent) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file =  fileChooser.showOpenDialog(null);

        if (file == null){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid File");

        }else {

            CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()));
            List<String[]> list = reader.readAll();
            Iterator<String[]> it = list.iterator();
            List<String[]> l = new ArrayList<>();
            if (null != studentsList){
                studentsList.clear();
            }

            while (it.hasNext()) {
                l.add(it.next());
            }

            for (int i = 0; i < l.size(); i++) {
                if (i != 0){
                    String name = l.get(i)[0];
                    String gender = l.get(i)[1];
                    String phone = l.get(i)[2];
                    String email = l.get(i)[3];

                    Student student = new Student(name , gender,phone,email);
                    studentsList.add(student);
                }
            }

            tableView.setItems(studentsList);

            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        }

    }
}