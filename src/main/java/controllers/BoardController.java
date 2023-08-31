package controllers;

import components.TileComponent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import models.Board;
import models.BoardImpl;
import models.Tile;
import util.BoardKeyEventHandler;
import util.BoardPosition;
import util.NoArgVoidCallback;

public class BoardController implements Initializable {

    @FXML
    public GridPane tileGrid;

    private Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new BoardImpl(extractTiles());

        tileGrid.sceneProperty().addListener((observableValue, scene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, new BoardKeyEventHandler(board));
            }
        });
    }

    public void onBoardMove(NoArgVoidCallback moveHandler) {
        board.setMoveHandler(moveHandler);
    }

    public void onPuzzleCompleted(NoArgVoidCallback puzzleCompletedHandler) {
        board.setPuzzleCompletedHandler(puzzleCompletedHandler);
    }

    public void resetBoard() {
        board.resetBoard();
    }

    private List<Tile> extractTiles() {
        return tileGrid.getChildren().stream()
            .filter(node -> node instanceof TileComponent)
            .map(node -> {
                Tile model = ((TileComponent) node).getModel();
                BoardPosition pos = new BoardPosition(GridPane.getColumnIndex(node),
                    GridPane.getRowIndex(node));
                model.boardPositionProperty().setValue(pos);
                return model;
            })
            .sorted().toList();
    }
}
