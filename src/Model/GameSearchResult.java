package Model;

import java.sql.Date;

public class GameSearchResult {
    private String gameTitle;
    private Date dateBought;
    private int quantityBought;
    private double unitPriceBought;

    public GameSearchResult(String gameTitle, Date dateBought, int quantityBought, double unitPriceBought){
        this.gameTitle = gameTitle;
        this.dateBought = dateBought;
        this.quantityBought = quantityBought;
        this.unitPriceBought = unitPriceBought;
    }

    public String getGametitle(){
        return gameTitle;
    }

    public Date getDateBought(){
        return dateBought;
    }

    public int getQuantityBought(){
        return quantityBought;
    }

    public double getUnitPriceBought() {
        return unitPriceBought;
    }
}
