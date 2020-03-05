import lombok.*;
import models.Account;
import models.Profile;
import models.Querier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DbUtil {

    private static final String URL = "jdbc:postgresql:// localhost:5432/ dev_profiles_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "org.postgreqsl.Driver";
    private static final String ACCOUNTS = "dev_profiles_db.public.accounts";
    private static final String PROFILES = "dev_profiles_db.public.profiles";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private void queryExecutor(String query) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("SUCCESS: %s%n", query);
    }

    public void insertData(String table, Querier querier) {
        String query = String.format("INSERT into %s %s values %s;",
                table, querier.getFieldsNames(), querier.getValues());
        queryExecutor(query);
    }

    private ResultSet getResultSet(String query) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try (Connection connection = connect()) {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }
        System.out.printf("SUCCESS: %s%n", query);
        return resultSet;
    }

    public List<Querier> read(String table) throws SQLException {
        List<Querier> tables = new ArrayList<>();
        Querier querier = null;
        String query = String.format("select * from %s;", table);
        ResultSet rs = getResultSet(query);
        while (rs.next()) {
            if (query.contains(ACCOUNTS)) {
                querier = new Account(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("city"),
                        rs.getString("gender"), rs.getString("username"));
            } else if (query.contains(PROFILES)) {
                querier = new Profile(rs.getInt("id"), rs.getString("username"),
                        rs.getString("job_title"), rs.getString("department"),
                        rs.getString("company"), rs.getString("skill"));
            }
            else {
                System.err.println("Please, checkout your tables.");
            }
            tables.add(querier);
        }
        return tables;
    }

    public void update(String table, String column, String oldData, String newData, Integer id) {
        String query = String.format("update %s set %s = replace(%s, '%s', '%s') where id = %d;"
                , table, column, column, oldData, newData, id);
        queryExecutor(query);
    }

    public void delete(String table, Integer id) {
        String query = String.format("delete from %s where id = %d;", table, id);
        queryExecutor(query);
    }
}
