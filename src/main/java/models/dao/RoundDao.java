package models.dao;

import java.util.List;
import models.Round;

/**
 * The RoundDao interface provides methods for accessing and managing rounds in a database.
 */
public interface RoundDao {

    /**
     * Retrieves a list of all recorded rounds.
     *
     * @return a list of {@link Round} objects representing recorded rounds
     */
    List<Round> listRounds();

    /**
     * Retrieves the record round, which is the best recorded round.
     *
     * @return the {@link Round} object representing the record round
     */
    Round getRecordRound();

    /**
     * Saves a new round to the database with the specified number of moves.
     *
     * @param moves the number of moves made in the round
     */
    void saveRound(int moves);
}
