package kea.dpang.qna.dto.request;

import kea.dpang.qna.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateQnaRequestDto {
    private Long itemId;
    private String title;
    private Category category;
    private String question;
    private String attachmentUrl;
}
