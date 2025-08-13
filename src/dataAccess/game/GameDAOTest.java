package dataAccess.game;

import exception.DataAccessException;
import model.Game;
import org.junit.jupiter.api.*;
import java.sql.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameDAOTest {

    private GameDAO gameDAO;

    @BeforeEach
    void setUp() throws DataAccessException {
        gameDAO = new GameDAO();
        try { gameDAO.delete("JUnitTestGame"); } catch (DataAccessException ignored) {}
        try { gameDAO.delete("JUnitTestGameUpdated"); } catch (DataAccessException ignored) {}
    }

    @AfterEach
    void tearDown() throws DataAccessException {
        try { gameDAO.delete("JUnitTestGame"); } catch (DataAccessException ignored) {}
        try { gameDAO.delete("JUnitTestGameUpdated"); } catch (DataAccessException ignored) {}
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
    public void testGetAllGame() throws DataAccessException {
        List<Game> games = gameDAO.getAllGame();
        assertNotNull(games);
        assertFalse(games.isEmpty());
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
    public void testHasRelatedDocumentLines() throws DataAccessException {
        assertTrue(gameDAO.hasRelatedDocumentLines("Cyberpunk 2077"));
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
    public void testDeleteWithDocumentLines() throws DataAccessException {
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