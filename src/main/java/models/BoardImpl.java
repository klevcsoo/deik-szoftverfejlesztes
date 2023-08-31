package models;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;
import util.BoardPosition;
import util.GameObjectColour;
import util.InvalidMoveDirectionException;
import util.NoArgVoidCallback;

public class BoardImpl implements Board {

    private static final BoardPosition DEFAULT_BLUE_POS = new BoardPosition(4, 1);
    private static final BoardPosition DEFAULT_RED_POS = new BoardPosition(4, 2);
    private final BoardPosition bluePosition, redPosition;
    private final List<List<Tile>> tiles;
    private final List<GateTile> gateTiles;
    private NoArgVoidCallback moveHandler;
    private NoArgVoidCallback puzzleCompletedHandler;
    private boolean movementDisabled;

    public BoardImpl(@NotNull List<Tile> tiles) {
        this.tiles = new ArrayList<>();
        gateTiles = new ArrayList<>();

        for (int row = 0; row < 5; row++) {
            this.tiles.add(new ArrayList<>());
            for (int col = 0; col < 5; col++) {
                int i = row * 5 + col;
                this.tiles.get(row).add(tiles.get(i));

                if (tiles.get(i) instanceof GateTile) {
                    gateTiles.add((GateTile) tiles.get(i));
                }
            }
        }

        bluePosition = new BoardPosition(4, 1);
        redPosition = new BoardPosition(4, 2);

        updateTiles();
    }

    @Override
    public void moveNorth() {
        moveInDirection(MoveDirection.NORTH);
    }

    @Override
    public void moveSouth() {
        moveInDirection(MoveDirection.SOUTH);
    }

    @Override
    public void moveWest() {
        moveInDirection(MoveDirection.WEST);
    }

    @Override
    public void moveEast() {
        moveInDirection(MoveDirection.EAST);
    }

    @Override
    public void setMoveHandler(NoArgVoidCallback moveHandler) {
        this.moveHandler = moveHandler;
    }

    @Override
    public void setPuzzleCompletedHandler(NoArgVoidCallback puzzleCompletedHandler) {
        this.puzzleCompletedHandler = puzzleCompletedHandler;
    }

    @Override
    public void resetBoard() {
        bluePosition.copyFrom(DEFAULT_BLUE_POS);
        redPosition.copyFrom(DEFAULT_RED_POS);
        movementDisabled = false;

        updateTiles();
    }

    private void moveInDirection(MoveDirection direction) {
        if (movementDisabled) {
            Logger.debug("Could not move: movement disabled");
            return;
        }

        BoardPosition projectedBlue = getProjectedPos(bluePosition, direction);
        BoardPosition projectedRed = getProjectedPos(redPosition, direction);

        if (projectedBlue.equals(projectedRed)) {
            Logger.debug("Could not move: balls would overlap");
            return;
        }

        if (bluePosition.equals(projectedBlue) && redPosition.equals(projectedRed)) {
            Logger.debug("Could not move: no space");
            return;
        }

        bluePosition.copyFrom(projectedBlue);
        redPosition.copyFrom(projectedRed);

        updateTiles();
        if (moveHandler != null) {
            moveHandler.call();
        }
    }

    private BoardPosition getProjectedPos(BoardPosition position, MoveDirection direction) {
        if (direction == MoveDirection.NORTH || direction == MoveDirection.SOUTH) {
            boolean movingNorth = direction == MoveDirection.NORTH;
            int projectedRow = position.getRow();

            while (true) {
                int nextRow = projectedRow + (movingNorth ? -1 : 1);
                if (nextRow < 0 || nextRow > 4) {
                    break;
                }

                boolean northernWall = tiles
                    .get(movingNorth ? projectedRow : nextRow)
                    .get(position.getCol())
                    .northernWallProperty()
                    .getValue();
                boolean southernWall = tiles
                    .get(movingNorth ? nextRow : projectedRow)
                    .get(position.getCol())
                    .southernWallProperty()
                    .getValue();

                if (northernWall || southernWall) {
                    break;
                }

                projectedRow += movingNorth ? -1 : 1;
            }

            return new BoardPosition(position.getCol(), projectedRow);
        } else if (direction == MoveDirection.WEST || direction == MoveDirection.EAST) {
            boolean movingWest = direction == MoveDirection.WEST;
            int projectedCol = position.getCol();

            while (true) {
                int nextCol = projectedCol + (movingWest ? -1 : 1);
                if (nextCol < 0 || nextCol > 4) {
                    break;
                }

                boolean westernWall = tiles
                    .get(position.getRow())
                    .get(movingWest ? projectedCol : nextCol)
                    .westernWallProperty()
                    .getValue();
                boolean easternWall = tiles
                    .get(position.getRow())
                    .get(movingWest ? nextCol : projectedCol)
                    .easternWallProperty()
                    .getValue();

                if (westernWall || easternWall) {
                    break;
                }

                projectedCol += movingWest ? -1 : 1;
            }

            return new BoardPosition(projectedCol, position.getRow());
        } else {
            throw new InvalidMoveDirectionException();
        }
    }

    private void updateTiles() {
        for (var row : tiles) {
            for (var tile : row) {
                if (bluePosition.equals(tile.boardPositionProperty().getValue())) {
                    tile.ballColourProperty().setValue(GameObjectColour.BLUE);
                } else if (redPosition.equals(tile.boardPositionProperty().getValue())) {
                    tile.ballColourProperty().setValue(GameObjectColour.RED);
                } else {
                    tile.ballColourProperty().setValue(null);
                }
            }
        }

        checkGates();
    }

    private void checkGates() {
        boolean puzzleCompleted = gateTiles.stream().allMatch(
            gateTile -> gateTile.colourProperty().getValue() == gateTile.ballColourProperty()
                .getValue());

        if (puzzleCompleted && puzzleCompletedHandler != null) {
            movementDisabled = true;
            puzzleCompletedHandler.call();
            Logger.info("Puzzle completed");
        }
    }

    private enum MoveDirection {
        NORTH, SOUTH, WEST, EAST
    }
}
