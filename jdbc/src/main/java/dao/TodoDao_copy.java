package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TodoDao_copy {
    // JDBC 단계

    // 1.드라이버 로드
    private Connection con;
    private PreparedStatement psmt;
    private ResultSet rs;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 2.커넥션 얻기
    public Connection getConnection() {
        String url = "jdbc:oracle:thin:@localhost";
        String user = "";
        String password = "";
        return null;
    }

    // 3.sql 작업

    // 4.자원정리
}
