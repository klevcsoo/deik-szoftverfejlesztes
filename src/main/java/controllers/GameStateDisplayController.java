package controllers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.Round;
import models.dao.RoundDaoProvider;
import org.tinylog.Logger;
import util.NoArgVoidCallback;

public class GameStateDisplayController implements Initializable {

    @FXML
    public Text movesText;
    @FXML
    public Text recordText;
    @FXML
    public Button resetButton;

    private int moves;
    private boolean isPuzzleCompleted;
    private NoArgVoidCallback resetCallback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moves = 0;
        isPuzzleCompleted = false;

        resetButton.setOnMouseClicked(event -> {
            if (resetCallback != null) {
                resetCallback.call();
                isPuzzleCompleted = false;
                updateMoves(0);
            }
        });

        updateRecordText();
    }

    public void increaseMoves() {
        updateMoves(moves + 1);
    }

    public void handlePuzzleCompletion() {
        // for some reason, this event is called before
        // the last move event, so at the time of the execution
        // the value of moves is behind the actual number
        // by exactly 1
        moves++;

        isPuzzleCompleted = true;
        movesText.setText("Puzzle completed in " + moves + " moves!");
        saveRoundToDatabase();
        updateRecordText();
    }

    public void onResetPressed(NoArgVoidCallback callback) {
        resetCallback = callback;
    }

    private void updateMoves(int moves) {
        if (!isPuzzleCompleted) {
            this.moves = moves;
            movesText.setText("Moves: " + moves);
        }
    }

    private void saveRoundToDatabase() {
        RoundDaoProvider.getInstance().saveRound(moves);
        Logger.debug("Round saved to database");
    }

    private void updateRecordText() {
        Round record = RoundDaoProvider.getInstance().getRecordRound();

        String text;
        if (record == null) {
            text = "No record.";
        } else {
            // noinspection StringBufferReplaceableByString
            text = new StringBuilder()
                .append("Record: ")
                .append(record.getMoves())
                .append(" moves, achieved on ")
                .append(record.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .append(" at ")
                .append(record.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                .append(".")
                .toString();
        }

        recordText.setText(text);
    }
}
