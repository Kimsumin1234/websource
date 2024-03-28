package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private SearchDto searchDto;
    private int total;

    // PageDto 안에 SearchDto 객체를 포함하고있다
    public PageDto(SearchDto searchDto, int total) {
        this.searchDto = searchDto;
        this.total = total;

        // 페이지 번호 매기기
        // endPage : 임시로 10개의 페이지를 지정
        // ex) 3페이지를 누르면 3/10 => 1 => 1 * 10 해서 endPage가 10페이지
        endPage = (int) (Math.ceil(searchDto.getPage() / 10.0)) * 10;
        startPage = endPage - 9;

        // realEnd : 실제 페이지
        int realEnd = (int) (Math.ceil((total / 1.0) / searchDto.getAmount()));

        // endPage 와 realEnd 비교 해서 값 대입
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        // prev 와 next 버튼
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}
