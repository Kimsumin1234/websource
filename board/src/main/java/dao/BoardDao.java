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
import dto.SearchDto;

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
    // - 전체 게시물 개수 가져오기
    public int getRows(String criteria, String keyword) {
        int total = 0;
        con = getConnection();
        try {
            String sql = "";
            if (criteria.isEmpty()) {
                sql += "SELECT COUNT(*) FROM BOARDTBL";
                pstmt = con.prepareStatement(sql);
            } else {
                sql += "SELECT COUNT(*) FROM BOARDTBL WHERE " + criteria + " LIKE ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + keyword + "%");
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 게시물 개수 1칸 만 나와서 while 을 안쓴다
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return total;
    }

    // - 전체 리스트 가져오기
    public List<BoardDto> getList(SearchDto searchDto) {
        List<BoardDto> list = new ArrayList<>();
        con = getConnection();
        // 페이지 나누기 안할 경우
        // 댓글 작업 (RE_LEV 추가, 정렬기준 추가)
        // String sql = "SELECT BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT ,RE_LEV FROM
        // BOARDTBL ORDER BY RE_REF DESC ,RE_SEQ ASC";

        // start = 페이지번호 * 한 페이지당 게시물 수
        // end = (페이지번호-1) * 한 페이지당 게시물 수
        int start = searchDto.getPage() * searchDto.getAmount();
        int end = (searchDto.getPage() - 1) * searchDto.getAmount();

        try {
            String sql = "SELECT BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT ,RE_LEV ";
            sql += "FROM (SELECT rownum AS rnum, A.* ";
            sql += "FROM (SELECT rownum, BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT ,RE_LEV ";
            sql += "FROM BOARDTBL b WHERE bno > 0 ";

            if (searchDto.getCriteria().isEmpty()) {
                // 페이지 나누기 할 경우
                // 전체 리스트 일시
                sql += "ORDER BY RE_REF DESC ,RE_SEQ ASC) A ";
                sql += "WHERE rownum <= ?) WHERE rnum > ?";

                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, start);
                pstmt.setInt(2, end);
                rs = pstmt.executeQuery();
            } else {
                // 검색 리스트 일시
                sql += "AND " + searchDto.getCriteria() + " LIKE ? ";
                sql += "ORDER BY RE_REF DESC ,RE_SEQ ASC) A ";
                sql += "WHERE rownum <= ?) WHERE rnum > ?";

                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, end);
                rs = pstmt.executeQuery();
            }
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setReadCount(rs.getInt(5));
                dto.setReLev(rs.getInt(6));
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
        // 댓글 작업 : re_ref, re_seq, re_lev 추가
        String sql = "SELECT BNO, NAME ,TITLE ,CONTENT ,ATTACH, re_ref, re_seq, re_lev FROM BOARDTBL WHERE BNO =?";
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
                // reply 에서 필요함
                dto.setReRef(rs.getInt("re_ref"));
                dto.setReSeq(rs.getInt("re_seq"));
                dto.setReLev(rs.getInt("re_lev"));

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
        String sql = "UPDATE BOARDTBL SET TITLE =?,CONTENT =? WHERE BNO =? AND PASSWORD =?";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, updateDto.getTitle());
            pstmt.setString(2, updateDto.getContent());
            pstmt.setInt(3, updateDto.getBno());
            pstmt.setString(4, updateDto.getPassword());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // - 삭제
    public int delete(BoardDto deleteDto) {
        // bno 와 password 일치시 삭제
        int result = 0;
        con = getConnection();
        String sql = "DELETE FROM BOARDTBL WHERE BNO =? AND PASSWORD =?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, deleteDto.getBno());
            pstmt.setString(2, deleteDto.getPassword());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // bno 와 password 받아서 비밀번호 확인
    public int pwdCheck(BoardDto passDto) {
        int result = 0;
        con = getConnection();

        String sql = "SELECT count(*) FROM BOARDTBL WHERE bno =? AND PASSWORD =?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, passDto.getBno());
            pstmt.setString(2, passDto.getPassword());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return result;
    }

    // - 삭제 (부모글이 삭제 되면 댓글도 삭제)
    public int deleteAll(int reRef) {
        int result = 0;
        con = getConnection();
        String sql = "DELETE FROM BOARDTBL WHERE RE_REF=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reRef);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // - 댓글
    public int reply(BoardDto replyDto) {
        int result = 0;
        con = getConnection();

        try {
            // 원본글의 RE_REF,RE_LEV,RE_SEQ 가져오기
            int reRef = replyDto.getReRef();
            int reSeq = replyDto.getReSeq();
            int reLev = replyDto.getReLev();

            String sql = "UPDATE BOARDTBL SET RE_SEQ = RE_SEQ + 1 WHERE RE_REF = ? AND RE_SEQ > ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reRef);
            pstmt.setInt(2, reSeq);
            pstmt.executeUpdate();

            sql = "INSERT INTO BOARDTBL (BNO,NAME,PASSWORD,TITLE,CONTENT,RE_REF,RE_LEV,RE_SEQ) ";
            sql += "VALUES (board_seq.nextval,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, replyDto.getName());
            pstmt.setString(2, replyDto.getPassword());
            pstmt.setString(3, replyDto.getTitle());
            pstmt.setString(4, replyDto.getContent());
            pstmt.setInt(5, reRef);
            pstmt.setInt(6, reLev + 1);
            pstmt.setInt(7, reSeq + 1);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // - 조회수 업데이트
    // - BoardReadAction.java 와 연계하게 되면 새로고침마다 조회수가 올라가는 문제가 발생
    // - 이러한 문제를 해결하기위해서 조회수 업데이트 action 을 따로 만든다
    public int updateCount(int bno) {
        int result = 0;
        try {
            con = getConnection();
            String sql = "UPDATE BOARDTBL SET read_count = read_count + 1 WHERE BNO = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // - 검색
    // - criteria 조건을 하나의 sql 구문으로 해결하기 위해서 SearchDto 를 만든다
    // - WHERE 에 = 대신 like 와 % 사용
    public List<BoardDto> getSearchList(SearchDto searchDto) {
        List<BoardDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "SELECT BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT ,RE_LEV ";
        sql += "FROM BOARDTBL WHERE " + searchDto.getCriteria() + " like ? ";
        sql += "ORDER BY RE_REF DESC ,RE_SEQ ASC";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setReadCount(rs.getInt(5));
                dto.setReLev(rs.getInt(6));
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
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
