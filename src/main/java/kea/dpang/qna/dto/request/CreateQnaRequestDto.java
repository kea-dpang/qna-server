package kea.dpang.qna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import kea.dpang.qna.entity.Category;

@Getter
@AllArgsConstructor
public class CreateQnaRequestDto {
    private final Long userId; // 사용자 ID
    private Category category; // 문의 카테고리
    private Long itemId; // 상품 ID
    private String title; // 문의 제목
    private String content; // 문의 내용
    private String imageUrl; // 첨부 이미지 URL
}