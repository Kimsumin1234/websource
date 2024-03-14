package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServletRequest : 사용자의 요청을 가져오는 객체 (사용자가 요청하면 필수로 있어야 하는 객체, 중요하다)
// HttpServletResponse : 사용자에게 응답할 때 사용하는 객체

// servlet 을 쉽게 접근하기 위해서 별칭을 사용 (별칭은 임의로 지정가능)
// 단, 별칭 중복 불가능
// 브라우저창 주소에 http://localhost:8080/web1/hello 로 접근가능
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // post 방식의 한글은 깨져서 브라우저 화면에 나온다
        // setCharacterEncoding("utf-8") : post 방식으로 넘어오는 한글을 인코딩 처리를 해줘야 한글이 안꺠진다
        req.setCharacterEncoding("utf-8");

        // 하나 가져오는경우
        // 가져오는 모든 데이터는 String 이다
        // ★★★ req.getParameter("form 요소명") ★★★
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        // 여러개 가져오는경우
        // value 가 checkbox 처럼 여러개인 경우 String[] 로 들어온다
        String dogs[] = req.getParameterValues("dog");

        // 응답할(보여주는) 페이지 설정
        // text/html;charset=utf-8 : html 형태로 응답할거다
        // out.print("<ul>"); : html 로 응답하니까 html 태그를 써준다
        // servlet 으로 응답하기가 불편하다
        // 그래서 jsp 를 주로 사용
        // servlet : 자바코드 작성은 편함, html css 작성은 불편함
        // jsp : 자바코드 작성은 불편함, html css 작성은 편함
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();

        // 하나 가져오는 경우
        out.print("<ul>");
        out.print("<li>id : " + id + "</li>");
        out.print("<li>name : " + name + "</li>");

        // 여러개 가져오는 경우
        // String[] 이니까 향상된 for문을 사용한다
        for (String dog : dogs) {
            out.print("<li>dog : " + dog + "</li>");
        }

        out.print("</ul>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // doPost() 호출 하면 doGet() 이 호출되는 상태
        // doGet() 만 작성하면 method="post",method="get" 동일하게 쓸수있다
        doGet(req, resp);
    }
}
