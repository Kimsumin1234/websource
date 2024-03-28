package service;

import java.util.List;

import dto.BoardDto;
import dto.SearchDto;

public interface BoardService {
    List<BoardDto> list(SearchDto searchDto);

    boolean insert(BoardDto insertDto);

    BoardDto read(int bno);

    boolean update(BoardDto updateDto);

    boolean delete(BoardDto deleteDto);

    boolean reply(BoardDto replyDto);

    boolean updateCount(int bno);

    List<BoardDto> search(SearchDto searchDto);

    int getRows(String criteria, String keyword);
}
