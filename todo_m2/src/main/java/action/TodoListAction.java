package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoListAction implements Action {
    private String path;

    public TodoListAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // DB 연동은 service 가 담당
        TodoService service = new TodoServiceImpl();
        List<TodoDto> list = service.list();
        req.setAttribute("list", list);

        // redirect 가 false 이면 forward 로 이동
        return new ActionForward(path, false);
    }

}
