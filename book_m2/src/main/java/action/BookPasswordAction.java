package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.ChangeDto;
import dto.MemberDto;
import service.BookService;
import service.BookServiceImpl;

public class BookPasswordAction implements Action {
    private String path;

    public BookPasswordAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 1. pwdChange.jsp 에서 넘긴값 가져오기
        String password = req.getParameter("password");
        String newPassword = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirm-password");

        // 2. 아이디와 현재 비밀번호를 가지고 확인 (서비스 메소드 호출)
        HttpSession session = req.getSession();
        // - 세션에 담긴 로그인 정보 가져오기
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        // - 변경해서 담을 객체 생성
        ChangeDto changeDto = new ChangeDto();
        changeDto.setUserid(loginDto.getUserid());
        changeDto.setNewPassword(newPassword);
        changeDto.setConfirmPassword(confirmPassword);

        // - newPasswordEqualsConfirmPassword()-새비밀번호랑 새비밀번호 확인 메소드 (스크립트로 해도됨)
        // newPassword.equals(confirmPassword)
        if (changeDto.newPasswordEqualsConfirmPassword()) {
            // - 세션애 담긴 아이디와 사용자가 입력한 비밀번호를 가지고 있는 객체
            MemberDto input = new MemberDto();
            input.setUserid(loginDto.getUserid());
            input.setPassword(password);

            BookService service = new BookServiceImpl();
            if (service.login(input) == null) {
                // 현재 비밀번호가 틀린 경우
                path = "/view/pwdChange.jsp";

            } else {
                // 사용자가 존재한다면 비밀번호 변경 서비스 메소드 호출
                if (service.passwordChange(changeDto)) {
                    // 3. 변경 완료 여부에 따라 true 이면 세션 제거 후 로그인 페이지로 이동
                    session.invalidate();
                } else {
                    // - false 면 pwdChange.jsp 로 이동
                    path = "/view/pwdChange.jsp";
                }
            }

        } else {
            path = "/view/pwdChange.jsp";
        }

        return new ActionForward(path, false);
    }

}
