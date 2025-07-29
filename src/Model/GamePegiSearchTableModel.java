package Model;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class GamePegiSearchTableModel extends AbstractTableModel {
    private List<GamePegiSearchResult> results;
    private final String[] columnNames = {"Nom du client", "Prénom du client", "Age du client", "Titre du jeu", "Restriction d'âge du jeu", "Genre du jeu"};

    public GamePegiSearchTableModel(List<GamePegiSearchResult> results){
        this.results = results;
    }
    public void setData(List<GamePegiSearchResult> results){
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
        GamePegiSearchResult result = results.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return result.getClientName();
            case 1:
                return result.getClientFirstName();
            case 2:
                return calculateAge(result.getClientBirthDate());
            case 3:
                return result.getGameTitle();
            case 4:
                return result.getGameAgeRestriction();
            case 5:
                return result.getGenreName();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0, 1, 3, 5: return String.class;
            case 2, 4: return Integer.class;
            default: return Object.class;
        }
    }

    private int calculateAge(Date birthDate) {
        if (birthDate == null) return 0;

        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }
}