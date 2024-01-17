package kea.dpang.qna.service;

import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.QnaCreateRequestDto;
import kea.dpang.qna.dto.request.QnaUpdateRequestDto;
import kea.dpang.qna.dto.response.AllQnaGetResponseDto;
import kea.dpang.qna.dto.response.QnaGetResponseDto;

import java.util.List;

public interface QnaService {
    void createQna(QnaCreateRequestDto request);
    void updateQna(Long qnaId, QnaUpdateRequestDto request);
    void deleteQna(List<Long> request);
    List<AllQnaGetResponseDto> getAllQna();
    List<AllQnaGetResponseDto> getUserQna(Iterable<Long> request);
    QnaGetResponseDto getQna(Long request);
    void updateAnswerQna(Long qnaId, QnaAnswerRequest request);

}
