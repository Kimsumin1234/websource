package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.PageDto;
import dto.SearchDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardListAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 페이지 개념 추가
        int page = Integer.parseInt(req.getParameter("page"));
        int amount = Integer.parseInt(req.getParameter("amount"));

        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        SearchDto searchDto = new SearchDto(page, amount, criteria, keyword);

        BoardService service = new BoardServiceImpl();

        // 페이지 번호
        PageDto pageDto = new PageDto(searchDto, service.getRows(criteria, keyword));

        List<BoardDto> list = service.list(searchDto);

        req.setAttribute("list", list);

        // searchDto 포함되서 검색조건이 남을수 있다
        req.setAttribute("pageDto", pageDto);

        return new ActionForward(path, false);
    }

}
