package Model;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.List;

public class ClientSearchTableModel extends AbstractTableModel {
    private List<ClientSearchResult> results;
    private final String[] columnNames = {"Prénom du client", "Nom du client", "Nom de la rue", "Code postal", "Nom du pays", "Devise du pays"};

    public ClientSearchTableModel(List<ClientSearchResult> results){
        this.results = results;
    }
    public static void setData(List<ClientSearchResult> results){
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
        ClientSearchResult result = results.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return result.getClientFirstName();
            case 1:
                return result.getClientName();
            case 2:
                return result.getClientStreetName();
            case 3:
                return result.getCityZipCode();
            case 4:
                return result.getCountryName();
            case 5:
                return result.getCountryCurrency();
            default:
                return null;
        }
    }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0, 1, 2, 4, 5: return String.class;
                case 3: return Integer.class;
                default: return Object.class;
            }
    }
}
