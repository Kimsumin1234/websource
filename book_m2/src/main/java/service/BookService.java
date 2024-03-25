package service;

import java.util.List;

import dto.BookDto;
import dto.ChangeDto;
import dto.MemberDto;

public interface BookService {
    // DAO 메소드 호출
    // CRUD - 조회,검색,삽입,삭제,수정 메소드 적는다 (메소드명은 임의로 지정가능)
    BookDto read(int code);

    List<BookDto> listAll();

    List<BookDto> searchListAll(String criteria, String keyword);

    boolean create(BookDto insertDto);

    boolean update(BookDto insertDto);

    boolean delete(int code);

    // 로그인 작업들 메소드 호출
    MemberDto login(MemberDto loginDto);

    boolean passwordChange(ChangeDto changeDto);

    boolean register(MemberDto insertDto);

    boolean leave(MemberDto leaveDto);

}
