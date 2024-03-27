package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
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

        BoardService service = new BoardServiceImpl();
        boolean result = service.delete(dto);

        if (!result) {
            path = "/view/qna_board_pwdCheck.jsp?bno=" + dto.getBno();
        }

        return new ActionForward(path, true);
    }

}
