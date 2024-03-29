package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        int bno = Integer.parseInt(req.getParameter("bno"));
        String password = req.getParameter("password");
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setPassword(password);

        // 페이지 나누기 개념 추가 후 들어오는 값들
        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardService service = new BoardServiceImpl();

        // bno 이용해서 행 조회
        BoardDto dto2 = service.read(dto.getBno());
        // bno == re_ref : 원본글
        if (dto2.getBno() == dto2.getReRef()) {
            // 비밀번호 확인 후 일치한다면 deleteAll 호출
            if (service.pwdCheck(dto)) {
                service.deleteAll(dto2.getReRef());
                path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
            } else {
                path = "/view/qna_board_pwdCheck.jsp?bno=" + dto.getBno() + "&page=" + page + "&amount=" + amount
                        + "&criteria=" + criteria + "&keyword=" + keyword;
            }

        } else {
            // 페이지 나누기 개념 추가후 주소창에 요소 딸려 보내기
            if (!service.delete(dto)) {
                path = "/view/qna_board_pwdCheck.jsp?bno=" + dto.getBno() + "&page=" + page + "&amount=" + amount
                        + "&criteria=" + criteria + "&keyword=" + keyword;
            } else {
                path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
            }
        }

        return new ActionForward(path, true);
    }

}
