package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.TodoCreateAction;
import action.TodoDeleteAction;
import action.TodoListAction;
import action.TodoReadAction;
import action.TodoUpdateAction;
import dao.TodoDao;
import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

@WebServlet("*.do")
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글 처리
        req.setCharacterEncoding("utf-8");

        // 경로에서 요청 찾기
        // requestUri : 8080 이후의 값
        // contextPath : /todo_m2 (지금은 ROOT 사용으로 떼어내서 안보인다)
        // cmd 에서 substring 를 한것 : /todo_m2/list.do - /todo_m2 = /list.do
        // cmd 로 어디서 사용자가 요청했는지 쉽게 알수있게된다
        String requestUri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String cmd = requestUri.substring(contextPath.length());

        // 브라우저창 에서 Todo List 를 누르면 vscode 하단 OUTPUT 에 sout 된게 나온다
        // System.out.println("requestUri : " + requestUri);
        // System.out.println("contextPath : " + contextPath);
        System.out.println("cmd : " + cmd);

        // / 랑 . 제거
        // cmd = cmd.substring(contextPath.length() + 1, cmd.lastIndexOf("."));
        // System.out.println("cmd sub : " + cmd);

        Action action = null;

        if (cmd.equals("/list.do")) {

            action = new TodoListAction("/view/list.jsp");

        } else if (cmd.equals("/read.do")) {

            action = new TodoReadAction("/view/read.jsp");

        } else if (cmd.equals("/modify.do")) {

            // path 만 다르고 코드는 TodoReadAction 이랑 동일해서 이렇게 가능하다
            action = new TodoReadAction("/view/modify.jsp");

        } else if (cmd.equals("/delete.do")) {

            action = new TodoDeleteAction("/list.do");

        } else if (cmd.equals("/update.do")) {

            action = new TodoUpdateAction("/list.do");

        } else if (cmd.equals("/create.do")) {

            action = new TodoCreateAction("/list.do");

        }

        ActionForward af = null;

        // action 호출 (try catch)
        try {
            af = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (af.isRedirect()) {
            resp.sendRedirect(af.getPath());
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(af.getPath());
            rd.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
