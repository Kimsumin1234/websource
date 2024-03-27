package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardUpdateAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 수정작업
        // NumberFormatException: Cannot parse null string 이 뜨면 Integer.parseInt 가 숫자로
        // 못바꾸는 경우 뜬다, 수정화면에 bno가 입력이 안되서 null 이 뜨기 때문에 Exception 이 뜸
        // qna_board_modify.jsp 에 input hidden 으로 bno 값을 넣어준다
        int bno = Integer.parseInt(req.getParameter("bno"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setPassword(password);

        BoardService service = new BoardServiceImpl();
        boolean result = service.update(dto);

        if (!result) {
            path = "/qModify.do?bno=" + dto.getBno();
        } else {
            path += "?bno=" + dto.getBno();
        }

        return new ActionForward(path, true);
    }

}
