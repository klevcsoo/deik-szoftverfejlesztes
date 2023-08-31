package models;

import java.time.LocalDateTime;

/**
 * The Round interface represents a recorded round in a game.
 */
public interface Round {

    /**
     * Retrieves the number of moves made in the round.
     *
     * @return The number of moves made in the round.
     */
    Integer getMoves();

    /**
     * Retrieves the timestamp indicating when the round was recorded.
     *
     * @return The LocalDateTime object representing the timestamp of the round.
     */
    LocalDateTime getTimestamp();
}
