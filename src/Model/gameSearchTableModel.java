package Model;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.List;

public class GameSearchTableModel extends AbstractTableModel {
    private List<GameSearchResult> results;
    private final String[] columnNames = {"Titre du jeu", "Date de l'achat", "Quantitée achetée", "Prix unitaire de l'achat", "Total payé"};

    public GameSearchTableModel(List<GameSearchResult> results){
        this.results = results;
    }
    public void setData(List<GameSearchResult> results){
        this.results = results;
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return results.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GameSearchResult result = results.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return result.getGametitle();
            case 1:
                return result.getDateBought();
            case 2:
                return result.getQuantityBought();
            case 3:
                return result.getUnitPriceBought();
            case 4:
                return result.getQuantityBought() * result.getUnitPriceBought();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return Date.class;
            case 2: return Integer.class;
            case 3, 4: return Double.class;
            default: return Object.class;
        }
    }
}
