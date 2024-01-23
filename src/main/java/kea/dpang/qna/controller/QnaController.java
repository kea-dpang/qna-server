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
    @GetMapping("/gatewaytest")
    public String gatewaytest(@RequestHeader("token") String token){
        return token;
    }
    @PostMapping("/onetooneqna")
    public void postQna(@RequestBody QnaCreateRequestDto request){
        qnaService.createQna(request);
    }
    @PatchMapping("/{postId}")
    public void updateQna(@PathParam("postId") Long postId, @RequestBody QnaUpdateRequestDto request){
        qnaService.updateQna(postId, request);
    }
    @PatchMapping("/{postId}/answer")
    public void updateAnswerQna(@PathParam("postId") Long postId, @RequestBody QnaAnswerRequest request){
        qnaService.updateAnswerQna(postId, request);
    }
    @GetMapping("/")
    public List<AllQnaGetResponseDto> getAllQna(){
        return qnaService.getAllQna();
    }
    @GetMapping("/{userId}/customerqna")
    public List<AllQnaGetResponseDto> getUserQna(@PathParam("userId") Iterable<Long> userId ){
        return qnaService.getUserQna(userId);
    }
    @GetMapping("/{qnaId}")
    public QnaGetResponseDto getQna(@PathParam("qnaId") Long qnaId){
        return qnaService.getQna(qnaId);
    }
    @DeleteMapping("/")
    public void deleteQna(@RequestParam List<Long> ids){
        qnaService.deleteQna(ids);
    }
}
