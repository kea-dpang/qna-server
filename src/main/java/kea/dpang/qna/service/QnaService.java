package kea.dpang.qna.service;

import kea.dpang.qna.dto.request.CreateQnaRequestDto;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.UpdateQnaRequestDto;
import kea.dpang.qna.dto.response.QnaDetailDto;
import kea.dpang.qna.dto.response.QnaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QnaService {

    /**
     * QnA를 생성합니다.
     *
     * @param request QnaDto 생성 요청 정보
     */
    void createQna(CreateQnaRequestDto request);

    /**
     * QnA를 업데이트합니다.
     *
     * @param id      업데이트할 QnA의 ID
     * @param request QnaDto 업데이트 요청 정보
     */
    void updateQna(Long id, UpdateQnaRequestDto request);

    /**
     * QnA를 삭제합니다.
     *
     * @param request 삭제할 QnA의 ID 목록
     */
    void deleteQna(List<Long> request);

    /**
     * QnA를 페이지네이션하여 조회합니다. userId가 있으면 해당 사용자의 QnA를, 없으면 모든 QnA를 조회합니다.
     *
     * @param userId   조회할 사용자의 ID
     * @param pageable 페이지네이션 정보
     * @return 페이지네이션된 QnaDto 정보
     */
    Page<QnaDto> getQnaList(Optional<Long> userId, Pageable pageable);

    /**
     * 특정 QnA를 조회합니다.
     *
     * @param id 조회할 QnA의 ID
     * @return QnaDto 정보
     */
    QnaDetailDto getQna(Long id);

    /**
     * QnA의 답변을 업데이트합니다.
     *
     * @param id      업데이트할 QnA의 ID
     * @param request QnaDto 답변 업데이트 요청 정보
     */
    void updateAnswerQna(Long id, QnaAnswerRequest request);
}
