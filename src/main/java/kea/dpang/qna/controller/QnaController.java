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
    @PostMapping("/onetooneqna")
    public void postQna(@ModelAttribute QnaCreateRequestDto request){
    }
    @PatchMapping("")
    public void updateQna(@ModelAttribute QnaUpdateRequestDto request){

    }
    @PatchMapping("")
    public void updateAnswerQna(@ModelAttribute QnaAnswerRequest request){

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
    public void deleteQna(@RequestParam)
}
