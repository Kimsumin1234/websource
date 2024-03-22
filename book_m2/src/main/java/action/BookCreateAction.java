package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookCreateAction implements Action {
    private String path;

    public BookCreateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 도서추가 화면에서 넘어오는 데이터 가져오기
        int code = Integer.parseInt(req.getParameter("code"));
        int price = Integer.parseInt(req.getParameter("price"));
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String description = req.getParameter("description");

        BookDto insertDto = new BookDto(code, title, writer, price, description);

        // 서비스 호출 create
        BookService service = new BookServiceImpl();
        boolean result = service.create(insertDto);

        // true : 다시 서블릿 /list.do 로 가니깐
        return new ActionForward(path, true);
    }

}
