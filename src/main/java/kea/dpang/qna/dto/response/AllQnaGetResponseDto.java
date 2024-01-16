package kea.dpang.qna.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AllQnaGetResponseDto {
    private Long qna_id;
    private String category;
    private String title;
    private String status;
}
