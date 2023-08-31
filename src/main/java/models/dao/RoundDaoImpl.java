package models.dao;

import java.util.List;
import models.Round;
import models.RoundImpl;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;

class RoundDaoImpl implements RoundDao {

    private static final String LOCAL_PSQL_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String CODD_URL = "jdbc:oracle:thin:@//codd.inf.unideb.hu:1521/ora21cp.inf.unideb.hu";
    private final Jdbi jdbi;

    RoundDaoImpl() {
        String coddUser = System.getenv("CODD_USER");
        String coddPass = System.getenv("CODD_PASS");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            Logger.error(e, "Oracle driver not found");
            System.exit(1);
        }

        if (coddPass != null) {
            jdbi = Jdbi.create(CODD_URL, coddUser, coddPass);
            Logger.info("Connected to CODD");
        } else {
            jdbi = Jdbi.create(LOCAL_PSQL_URL);
            jdbi.installPlugin(new PostgresPlugin());
            Logger.info("Connected to Local PostgreSQL database");
        }

        jdbi.installPlugin(new SqlObjectPlugin());

        jdbi.registerRowMapper(Round.class,
                (rs, ctx) -> new RoundImpl(rs.getInt("moves"),
                        rs.getTimestamp("timestamp").toLocalDateTime()));
    }

    @Override
    public List<Round> listRounds() {
        return jdbi.withHandle(handle -> handle
                .createQuery("select * from puzzle_round")
                .mapTo(Round.class)
                .list());
    }

    @Override
    public Round getRecordRound() {
        List<Round> rounds = jdbi.withHandle(handle -> handle
                .createQuery(
                        "select * from puzzle_round order by moves, timestamp fetch first row only ")
                .mapTo(Round.class)
                .list());

        return rounds.size() == 0 ? null : rounds.get(0);
    }

    @Override
    public void saveRound(int moves) {
        jdbi.useHandle(
                handle -> handle.execute("insert into puzzle_round (moves) values (?)", moves));
    }
}
