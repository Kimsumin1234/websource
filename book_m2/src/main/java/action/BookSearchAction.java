package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookSearchAction implements Action {
    private String path;

    public BookSearchAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        BookService service = new BookServiceImpl();
        List<BookDto> list = service.searchListAll(criteria, keyword);

        req.setAttribute("list", list);

        return new ActionForward(path, false);
    }

}
