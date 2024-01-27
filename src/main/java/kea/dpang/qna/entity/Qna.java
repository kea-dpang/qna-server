package kea.dpang.qna.entity;

import jakarta.persistence.*;
import kea.dpang.qna.dto.response.QnaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id")
    private Long id; // 문의 ID

    @Column(name = "author_id")
    private Long authorId; // 문의 작성자 ID

    @Column(name = "responder_id")
    private Long responderId; // 답변자 ID

    @Column(name = "item_id")
    private Long itemId; // 상품 ID

    @Column(name = "title")
    private String title; // 문의 제목

    @Enumerated(EnumType.STRING)
    private Category category; // 문의 카테고리

    @Column
    private String contents; // 문의 내용

    @Column(name = "attachment_url")
    private String attachmentUrl; // 첨부 사진 링크

    @Enumerated(EnumType.STRING)
    private Status status; // 문의 상태

    @Column
    private String answer; // 문의 답변

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 생성 날짜

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 변경 날짜

    /**
     * QnA의 정보를 업데이트합니다.
     *
     * @param title         제목
     * @param category      카테고리
     * @param contents      내용
     * @param attachmentUrl 첨부파일 URL
     */
    public void update(String title, Category category, String contents, String attachmentUrl) {
        this.title = title;
        this.category = category;
        this.contents = contents;
        this.attachmentUrl = attachmentUrl;
    }

    /**
     * QnA에 답변을 등록하고, 상태를 '완료'로 변경합니다.
     *
     * @param responderId 답변을 작성한 관리자의 ID
     * @param answer      답변 내용
     */
    public void answer(Long responderId, String answer) {
        this.responderId = responderId;
        this.answer = answer;
        this.status = Status.COMPLETED;
    }
}