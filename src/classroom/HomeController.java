package classroom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private final static String connection= "jdbc:mysql://localhost:3306/classrooms";
    private final static String user = "root";
    private final static String pwd = "";
    public TableView<Classroom> tbV;
    public TableColumn<Classroom,Integer> tcId;
    public TableColumn<Classroom,String> tcName;
    public TableColumn<Classroom,String> tcCourse;
    public TableColumn<Classroom, Button> tcAction;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCourse.setCellValueFactory(new PropertyValueFactory<>("room"));
        tcAction.setCellValueFactory(new PropertyValueFactory<>("edit"));


        try {
            // Connect database
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(connection,user,pwd);

            //query

            Statement stt = conn.createStatement();
            String sql = "select * from classroom2";
            ResultSet rs = stt.executeQuery(sql);
            ObservableList<Classroom> list = FXCollections.observableArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String room = rs.getString("room");
                Classroom cl = new Classroom(id,name,room);
                list.add(cl);
            }
            tbV.setItems(list);
        } catch (Exception e) {
            System.out.println("error :" +e.getMessage());
        }

    }

    public void goToAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("create.fxml"));
        Main.mainStage.setScene(new Scene(root,600,400));
    }
}


