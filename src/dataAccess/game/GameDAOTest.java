package dataAccess.game;

import exception.DataAccessException;
import model.Game;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameDAOTest {

    private GameDAO gameDAO;
    private Connection conn;

    @BeforeEach
    void setUp() throws Exception {
        conn = dataAccess.SingletonConnection.getInstance();
        conn.setAutoCommit(true);
        ensureRefData();
        gameDAO = new GameDAO(conn);
        try { gameDAO.delete("JUnitTestGame"); } catch (DataAccessException ignored) {}
        try { gameDAO.delete("JUnitTestGameUpdated"); } catch (DataAccessException ignored) {}
    }

    @AfterEach
    void tearDown() throws Exception {
        try { gameDAO.delete("JUnitTestGame"); } catch (DataAccessException ignored) {}
        try { gameDAO.delete("JUnitTestGameUpdated"); } catch (DataAccessException ignored) {}
    }

    private void ensureRefData() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("insert into publisher(name) values (?) on duplicate key update name=name")) {
            ps.setString(1, "Ubisoft"); ps.executeUpdate();
            ps.setString(1, "Sony"); ps.executeUpdate();
        }
        try (PreparedStatement ps = conn.prepareStatement("insert into platform(name) values (?) on duplicate key update name=name")) {
            ps.setString(1, "PC"); ps.executeUpdate();
            ps.setString(1, "PS5"); ps.executeUpdate();
        }
        try (PreparedStatement ps = conn.prepareStatement("insert into genre(name, description) values (?, ?) on duplicate key update name=name")) {
            ps.setString(1, "Action"); ps.setString(2, "Action"); ps.executeUpdate();
            ps.setString(1, "Adventure"); ps.setString(2, "Adventure"); ps.executeUpdate();
        }
    }

    @Test
    public void testInsertAndGetGameByTitle() throws DataAccessException {
        Game game = new Game(
                "JUnitTestGame",
                19.99,
                Date.valueOf("2024-01-01"),
                "JUnit test description",
                16,
                true,
                8.5,
                10,
                "Ubisoft",
                "PC",
                "Action"
        );
        gameDAO.insert(game);
        Game fetched = gameDAO.getGameByTitle("JUnitTestGame");
        assertNotNull(fetched);
        assertEquals("JUnitTestGame", fetched.getTitle());
        assertEquals(19.99, fetched.getPrice());
        assertEquals("Ubisoft", fetched.getPublisher());
        assertEquals("PC", fetched.getPlatform());
        assertEquals("Action", fetched.getGenre());
    }

    @Test
    public void testUpdate() throws DataAccessException {
        Game game = new Game(
                "JUnitTestGame",
                19.99,
                Date.valueOf("2024-01-01"),
                "JUnit test description",
                16,
                true,
                8.5,
                10,
                "Ubisoft",
                "PC",
                "Action"
        );
        gameDAO.insert(game);
        Game updated = new Game(
                "JUnitTestGameUpdated",
                29.99,
                Date.valueOf("2024-02-02"),
                "Updated description",
                18,
                false,
                12.0,
                5,
                "Sony",
                "PS5",
                "Adventure"
        );
        gameDAO.update(updated, "JUnitTestGame");
        Game fetched = gameDAO.getGameByTitle("JUnitTestGameUpdated");
        assertNotNull(fetched);
        assertEquals("JUnitTestGameUpdated", fetched.getTitle());
        assertEquals(29.99, fetched.getPrice());
        assertEquals("Sony", fetched.getPublisher());
        assertEquals("PS5", fetched.getPlatform());
        assertEquals("Adventure", fetched.getGenre());
    }

    @Test
    public void testDelete() throws DataAccessException {
        Game game = new Game(
                "JUnitTestGame",
                19.99,
                Date.valueOf("2024-01-01"),
                "JUnit test description",
                16,
                true,
                8.5,
                10,
                "Ubisoft",
                "PC",
                "Action"
        );
        gameDAO.insert(game);
        gameDAO.delete("JUnitTestGame");
        Game fetched = gameDAO.getGameByTitle("JUnitTestGame");
        assertNull(fetched);
    }

    @Test
    public void testGetAllGameContainsInserted() throws DataAccessException {
        Game game = new Game(
                "JUnitTestGame",
                19.99,
                Date.valueOf("2024-01-01"),
                "JUnit test description",
                16,
                true,
                8.5,
                10,
                "Ubisoft",
                "PC",
                "Action"
        );
        gameDAO.insert(game);
        List<Game> games = gameDAO.getAllGame();
        assertNotNull(games);
        boolean found = false;
        for (Game g : games) {
            if ("JUnitTestGame".equals(g.getTitle())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testGetAllPublisherNames() throws DataAccessException {
        List<String> publishers = gameDAO.getAllPublisherNames();
        assertNotNull(publishers);
        assertTrue(publishers.contains("Ubisoft"));
        assertTrue(publishers.contains("Sony"));
    }

    @Test
    public void testGetAllPlatformNames() throws DataAccessException {
        List<String> platforms = gameDAO.getAllPlatformNames();
        assertNotNull(platforms);
        assertTrue(platforms.contains("PC"));
        assertTrue(platforms.contains("PS5"));
    }

    @Test
    public void testGetAllGenreNames() throws DataAccessException {
        List<String> genres = gameDAO.getAllGenreNames();
        assertNotNull(genres);
        assertTrue(genres.contains("Action"));
        assertTrue(genres.contains("Adventure"));
    }

    @Test
    public void testHasRelatedDocumentLinesFalseForNewGame() throws DataAccessException {
        Game game = new Game(
                "JUnitTestGame",
                19.99,
                Date.valueOf("2024-01-01"),
                "JUnit test description",
                16,
                true,
                8.5,
                10,
                "Ubisoft",
                "PC",
                "Action"
        );
        gameDAO.insert(game);
        assertFalse(gameDAO.hasRelatedDocumentLines("JUnitTestGame"));
    }

    @Test
    public void testDeleteWithDocumentLinesOnIsolatedGame() throws DataAccessException {
        Game game = new Game(
                "JUnitTestGame",
                19.99,
                Date.valueOf("2024-01-01"),
                "JUnit test description",
                16,
                true,
                8.5,
                10,
                "Ubisoft",
                "PC",
                "Action"
        );
        gameDAO.insert(game);
        gameDAO.deleteWithDocumentLines("JUnitTestGame");
        assertNull(gameDAO.getGameByTitle("JUnitTestGame"));
    }
}