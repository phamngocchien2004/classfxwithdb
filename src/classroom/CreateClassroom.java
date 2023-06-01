package classroom;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import database.Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateClassroom {
    public TextField txtName;
    public TextField txtCourse;

    public void goToBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("list.fxml"));
        Main.mainStage.setScene(new Scene(root, 600, 400));
    }

    public void goToCreate(ActionEvent actionEvent) throws Exception {
        // connect database
            Connection conn = new Connector().getConn();
            //add to database
            String name = txtName.getText();
            String room = txtCourse.getText();
            Classroom cl = new Classroom(name, room);
            String sql = "insert into classroom2(name,room) values (?,?)";
            PreparedStatement stt = (PreparedStatement) conn.prepareStatement(sql);
            stt.setString(1, cl.getName());
            stt.setString(2, cl.getRoom());
            stt.executeUpdate();
            goToBack(null);
    }
}
