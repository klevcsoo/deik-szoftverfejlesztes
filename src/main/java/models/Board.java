package models;

import util.NoArgVoidCallback;

/**
 * The Board interface represents a game board on which tiles are placed. The board consists of a
 * grid of tiles.
 *
 * @see Tile
 * @see GateTile
 */
public interface Board {

    /**
     * Moves both the balls towards the north if possible.
     */
    void moveNorth();

    /**
     * Moves both the balls towards the south if possible.
     */
    void moveSouth();

    /**
     * Moves both the balls towards the west if possible.
     */
    void moveWest();

    /**
     * Moves both the balls towards the north if possible.
     */
    void moveEast();

    /**
     * Sets the function that should be called when the player made a move.
     * <p>
     * Example:
     * <code>
     * board.setMoveHandler(() -> { System.out.println("the balls moved!") });
     * </code>
     *
     * @param moveHandler The new move handler.
     */
    void setMoveHandler(NoArgVoidCallback moveHandler);

    /**
     * Sets the function that should be called when the puzzle is completed.
     * <p>
     * <code>
     * board.setPuzzleCompletedHandler(() -> { System.out.println("the puzzle is completed!") });
     * </code>
     *
     * @param puzzleCompletedHandler The new game over handler.
     */
    void setPuzzleCompletedHandler(NoArgVoidCallback puzzleCompletedHandler);

    /**
     * Resets the board to the initial positions.
     */
    void resetBoard();
}
