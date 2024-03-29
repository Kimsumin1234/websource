package action;

import java.io.File;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardWriteAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // qna_board_write.jsp 에서 넘긴 값 가져오기
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");

        // 페이지 나누기 개념 추가 후 들어오는 값들
        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardDto insertDto = new BoardDto();
        insertDto.setName(name);
        insertDto.setTitle(title);
        insertDto.setContent(content);
        insertDto.setPassword(password);

        // 업로드 처리 (Part : servlet.http)
        // String attach = req.getParameter("attach"); 파일첨부는 req 가 못가져온다
        // File.separator : \\
        Part part = req.getPart("attach");
        String fileName = getFileName(part);
        String saveDir = "c:\\upload";

        if (!fileName.isEmpty()) {

            // 파일명이 중복일 경우 파일업로드를 안해주니 => 서버에 저장시 고유한 값을 더해서 다른 이름을 사용
            // UUID : 자바가 제공해주는 고유한 값 생성 (거의 중복이 안나옴)
            UUID uuid = UUID.randomUUID();
            // c:\\upload\\고유한값_파일명
            File uploadFile = new File(saveDir + File.separator + uuid + "_" + fileName);

            part.write(uploadFile.toString()); // 서버의 디스크에 파일저장
            insertDto.setAttach(uploadFile.getName());
        }
        System.out.println(insertDto);

        // 서비스 호출
        BoardService service = new BoardServiceImpl();
        boolean result = service.insert(insertDto);

        // 결과가 true 면 목록으로, false 면 qna_board_write.jsp
        if (!result) {
            path = "/view/qna_board_write.jsp" + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword=" + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword=" + keyword;
        }

        return new ActionForward(path, true);
    }

    // 파일 메소드
    private String getFileName(Part part) {
        // Content-Disposition: attachment; filename="filename.jpg" 이런식으로 넘어온다
        String header = part.getHeader("content-disposition");
        // ; 으로 분리
        String[] arr = header.split(";");
        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];
            // filename 을 찾아서 filename.jpg 만 추출하기
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return "";
    }

}
