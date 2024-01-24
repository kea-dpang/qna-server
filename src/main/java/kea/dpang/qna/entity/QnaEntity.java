package kea.dpang.qna.entity;

import jakarta.persistence.*;
import kea.dpang.qna.dto.request.QnaCreateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class QnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long author_id;

    @Column
    private Long responder_id;

    @Column
    private Long item_id;

    @Column
    private String title;

    @Column
    private String category;

    @Column
    private String question;

    @Column
    private String attachment_url;

    @Column
    private String state;

    @Column
    private String answer;

    @Column
    private Boolean is_public;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @Builder
    public QnaEntity(Long author_id, Long responder_id, Long item_id, String title, String category, String question, String attachment_url, String state, String answer, Boolean is_public) {
        this.author_id = author_id;
        this.responder_id = responder_id;
        this.item_id = item_id;
        this.title = title;
        this.category = category;
        this.question = question;
        this.attachment_url = attachment_url;
        this.state = state;
        this.answer = answer;
        this.is_public = is_public;
    }

    public void updateQnaEntity(Long item_id, String title, String category, String question, String attachment_url, Boolean is_public) {
        this.item_id = item_id;
        this.title = title;
        this.category = category;
        this.question = question;
        this.attachment_url = attachment_url;
        this.is_public = is_public;
    }

    public void updateAnswerQna(Long responder_id, String answer){
        this.responder_id = responder_id;
        this.answer = answer;
        this.state = "2";
    }
}
