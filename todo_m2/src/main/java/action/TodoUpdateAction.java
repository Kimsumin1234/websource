package action;

import javax.servlet.http.HttpServletRequest;

import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoUpdateAction implements Action {
    private String path;

    public TodoUpdateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String completed = req.getParameter("completed");
        String description = req.getParameter("description");
        String no = req.getParameter("no");

        TodoDto updateDto = new TodoDto();
        updateDto.setCompleted(Boolean.parseBoolean(completed));
        updateDto.setDescription(description);
        updateDto.setNo(Integer.parseInt(no));

        TodoService service = new TodoServiceImpl();
        boolean result = service.update(updateDto);

        return new ActionForward(path, true);
    }

}
