package kea.dpang.qna.dto.response;

import kea.dpang.qna.entity.Category;
import kea.dpang.qna.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * QnA의 상세한 정보를 담는 DTO 클래스입니다.
 */
@Builder
@AllArgsConstructor
public class QnaDetailDto {
    private Long qnaId;  // QnA의 ID
    private Long authorId;  // QnA를 작성한 사용자의 ID
    private Long responderId;  // QnA에 답변한 관리자의 ID
    private Long itemId;  // QnA와 관련된 아이템의 ID
    private String title;  // QnA의 제목
    private Category category;  // QnA의 카테고리
    private String contents;  // QnA의 질문 내용
    private String attachmentUrl;  // QnA에 첨부된 파일의 URL
    private Status status;  // QnA의 상태
    private String answer;  // QnA의 답변 내용
    private LocalDateTime createdAt;  // QnA가 생성된 시각
    private LocalDateTime updatedAt;  // QnA가 마지막으로 업데이트된 시각
}
