package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            // LOAD DRIVER (RẤT QUAN TRỌNG KHI CHẠY JUNIT)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=QL_KHACHHANG;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true";

            String user = "sa";
            String pass = "123";

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
