package kea.dpang.qna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QnaUpdateRequestDto {
    private Long item_id;
    private String title;
    private String category;
    private String question;
    private String attachment_url;
    private Boolean is_public;
}
