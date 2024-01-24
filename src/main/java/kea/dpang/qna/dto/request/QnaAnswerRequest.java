package kea.dpang.qna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QnaAnswerRequest {
    private Long responderId;
    private String answer;
}
