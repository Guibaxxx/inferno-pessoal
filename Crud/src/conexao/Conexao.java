package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String DB_HOST = "mysql-11fa8237-ba-4319.c.aivencloud.com";
    private static final String DB_PORT = "17599";
    private static final String DB_NAME = "deuses_entidades_divinas_Guilherme";
    private static final String DB_USER = "avnadmin";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME +
            "?useSSL=false" +
            "&requireSSL=true" +
            "&verifyServerCertificate=true" +
            "&allowPublicKeyRetrieval=true";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}