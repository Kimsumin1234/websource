package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import service.BookService;
import service.BookServiceImpl;

public class BookLeaveAction implements Action {
    private String path;

    public BookLeaveAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");

        MemberDto leaveDto = new MemberDto();
        leaveDto.setUserid(loginDto.getUserid());
        leaveDto.setPassword(password);

        BookService service = new BookServiceImpl();
        boolean result = service.leave(leaveDto);

        if (result) {
            // 탈퇴 시 기존 세션 제거
            session.invalidate();
        } else {
            path = "/view/leave.jsp";
        }

        return new ActionForward(path, true);
    }

}
