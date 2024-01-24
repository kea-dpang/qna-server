package kea.dpang.qna.controller;


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
    public ResponseEntity<Void> createQna(@RequestBody CreateQnaRequestDto createQnaRequest) {
        qnaService.createQna(createQnaRequest);
        return ResponseEntity.ok().build();
    }

    // QnA 목록 조회 API
    @GetMapping
    public ResponseEntity<Page<QnaDto>> getQnaList(@RequestParam Optional<Long> userId, Pageable pageable) {
        Page<QnaDto> qnaDtoPage = qnaService.getQnaList(userId, pageable);
        return ResponseEntity.ok(qnaDtoPage);
    }

    // QnA 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<QnaDetailDto> getQna(@PathVariable Long id) {
        QnaDetailDto qnaDetailDto = qnaService.getQna(id);
        return ResponseEntity.ok(qnaDetailDto);
    }

    // QnA 답변 등록 API
    @PutMapping("/{id}/answer")
    public ResponseEntity<Void> answerQna(@PathVariable Long id, @RequestBody QnaAnswerRequest answerRequest) {
        qnaService.updateAnswerQna(id, answerRequest);
        return ResponseEntity.ok().build();
    }

    // QnA 정보 업데이트 API
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQna(@PathVariable Long id, @RequestBody UpdateQnaRequestDto updateQnaRequest) {
        qnaService.updateQna(id, updateQnaRequest);
        return ResponseEntity.ok().build();
    }

    // QnA 삭제 API
    @DeleteMapping
    public ResponseEntity<Void> deleteQna(@RequestBody List<Long> ids) {
        qnaService.deleteQna(ids);
        return ResponseEntity.ok().build();
    }
}
