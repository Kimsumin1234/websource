package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 가져오는 데이터 한글처리
        req.setCharacterEncoding("utf-8");
        // 사용자가 전송한 데이터 가져오기
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        // DB 작업완료 후 담긴 객체라고 가정한다
        MemberDTO dto = new MemberDTO(userId, password, name);
        // 세션 담기
        HttpSession session = req.getSession();
        session.setAttribute("loginDto", dto);

        // 세션에 담았을경우 sendRedirect() 과 짝꿍으로 사용한다
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
