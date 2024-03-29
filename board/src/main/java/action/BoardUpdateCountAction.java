package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardUpdateCountAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 조회수 업데이트
        int bno = Integer.parseInt(req.getParameter("bno"));

        // 페이지 나누기 개념 추가 후 들어오는 값들
        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");

        // 한글일 경우 get 방식 으로 넘어오면 한글이 깨진다 그래서 인코딩을 해줘야함
        // 검색한후 read를 하러 들어가는데
        // 주소줄에 한글이 깨져서 들어와서 인코딩 작업을함
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardService service = new BoardServiceImpl();

        service.updateCount(bno);

        // bno 는 조회수 업데이트할때 필요한것 나머지 4개는 페이지 나누기 개념에 필요한것
        path += "?bno=" + bno + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;

        return new ActionForward(path, true);
    }

}
