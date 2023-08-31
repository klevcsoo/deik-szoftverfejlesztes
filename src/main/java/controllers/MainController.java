package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable {

    @FXML
    private BoardController boardController;

    @FXML
    private GameStateDisplayController gameStateDisplayController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardController.onBoardMove(() -> gameStateDisplayController.increaseMoves());
        boardController.onPuzzleCompleted(
            () -> gameStateDisplayController.handlePuzzleCompletion());
        gameStateDisplayController.onResetPressed(() -> boardController.resetBoard());
    }
}
