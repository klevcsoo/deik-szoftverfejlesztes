import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeworkApplication extends Application {

    private static final String WINDOW_TITLE = "Rolling Balls Puzzle";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("/views/main-view.fxml")));

        stage.setScene(new Scene(root));
        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(false);

        stage.show();
    }
}
