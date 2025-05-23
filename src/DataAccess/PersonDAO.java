package DataAccess;
import java.sql.*;

public class PersonDAO {
    public ResultSet GameRestridedSearcbByCountryAndPegi(int idGame) {
        ResultSet resultSet = null;
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.Name AS clientName, p.FirstName AS clientFirstName, p.BirthDate AS clientBirthDate," +
                    "g.Title AS gameTitle, g.AgeRestriction AS gameAgeRestriction, ge.Name AS genreName" +
                    "FROM person p" +
                    "INNER JOIN document d ON d.person = p.id" +
                    "INNER JOIN documentline dl ON dl.Document = d.Reference" +
                    "INNER JOIN game g ON g.Title = dl.Game" +
                    "INNER JOIN genre ge ON ge.Name = g.Genre" +
                    "WHERE p.Country = ? AND g.AgeRestriction < ? AND p.IsClient = 1" +
                    "ORDER BY g.AgeRestriction ASC"
            );

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
