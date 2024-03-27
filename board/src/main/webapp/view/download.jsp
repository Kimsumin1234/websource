<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%

        // 클라이언트가 요청한 fileName 가져오기
        // 예시) 8663c15a-503e-40c9-99d1-983ca7cee585_1.png
        String fileName = request.getParameter("fileName");

        // 서버에 저장된 폴더를 경로로 지정
        String fDownloadPath = "c:\\upload";
        // 예시) c:\\upload\\8663c15a-503e-40c9-99d1-983ca7cee585_1.png
        String filePath = fDownloadPath + "\\" + fileName;

        // 하드디스크에 있는 파일 읽기
        FileInputStream in = new FileInputStream(filePath);

        // 브라우저가 응답할 때 읽어온 파일 보내기
        // MIME types : application/octet-stream (이거는 만능으로 사용할수있다 이외에도 여러가지 타입이 있다)
        response.setContentType("application/octet-stream");

        // 한글 안깨지게 하는 코드
        fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");

        // UUID 제거후 실제 파일명만 추출하는 작업
        int start = fileName.lastIndexOf("_");
        String oriName = fileName.substring(start + 1);

        // 다운로드 핵심코드 파일명을 제대로 추출해야한다
        response.setHeader("Content-Disposition", "attachment;filename=" + oriName);

        out.clear();
        out = pageContext.pushBody();

        // 다운로드 코드
        BufferedOutputStream buf = new BufferedOutputStream(response.getOutputStream());

        int numRead;
        byte b[] = new byte[4096];
        while ((numRead = in.read(b, 0, b.length)) != -1) {
            buf.write(b, 0, numRead);
        }
        buf.flush();
        buf.close();
        in.close();

%>