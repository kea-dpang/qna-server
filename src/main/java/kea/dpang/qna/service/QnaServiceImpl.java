package kea.dpang.qna.service;

import kea.dpang.qna.dto.request.CreateQnaRequestDto;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.UpdateQnaRequestDto;
import kea.dpang.qna.dto.response.QnaDetailDto;
import kea.dpang.qna.dto.response.QnaDto;
import kea.dpang.qna.entity.Qna;
import kea.dpang.qna.entity.Status;
import kea.dpang.qna.exception.QnaNotFoundException;
import kea.dpang.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaRepository qnaRepository;

    @Override
    public void createQna(CreateQnaRequestDto request) {
        // request의 정보를 이용해서 새로운 QnA 객체를 생성한다
        Qna qna = Qna.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .title(request.getTitle())
                .itemId(request.getItemId())
                .attachmentUrl(request.getImageUrl())
                .status(Status.PROCESSING)
                .build();

        // 생성한 QnA 객체를 데이터베이스에 저장한다.
        qnaRepository.save(qna);
    }

    @Override
    public void updateQna(Long id, UpdateQnaRequestDto request) {
        // 데이터베이스에서 ID에 해당하는 Qna 객체를 가져온다.
        Qna qna = qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id));

        //  가져온 Qna 객체의 정보를 request의 정보로 업데이트한다.
        qna.update(
                request.getTitle(),
                request.getCategory(),
                request.getQuestion(),
                request.getAttachmentUrl()
        );
    }

    @Override
    public void deleteQna(List<Long> ids) {
        // 요청받은 ID 리스트를 순회하면서 각각의 QnA를 삭제한다.
        for (Long id : ids) {

            // 데이터베이스에서 ID에 해당하는 QnA를 조회한다.
            Qna qna = qnaRepository.findById(id)
                    .orElseThrow(() -> new QnaNotFoundException(id)); // 해당하는 QnA가 없으면 예외를 발생시킨다.

            // 조회한 QnA를 데이터베이스에서 삭제한다.
            qnaRepository.delete(qna);
        }
    }

    @Override
    public Page<QnaDto> getQnaList(Optional<Long> userId, Pageable pageable) {
        // userId가 있으면 해당 사용자의 QnA를, 없으면 모든 QnA를 페이지네이션하여 조회한다.
        Page<Qna> qnaPage = userId
                .map(id -> qnaRepository.findByAuthorId(id, pageable)) // 사용자의 QnA 조회
                .orElse(qnaRepository.findAll(pageable)); // 모든 QnA 조회

        // 조회한 QnA를 Dto로 변환하고 반환한다.
        return qnaPage.map(Qna::toQnaDto);
    }

    @Override
    public QnaDetailDto getQna(Long id) {
        // 데이터베이스에서 ID에 해당하는 QnA를 조회한다.
        Qna qna = qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id));  // 해당하는 QnA가 없으면 예외를 발생시킨다.

        // 조회한 QnA를 QnaDetailDto로 변환 및 반환한다.
        return new QnaDetailDto(qna);
    }

    @Override
    public void updateAnswerQna(Long id, QnaAnswerRequest request) {
        // 데이터베이스에서 ID에 해당하는 QnA를 조회한다.
        Qna qna = qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id));  // 해당하는 QnA가 없으면 예외를 발생시킨다.

        // 조회한 QnA의 답변자 ID와 답변 내용을 업데이트한다.
        qna.answer(request.getResponderId(), request.getAnswer());
    }

}
