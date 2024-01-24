package kea.dpang.qna.controller;


import io.swagger.v3.oas.annotations.Operation;
import kea.dpang.qna.base.BaseResponse;
import kea.dpang.qna.base.SuccessResponse;
import kea.dpang.qna.dto.request.CreateQnaRequestDto;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.UpdateQnaRequestDto;
import kea.dpang.qna.dto.response.QnaDetailDto;
import kea.dpang.qna.dto.response.QnaDto;
import kea.dpang.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qna")
public class QnaController {

    private final QnaService qnaService;

    // QnA 생성 API
    @PostMapping
    @Operation(summary = "QnA 생성", description = "QnA를 생성합니다.")
    public ResponseEntity<BaseResponse> createQna(@RequestBody CreateQnaRequestDto createQnaRequest) {
        qnaService.createQna(createQnaRequest);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 생성이 완료되었습니다."));
    }

    // QnA 목록 조회 API
    @GetMapping
    @Operation(summary = "QnA 목록 조회", description = "userId가 있으면 해당 사용자의 QnA를, 없으면 모든 QnA를 페이지네이션하여 조회합니다.")
    public ResponseEntity<SuccessResponse<Page<QnaDto>>> getQnaList(@RequestParam Optional<Long> userId, Pageable pageable) {
        Page<QnaDto> qnaDtoPage = qnaService.getQnaList(userId, pageable);
        return ResponseEntity.ok(new SuccessResponse<>(200, "QnA 목록 조회가 완료되었습니다.", qnaDtoPage));
    }

    // QnA 조회 API
    @GetMapping("/{id}")
    @Operation(summary = "QnA 조회", description = "ID에 해당하는 QnA를 조회합니다.")
    public ResponseEntity<SuccessResponse<QnaDetailDto>> getQna(@PathVariable Long id) {
        QnaDetailDto qnaDetailDto = qnaService.getQna(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "QnA 조회가 완료되었습니다.", qnaDetailDto));
    }

    // QnA 답변 등록 API
    @PutMapping("/{id}/answer")
    @Operation(summary = "QnA 답변 등록", description = "ID에 해당하는 QnA에 답변을 등록합니다.")
    public ResponseEntity<BaseResponse> answerQna(@PathVariable Long id, @RequestBody QnaAnswerRequest answerRequest) {
        qnaService.updateAnswerQna(id, answerRequest);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 답변 등록이 완료되었습니다."));
    }

    // QnA 정보 업데이트 API
    @PutMapping("/{id}")
    @Operation(summary = "QnA 정보 업데이트", description = "ID에 해당하는 QnA의 정보를 업데이트합니다.")
    public ResponseEntity<BaseResponse> updateQna(@PathVariable Long id, @RequestBody UpdateQnaRequestDto updateQnaRequest) {
        qnaService.updateQna(id, updateQnaRequest);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 정보 업데이트가 완료되었습니다."));
    }

    // QnA 삭제 API
    @DeleteMapping
    @Operation(summary = "QnA 삭제", description = "ID에 해당하는 QnA를 삭제합니다.")
    public ResponseEntity<BaseResponse> deleteQna(@RequestBody List<Long> ids) {
        qnaService.deleteQna(ids);
        return ResponseEntity.ok(new BaseResponse(200, "QnA 삭제가 완료되었습니다."));
    }
}
