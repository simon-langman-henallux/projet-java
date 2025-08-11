package business;

import dataAccess.IGameDAO;
import dataAccess.GameDAO;
import exception.DataAccessException;

import java.math.BigDecimal;
import java.util.Map;

public class SearchService {
    private final IGameDAO gamedao;

    public SearchService() {
        this.gamedao = new GameDAO();
    }

    public BigDecimal totalSalesByGenre(String genre) throws DataAccessException {
        if (genre == null || genre.isBlank()) {
            throw new DataAccessException("Genre is required.");
        }
        Map<String, BigDecimal> map = gamedao.getTotalSalesByGenre();
        BigDecimal total = map.get(genre);
        return total != null ? total : BigDecimal.ZERO;
    }

    public BigDecimal averagePriceByPublisher(String publisher) throws DataAccessException {
        if (publisher == null || publisher.isBlank()) {
            throw new DataAccessException("Publisher is required.");
        }
        Map<String, BigDecimal> map = gamedao.getAveragePriceByPublisher();
        BigDecimal avg = map.get(publisher);
        return avg != null ? avg : BigDecimal.ZERO;
    }

}