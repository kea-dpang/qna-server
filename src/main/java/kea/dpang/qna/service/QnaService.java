package kea.dpang.qna.service;

import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.QnaCreateRequestDto;
import kea.dpang.qna.dto.request.QnaUpdateRequestDto;
import kea.dpang.qna.dto.response.AllQnaGetResponseDto;
import kea.dpang.qna.dto.response.QnaGetResponseDto;

import java.util.List;

public interface QnaService {
    void createQna(QnaCreateRequestDto request);
    void updateQna(QnaUpdateRequestDto request);
    void deleteQna(List<Long> request);
    List<AllQnaGetResponseDto> getAllQna();
    QnaGetResponseDto getQna(Long request);
    void updateAnswerQna(QnaAnswerRequest request);

}
