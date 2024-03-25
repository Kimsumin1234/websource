package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import service.BookService;
import service.BookServiceImpl;

public class BookLoginAction implements Action {
    private String path;

    public BookLoginAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // userid, password 가져오기
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");

        MemberDto dto = new MemberDto();
        dto.setUserid(userid);
        dto.setPassword(password);

        // 서비스 호출
        BookService service = new BookServiceImpl();
        MemberDto loginDto = service.login(dto);

        // true 로 결과를 받았다면 받은 정보를 session 에 저장
        if (loginDto != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loginDto", loginDto);
        } else {
            path = "/view/login.jsp";
        }
        // sendRedirect, list 목록 페이지로 이동
        return new ActionForward(path, true);
    }

}
