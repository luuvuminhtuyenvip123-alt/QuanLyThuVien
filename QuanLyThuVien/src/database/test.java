
package database;

import java.sql.Connection;

public class test{

    public static void main(String[] args) {

        try (Connection con = DBConnection.getConnection()) {

            System.out.println("KET NOI THANH CONG!");
            System.out.println("Database: "
                    + con.getCatalog());

        } catch (Exception e) {

            System.out.println("KET NOI THAT BAI!");
            e.printStackTrace();
        }
    }
}
