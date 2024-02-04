package kea.dpang.qna.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kea.dpang.qna.base.BaseResponse;
import kea.dpang.qna.base.SuccessResponse;
import kea.dpang.qna.dto.DeleteQna;
import kea.dpang.qna.dto.request.CreateQnaRequestDto;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.UpdateQnaRequestDto;
import kea.dpang.qna.dto.response.QnaDetailDto;
import kea.dpang.qna.dto.response.QnaDto;
import kea.dpang.qna.entity.Category;
import kea.dpang.qna.entity.Status;
import kea.dpang.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qna")
public class QnaController {

    private final QnaService qnaService;

    @PostMapping
    @Operation(summary = "QnA 생성", description = "QnA를 생성합니다.")
    public ResponseEntity<BaseResponse> createQna(
            @RequestBody @Parameter(description = "QnA 생성 정보") CreateQnaRequestDto createQnaRequest
    ) {
        qnaService.createQna(createQnaRequest);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 생성이 완료되었습니다."));
    }

    @GetMapping
    @Operation(summary = "QnA 목록 조회", description = "userId가 있으면 해당 사용자의 QnA를, 없으면 모든 QnA를 페이지네이션하여 조회합니다. 카테고리가 ITEM_INQUIRY이고 상품 ID가 있다면 해당 상품의 상품 문의 목록을 조회합니다.")
    public ResponseEntity<SuccessResponse<Page<QnaDto>>> getQnaList(
            @RequestParam @Parameter(description = "사용자 ID") Optional<Long> userId,
            @RequestParam @Parameter(description = "카테고리") Optional<Category> category,
            @RequestParam @Parameter(description = "상품 ID") Optional<Long> itemId,
            @RequestParam @Parameter(description = "상태") Optional<Status> status,
            @RequestParam @Parameter(description = "검색 시작 날짜") LocalDate startDate,
            @RequestParam @Parameter(description = "검색 종료 날짜") LocalDate endDate,
            Pageable pageable
    ) {
        Page<QnaDto> qnaDtoPage = qnaService.getQnaList(userId, category, status,itemId, startDate, endDate, pageable);
        return ResponseEntity.ok(new SuccessResponse<>(200, "QnA 목록 조회가 완료되었습니다.", qnaDtoPage));
    }

    @GetMapping("/{id}")
    @Operation(summary = "QnA 조회", description = "ID에 해당하는 QnA를 조회합니다.")
    public ResponseEntity<SuccessResponse<QnaDetailDto>> getQna(
            @PathVariable @Parameter(description = "QnA ID") Long id
    ) {
        QnaDetailDto qnaDetailDto = qnaService.getQna(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "QnA 조회가 완료되었습니다.", qnaDetailDto));
    }

    @PutMapping("/{id}/answer")
    @Operation(summary = "QnA 답변 등록", description = "ID에 해당하는 QnA에 답변을 등록합니다.")
    public ResponseEntity<BaseResponse> answerQna(
            @PathVariable @Parameter(description = "QnA ID") Long id,
            @RequestBody @Parameter(description = "답변 정보") QnaAnswerRequest answerRequest
    ) {
        qnaService.updateAnswerQna(id, answerRequest);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 답변 등록이 완료되었습니다."));
    }

    @PutMapping("/{id}")
    @Operation(summary = "QnA 정보 업데이트", description = "ID에 해당하는 QnA의 정보를 업데이트합니다.")
    public ResponseEntity<BaseResponse> updateQna(
            @PathVariable @Parameter(description = "QnA ID") Long id,
            @RequestBody @Parameter(description = "업데이트 정보") UpdateQnaRequestDto updateQnaRequest
    ) {
        qnaService.updateQna(id, updateQnaRequest);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 정보 업데이트가 완료되었습니다."));
    }

    @DeleteMapping
    @Operation(summary = "QnA 삭제", description = "ID에 해당하는 QnA를 삭제합니다.")
    public ResponseEntity<BaseResponse> deleteQna(
            @RequestBody @Parameter(description = "삭제할 QnA ID 리스트") DeleteQna deleteQna
            ) {
        qnaService.deleteQna(deleteQna.getDeleteIds());
        return ResponseEntity.ok(new BaseResponse(200, "QnA 삭제가 완료되었습니다."));
    }
}