package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardSearchAction implements Action {
    private String path;

    public BoardSearchAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");
        SearchDto dto = new SearchDto();
        dto.setCriteria(criteria);
        dto.setKeyword(keyword);

        BoardService service = new BoardServiceImpl();
        List<BoardDto> list = service.search(dto);

        req.setAttribute("list", list);
        // 검색조건 이 남아있게하기
        req.setAttribute("search", dto);

        return new ActionForward(path, false);
    }

}
