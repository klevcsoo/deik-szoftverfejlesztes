package models.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import models.Round;
import org.junit.jupiter.api.Test;

class RoundDaoTest {

    @Test
    void saveRound() {
        RoundDaoProvider.getInstance().saveRound(152);

        List<Round> rounds = RoundDaoProvider.getInstance().listRounds();
        assertTrue(rounds.stream().anyMatch(round -> round.getMoves() == 152));
    }

    @Test
    void listRounds() {
        List<Round> rounds = RoundDaoProvider.getInstance().listRounds();
        assertTrue(rounds.size() > 0);
    }

    @Test
    void getRecordRound() {
        Round recordRound = RoundDaoProvider.getInstance().getRecordRound();
        assertNotNull(recordRound);
    }
}
