package kea.dpang.qna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class AllQnaGetResponseDto {
    private Long qna_id;
    private String category;
    private String title;
    private String status;
    private LocalDateTime created_at;
}
