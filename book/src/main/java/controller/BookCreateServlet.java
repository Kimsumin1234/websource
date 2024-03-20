package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dto.BookDto;

@WebServlet("/create")
public class BookCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // create.jsp 에서 넘긴값 가져오기
        // 한글처리

        // 오라클에서 테이블을 만들때 code 컬럼에 PRIMARY KEY 를 걸어서 중복불가를 했음
        // sql 구문을 잘못썻을경우 밑에창 output에 뜬다
        // 중복으로 code를 넣을경우 : Error Message = ORA-00001: 무결성 제약 조건(C##TEST2.SYS_C008465)에
        // 위배됩니다 가 뜬다

        req.setCharacterEncoding("utf-8");
        // Integer.parseInt("") => NumberFormatException 을 날리게 된다 (String 타입이난 공백 같은걸
        // 넣게되면)
        // modify.jsp, create.jsp 같은경우 사용자가 잘못 입력하면 500번 에러가 난다
        // 그걸 해결하기 위해서 사용자가 입력을 못하게 브라우저창에서 script를 사용해 막는다
        int code = Integer.parseInt(req.getParameter("code"));
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");

        BookDao dao = new BookDao();
        BookDto insertDto = new BookDto(code, title, writer, price, description);
        int result = dao.insert(insertDto);

        // 생성 실패시 다시 생성 창으로 돌아오게 함
        if (result > 0) {
            resp.sendRedirect("/list");
        } else {
            resp.sendRedirect("/view/create.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
