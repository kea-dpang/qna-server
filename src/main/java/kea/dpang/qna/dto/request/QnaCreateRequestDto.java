package kea.dpang.qna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QnaCreateRequestDto {
    private final Long userID;
    private String inquiryType1;
    private Long itemId;
    private String title;
    private String contents;
    private String image;
    private Boolean agree;
}