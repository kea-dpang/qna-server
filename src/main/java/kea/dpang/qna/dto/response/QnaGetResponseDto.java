package kea.dpang.qna.dto.response;

import kea.dpang.qna.entity.QnaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
public class QnaGetResponseDto {
    private Long qna_id;
    private Long author_id;
    private Long responder_id;
    private Long item_id;
    private String title;
    private String category;
    private String question;
    private String attachment_url;
    private String state;
    private String answer;
    private Boolean is_public;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
