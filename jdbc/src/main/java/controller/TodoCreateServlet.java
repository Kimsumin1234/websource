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

@WebServlet("/create")
public class TodoCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리 (get/post 방식은 한글처리 해준다)
        req.setCharacterEncoding("utf-8");
        // 사용자가 입력한 todo 가져오기
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        // DB 작업
        // 사용자가 입력한 title, description 담기
        // no 는 디비버 시퀀스 생성으로 자동으로 번호가 생기게 해놨음
        TodoDao dao = new TodoDao();

        TodoDto inserDto = new TodoDto();
        inserDto.setTitle(title);
        inserDto.setDescription(description);

        int result = dao.insert(inserDto);

        // 화면이동(list)
        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
