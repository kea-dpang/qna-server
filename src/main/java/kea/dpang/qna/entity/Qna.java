package kea.dpang.qna.entity;

import jakarta.persistence.*;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.UpdateQnaRequestDto;
import kea.dpang.qna.dto.response.QnaDetailDto;
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
     * @param dto           업데이트할 내용을 가진 DTO
     */
    public void update(UpdateQnaRequestDto dto) {
        this.title = dto.getTitle();
        this.category = dto.getCategory();
        this.contents = dto.getQuestion();
        this.attachmentUrl = dto.getAttachmentUrl();
    }

    /**
     * QnA에 답변을 등록하고, 상태를 '완료'로 변경합니다.
     *
     * @param responderId 답변을 작성한 관리자의 ID
     * @param answer      답변 내용
     */
    public void updateAnswer(Long responderId, String answer) {
        this.responderId = responderId;
        this.answer = answer;
        this.status = Status.COMPLETED;
    }

    /**
     * QnA를 QnaDto로 변환합니다.
     *
     * @return 변환된 QnaDto
     */
    public QnaDto toQnaDto() {
        return QnaDto.builder()
                .qnaId(this.id)
                .category(this.category)
                .title(this.title)
                .status(this.status)
                .createdAt(this.createdAt)
                .build();
    }

    /**
     * QnA를 QnaDetailDto로 변환합니다.
     *
     * @return 변환된 QnaDetailDto
     */
    public QnaDetailDto toQnaDetailDto() {
        return QnaDetailDto.builder()
                .qnaId(this.id)
                .authorId(this.authorId)
                .responderId(this.responderId)
                .itemId(this.itemId)
                .title(this.title)
                .category(this.category)
                .contents(this.contents)
                .attachmentUrl(this.attachmentUrl)
                .status(this.status)
                .answer(this.answer)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}