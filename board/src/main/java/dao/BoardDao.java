package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDto;

public class BoardDao {
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
        // 톰캣서버에서 커넥션 얻는 방법
        Context initContext;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            con = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // 3. CRUD
    // - 전체 리스트 가져오기
    public List<BoardDto> getList() {
        List<BoardDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "SELECT BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT  FROM BOARDTBL ORDER BY BNO DESC";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setReadCount(rs.getInt(5));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    // - 새글작성
    public int create(BoardDto insertDto) {
        int result = 0;
        con = getConnection();
        String sql = "INSERT INTO BOARDTBL (BNO,NAME,PASSWORD,TITLE,CONTENT,ATTACH,RE_REF,RE_LEV,RE_SEQ) ";
        sql += "VALUES (board_seq.nextval,?,?,?,?,?,board_seq.currval,?,?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, insertDto.getName());
            pstmt.setString(2, insertDto.getPassword());
            pstmt.setString(3, insertDto.getTitle());
            pstmt.setString(4, insertDto.getContent());
            pstmt.setString(5, insertDto.getAttach());
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // - 조회
    public BoardDto getRow(int bno) {
        BoardDto dto = null;
        con = getConnection();
        String sql = "SELECT BNO, NAME ,TITLE ,CONTENT ,ATTACH FROM BOARDTBL WHERE BNO =?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new BoardDto();
                dto.setBno(rs.getInt("bno"));
                dto.setName(rs.getString("name"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setAttach(rs.getString("attach"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return dto;
    }

    // - 수정
    public int update(BoardDto updateDto) {
        int result = 0;
        con = getConnection();
        String sql = "UPDATE BOARDTBL SET TITLE =?,CONTENT =?,PASSWORD =? WHERE BNO =?";

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
