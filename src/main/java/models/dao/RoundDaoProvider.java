package models.dao;

/**
 * The RoundDaoProvider class is responsible for providing access to the RoundDao instance.
 */
public class RoundDaoProvider {

    private static RoundDao instance;

    /**
     * Retrieves the singleton instance of the RoundDao.
     *
     * @return The {@link RoundDao} instance.
     */
    public static RoundDao getInstance() {
        synchronized (RoundDaoProvider.class) {
            if (instance == null) {
                instance = new RoundDaoImpl();
            }
        }

        return instance;
    }
}
