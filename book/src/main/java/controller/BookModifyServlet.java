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

@WebServlet("/modify")
public class BookModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // modify.jsp 에서 넘긴값 가져오기
        // 한글처리

        req.setCharacterEncoding("utf-8");

        BookDto modifyDto = new BookDto();

        // 한꺼번에 쓰는 방법
        modifyDto.setCode(Integer.parseInt(req.getParameter("code")));
        modifyDto.setPrice(Integer.parseInt(req.getParameter("price")));

        // 나눠서 쓰는 방법
        // int code = Integer.parseInt(req.getParameter("code"));
        // int price = Integer.parseInt(req.getParameter("price"));
        // BookDto insertDto = new BookDto();
        // insertDto.setCode(code);
        // insertDto.setPrice(price);

        BookDao dao = new BookDao();
        int result = dao.update(modifyDto);

        if (result > 0) {
            resp.sendRedirect("/list");
        } else {
            resp.sendRedirect("/view/modify.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
