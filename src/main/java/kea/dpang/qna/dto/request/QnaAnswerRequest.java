package kea.dpang.qna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QnaAnswerRequest {
    private Long responder_id;
    private String answer;
}
