package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReadAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        int bno = Integer.parseInt(req.getParameter("bno"));

        BoardService service = new BoardServiceImpl();

        // 조회수 업데이트
        // 이렇게 같이 사용하면 새로고침 할때마다 조회수가 올라가는 문제가 발생
        // 이러한 문제를 해결하기 위해 조회수 업데이트 action 을 따로 만든다
        // service.updateCount(bno);

        BoardDto dto = service.read(bno);

        req.setAttribute("dto", dto);

        return new ActionForward(path, false);
    }

}
