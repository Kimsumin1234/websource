package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookLogoutAction implements Action {
    private String path;

    public BookLogoutAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        session.invalidate();

        return new ActionForward(path, true);
    }

}
