package kea.dpang.qna.service;

import kea.dpang.qna.base.SuccessResponse;
import kea.dpang.qna.client.ItemServiceClient;
import kea.dpang.qna.client.UserServiceClient;
import kea.dpang.qna.client.dto.RequestItemServiceDto;
import kea.dpang.qna.dto.request.CreateQnaRequestDto;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.UpdateQnaRequestDto;
import kea.dpang.qna.dto.response.QnaDetailDto;
import kea.dpang.qna.dto.response.QnaDto;
import kea.dpang.qna.dto.response.UserDto;
import kea.dpang.qna.entity.Category;
import kea.dpang.qna.entity.Qna;
import kea.dpang.qna.entity.Status;
import kea.dpang.qna.exception.QnaNotFoundException;
import kea.dpang.qna.exception.FeignException;
import kea.dpang.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaRepository qnaRepository;

    private final UserServiceClient userServiceClient;
    private final ItemServiceClient itemServiceClient;

    @Override
    public void createQna(CreateQnaRequestDto request) {
        log.info("새로운 QnA를 생성합니다: {}", request);

        // 생성한 QnaDto 객체를 데이터베이스에 저장한다.
        qnaRepository.save(request.toQna());
    }

    @Override
    public void updateQna(Long id, UpdateQnaRequestDto request) {
        log.info("ID: {}에 해당하는 QnA를 업데이트합니다: {}", id, request);

        // 데이터베이스에서 ID에 해당하는 Qna 객체를 가져온다.
        //  가져온 Qna 객체의 정보를 request의 정보로 업데이트한다.
        qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id))
                .update(request);
    }

    @Override
    public void deleteQna(List<Long> ids) {
        log.info("다음 ID를 가진 QnA들을 삭제합니다: {}", ids);

        // 요청받은 ID 리스트를 순회하면서 각각의 QnA를 삭제한다.
        // 조회한 QnA를 데이터베이스에서 삭제한다.
        ids.forEach(qnaRepository::deleteById);
    }

    @Override
    public Page<QnaDto> getQnaList(Optional<Long> userId, Optional<Category> category, Optional<Status> status, Optional<Long> itemId, Optional<String> startDateString, Optional<String> endDateString, Pageable pageable) {
        log.info("사용자 ID: {}에 해당하는 QnA 목록을 조회합니다", userId.orElse(null));

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if(startDateString.isPresent()) startDate = LocalDate.parse(startDateString.get()).atStartOfDay();
        if(endDateString.isPresent()) endDate = LocalDate.parse(endDateString.get()).atStartOfDay().plusDays(1);

        // userId, category, status의 값이 없을 경우 null로 처리한다.
        return qnaRepository.findAllByUserIdAndCategoryAndStatus(
                        userId.orElse(null),
                        category.orElse(null),
                        itemId.orElse(null),
                        status.orElse(null),
                        startDate,
                        endDate,
                        pageable)
                .map(qna -> qna.toQnaDto(userServiceClient.getUser(qna.getAuthorId()).getBody().getData()));
    }

    @Override
    public QnaDetailDto getQna(Long id) {
        log.info("ID: {}에 해당하는 QnA를 조회합니다", id);

        // 데이터베이스에서 ID에 해당하는 QnA를 조회한다.
        // 조회한 QnA를 QnaDetailDto로 변환 및 반환한다.
        Qna qna = qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id));

        UserDto user;
        try {
            ResponseEntity<SuccessResponse<UserDto>> responseEntity = userServiceClient.getUser(qna.getAuthorId());
            user = responseEntity.getBody().getData();
        } catch (Exception e) {
            throw new FeignException("사용자 정보를 가져오는 중에 오류가 발생했습니다: ", e);
        }

        String itemName = null;
        if(qna.getItemId()!=null) {
            try {
                ResponseEntity<SuccessResponse<RequestItemServiceDto>> responseEntity = itemServiceClient.getItemName(qna.getItemId());
                itemName = responseEntity.getBody().getData().getName();
            } catch (Exception e) {
                throw new FeignException("상품 정보를 가져오는 중에 오류가 발생했습니다: ", e);
            }
        }

        return qna.toQnaDetailDto(user,itemName);
    }

    @Override
    public void updateAnswerQna(Long id, QnaAnswerRequest request) {
        log.info("ID: {}에 해당하는 QnA의 답변을 업데이트합니다: {}", id, request);

        // 데이터베이스에서 ID에 해당하는 QnA를 조회한다.
        // 조회한 QnA의 답변자 ID와 답변 내용을 업데이트한다.
        qnaRepository.findById(id)
                .orElseThrow(() -> new QnaNotFoundException(id))  // 해당하는 QnA가 없으면 예외를 발생시킨다.
                .updateAnswer(request.getResponderId(), request.getAnswer());
    }

}
