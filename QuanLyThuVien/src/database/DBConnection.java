
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:sqlserver://localhost:1433;"
        + "databaseName=QuanLyThuVien;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(
                "com.microsoft.sqlserver.jdbc.SQLServerDriver"
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
