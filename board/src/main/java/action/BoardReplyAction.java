package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReplyAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        int bno = Integer.parseInt(req.getParameter("bno"));
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");
        int reRef = Integer.parseInt(req.getParameter("reRef"));
        int reSeq = Integer.parseInt(req.getParameter("reSeq"));
        int reLev = Integer.parseInt(req.getParameter("reLev"));
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setName(name);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setPassword(password);
        dto.setReRef(reRef);
        dto.setReSeq(reSeq);
        dto.setReLev(reLev);

        BoardService service = new BoardServiceImpl();
        boolean result = service.reply(dto);

        // 성공시 리스트 보여주기, 실패시 답변화면으로
        if (!result) {
            path = "/qReplyView.do?bno=" + dto.getBno();
        }

        return new ActionForward(path, true);
    }

}
