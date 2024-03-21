package service;

import java.util.List;

import dto.TodoDto;

// DAO 의 CRUD 메소드를 호출 하는 클래스 Service
// 인터페이스니까 메소드만 넣는다 (메소드 이름은 임의로 변경가능)
public interface TodoService {
    List<TodoDto> list();

    TodoDto getRow(String no);

    boolean insert(TodoDto insertDto);

    boolean update(TodoDto insertDto);

    boolean delete(String no);
}
