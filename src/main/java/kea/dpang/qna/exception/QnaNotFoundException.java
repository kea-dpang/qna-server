package kea.dpang.qna.exception;

public class QnaNotFoundException extends RuntimeException {
    public QnaNotFoundException(Long id) {
        super("해당 ID를 가진 QnA를 찾을 수 없습니다: " + id);
    }
}
