package action;

import javax.servlet.http.HttpServletRequest;

import dao.BookDao;
import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookReadAction implements Action {
    private String path;

    public BookReadAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        int code = Integer.parseInt(req.getParameter("code"));

        BookService service = new BookServiceImpl();
        BookDto dto = service.read(code);

        req.setAttribute("dto", dto);

        return new ActionForward(path, false);
    }

}
