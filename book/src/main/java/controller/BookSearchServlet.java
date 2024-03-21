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

@WebServlet("/search")
public class BookSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 이거를 안써주면 작가가 한글이라 검색이 안된다
        req.setCharacterEncoding("utf-8");

        // 검색기준(criteria)과 검색어(keyword) 가져오기
        // 기준이 code 면 PK 니깐 1개, 기준이 작가면 중복이 나올수있으니 여러개가 나올수있어서 List를 사용
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");
        BookDao dao = new BookDao();
        List<BookDto> list = dao.getSearchList(criteria, keyword);

        req.setAttribute("list", list);

        RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
