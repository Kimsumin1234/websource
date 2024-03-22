package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookUpdateAction implements Action {
    private String path;

    public BookUpdateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        int code = Integer.parseInt(req.getParameter("code"));
        int price = Integer.parseInt(req.getParameter("price"));
        BookDto dto = new BookDto();
        dto.setCode(code);
        dto.setPrice(price);

        // 수정이 잘됬으면 /read.do 화면으로 가기 안되면 다시 수정화면 보여주기
        BookService service = new BookServiceImpl();
        boolean result = service.update(dto);

        if (result) {
            path += "?code=" + dto.getCode();
        } else {
            path = "/view/modify.jsp";
        }

        // setAttribute()
        // 이방법을 사용하면 /update.do 에 사용된 code 가 /read.do 까지 사용되서 두개 클래스가 연결이되버린다
        // 지금은 간단한 작업이라 큰 문제가 없지만 나중에 프로젝트가 길어지면 한개 한개 끊어서 작업하는게 좋기때문에
        // 사용자 입력값 같은거는 연쇄작용이 생기게 하지 않는게 좋다
        // path = "?code=" + dto.getCode();
        // req.setAttribute("dto", dto);
        // if (!result)
        // path = "/view/modify.jsp";

        // 수정 내용 보여주기 read.do 화면으로 띄우기
        return new ActionForward(path, true);
    }

}
