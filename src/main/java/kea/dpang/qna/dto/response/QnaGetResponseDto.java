package kea.dpang.qna.dto.response;

import kea.dpang.qna.entity.QnaEntity;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
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

    public QnaGetResponseDto(QnaEntity qna) {
        this.qna_id = qna.getId();
        this.author_id = qna.getAuthor_id();
        this.responder_id = qna.getResponder_id();
        this.item_id = qna.getItem_id();
        this.title = qna.getTitle();
        this.category = qna.getCategory();
        this.question = qna.getQuestion();
        this.attachment_url = qna.getAttachment_url();
        this.state = qna.getState();
        this.answer = qna.getAnswer();
        this.is_public = qna.getIs_public();
        this.created_at = qna.getCreated_at();
        this.updated_at = qna.getUpdated_at();
    }
}
