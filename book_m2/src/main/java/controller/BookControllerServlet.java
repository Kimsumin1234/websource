package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.BookCreateAction;
import action.BookDeleteAction;
import action.BookLeaveAction;
import action.BookListAction;
import action.BookLoginAction;
import action.BookLogoutAction;
import action.BookPasswordAction;
import action.BookUpdateAction;
import action.BookReadAction;
import action.BookRegisterAction;
import action.BookSearchAction;

@WebServlet("*.do")
public class BookControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 한글처리
        req.setCharacterEncoding("utf-8");
        // 2. URI 분리
        String requestUri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String cmd = requestUri.substring(contextPath.length());
        // 3. cmd 를 가지고 action 생성
        Action action = null;
        if (cmd.equals("/list.do")) {
            // 작업이 끝난 후 부여줄 페이지 경로
            action = new BookListAction("/view/list.jsp");
        }
        if (cmd.equals("/create.do")) {
            // 여기서 /view/list.jsp 에 가봐야 목록이 안보인다 디자인화면만 보임
            action = new BookCreateAction("/list.do");
        }
        if (cmd.equals("/read.do")) {
            action = new BookReadAction("/view/read.jsp");
        }
        if (cmd.equals("/update.do")) {
            // 수정하고 수정한내용 상세보기로 보여주기
            action = new BookUpdateAction("/read.do");
        }
        if (cmd.equals("/search.do")) {
            action = new BookSearchAction("/view/list.jsp");
        }
        if (cmd.equals("/delete.do")) {
            action = new BookDeleteAction("/list.do");
        }
        if (cmd.equals("/login.do")) {
            action = new BookLoginAction("/list.do");
        }
        if (cmd.equals("/logout.do")) {
            action = new BookLogoutAction("/");
        }
        if (cmd.equals("/change.do")) {
            action = new BookPasswordAction("/view/login.jsp");
        }
        if (cmd.equals("/register.do")) {
            action = new BookRegisterAction("/view/login.jsp");
        }
        if (cmd.equals("/leave.do")) {
            action = new BookLeaveAction("/view/login.jsp");
        }

        // 4. 생성된 action 에게 일 시키기(서블릿(~Pro),이 해야했던 일)
        ActionForward af = null;
        try {
            af = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 5. 이동방식과 경로에 따라 움직이기
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
