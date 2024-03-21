package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BookDto;

public class BookDao {
    // JDBC 단계
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 1. 드라이버 로드
    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 2. 커넥션 얻기
    public Connection getConnection() {
        // 고정 : url="jdbc:oracle:thin:
        // localhost : 내 컴퓨터 (이자리에 회사 서버 ip 가 들어올수있다)
        // xe : 오라클 XE 버전

        // DB 서버에 직접 커넥션 얻는 방법
        // String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // String user = "c##test2";
        // String password = "test";
        // try {
        // con = DriverManager.getConnection(url, user, password);
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // return con;

        // 톰캣서버에서 커넥션 얻는 방법
        // 톰캣서버가 미리 DB 서버에서 데이터를 받아서 관리하기 때문에
        // 개발자는 DB서버에 바로 가지않고 톰캣서버랑 접촉을해서 작업을 할수있게된다
        // url , "c##test2" , "test" 이런 정보는 context.xml 에 저장되있다
        Context initContext;
        try {
            initContext = new InitialContext();
            // java:/comp/env : 자바에서 등록된 환경설정 관리 이름
            // jdbc/myoracle : 환경설정에 들어가서 이 이름이 있는지 찾아봐
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            con = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // 3. sql (CRUD) 작성
    public List<BookDto> geList() {
        List<BookDto> list = new ArrayList<>();

        con = getConnection();

        String sql = "SELECT * FROM BOOKTBL order by code desc";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    public BookDto getRow(int code) {
        BookDto dto = null;
        con = getConnection();
        String sql = "SELECT * FROM BOOKTBL WHERE CODE =?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, code);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new BookDto();
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));
                dto.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    public int update(BookDto updateDto) {
        int result = 0;
        con = getConnection();
        String sql = "UPDATE BOOKTBL SET PRICE =? WHERE CODE =?";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, updateDto.getPrice());
            pstmt.setInt(2, updateDto.getCode());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    public int insert(BookDto insertDto) {
        int result = 0;
        con = getConnection();
        String sql = "INSERT INTO BOOKTBL (CODE,TITLE,WRITER,PRICE,description) VALUES (?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, insertDto.getCode());
            pstmt.setString(2, insertDto.getTitle());
            pstmt.setString(3, insertDto.getWriter());
            pstmt.setInt(4, insertDto.getPrice());
            pstmt.setString(5, insertDto.getDescription());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    public List<BookDto> getSearchList(String criteria, String keyword) {
        List<BookDto> list = new ArrayList<>();
        con = getConnection();
        // String sql = "SELECT * FROM BOOKTBL WHERE CODE =?";

        String sql = "";

        // 검색기준(criteria)이 code라면
        // SELECT * FROM BOOKTBL WHERE CODE =1000; 이거 실행
        // 검색기준(criteria)이 writer라면
        // SELECT * FROM BOOKTBL WHERE WRITER ='홍길동';
        if (criteria.equals("code")) {
            sql = "SELECT * FROM BOOKTBL WHERE CODE =?";
        } else {
            sql = "SELECT * FROM BOOKTBL WHERE WRITER =?";
        }

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookDto dto = new BookDto();
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    public int delete(int code) {
        int result = 0;
        con = getConnection();
        String sql = "DELETE FROM BOOKTBL WHERE CODE = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, code);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 4. 자원 정리
    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
