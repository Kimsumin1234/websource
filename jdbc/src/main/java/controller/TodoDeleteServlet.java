package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

@WebServlet("/delete")
public class TodoDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리 (get/post 방식은 한글처리 해준다)
        req.setCharacterEncoding("utf-8");

        // 삭제 a태그 가 보낸 no 가져오기
        String no = req.getParameter("no");

        // DB 작업
        TodoDao dao = new TodoDao();

        int result = dao.delete(no);

        // 화면이동(list)
        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
