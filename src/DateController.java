import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class DateController {

    @FXML
    private DatePicker dateAt;

    @FXML
    void getDate(ActionEvent event) {

        System.out.println(dateAt.getValue().toString());

    }
}
