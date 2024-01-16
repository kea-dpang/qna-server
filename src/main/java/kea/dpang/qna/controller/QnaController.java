package kea.dpang.qna.controller;


import jakarta.websocket.server.PathParam;
import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.QnaCreateRequestDto;
import kea.dpang.qna.dto.request.QnaUpdateRequestDto;
import kea.dpang.qna.dto.response.AllQnaGetResponseDto;
import kea.dpang.qna.dto.response.QnaGetResponseDto;
import kea.dpang.qna.service.QnaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class QnaController {
    private final QnaServiceImpl qnaService;
    @PostMapping("")
    public void postQna(@RequestBody QnaCreateRequestDto request){
        qnaService.createQna(request);
    }
    @PatchMapping("")
    public void updateQna(@RequestBody QnaUpdateRequestDto request){
        qnaService.updateQna(request);
    }
    @PatchMapping("")
    public void updateAnswerQna(@RequestBody QnaAnswerRequest request){
        qnaService.updateAnswerQna(request);
    }
    @GetMapping("")
    public List<AllQnaGetResponseDto> getAllQna(){
        return qnaService.getAllQna();
    }
    @GetMapping("/{qnaId}")
    public QnaGetResponseDto getQna(@PathParam("qnaId") Long qnaId){
        return qnaService.getQna(qnaId);
    }
    @DeleteMapping("")
    public void deleteQna(@RequestParam List<Long> ids){
        qnaService.deleteQna(ids);
    }
}
