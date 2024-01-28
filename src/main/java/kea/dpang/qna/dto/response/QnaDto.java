package kea.dpang.qna.dto.response;

import kea.dpang.qna.entity.Category;
import kea.dpang.qna.entity.Qna;
import kea.dpang.qna.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * QnA의 간략한 정보를 담는 DTO 클래스
 */
@Data
@Builder
@AllArgsConstructor
public class QnaDto {
    private Long qnaId;  // QnA의 ID
    private Category category;  // QnA의 카테고리
    private String title;  // QnA의 제목
    private Status status;  // QnA의 상태
    private LocalDateTime createdAt;  // QnA가 생성된 시각

    public QnaDto(Qna qna) {
        this.qnaId = qna.getId();
        this.category = qna.getCategory();
        this.title = qna.getTitle();
        this.status = qna.getStatus();
        this.createdAt = qna.getCreatedAt();
    }
}
