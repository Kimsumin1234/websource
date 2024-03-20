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

@WebServlet("/update")
public class TodoUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리 (get/post 방식은 한글처리 해준다)
        req.setCharacterEncoding("utf-8");

        String completed = req.getParameter("completed");
        String description = req.getParameter("description");
        String no = req.getParameter("no");

        // DB 작업
        TodoDao dao = new TodoDao();
        TodoDto updateDto = new TodoDto();
        updateDto.setCompleted(Boolean.parseBoolean(completed));
        updateDto.setDescription(description);
        updateDto.setNo(Integer.parseInt(no));

        int result = dao.update(updateDto);

        // 서블릿 list 이동
        resp.sendRedirect("list");

        // setAttribute() 방식이 아니라서 이방법은 사용x
        // RequestDispatcher rd = req.getRequestDispatcher("/view/.jsp");
        // rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
