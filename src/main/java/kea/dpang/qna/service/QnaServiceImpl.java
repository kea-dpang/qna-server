package kea.dpang.qna.service;

import kea.dpang.qna.dto.request.QnaAnswerRequest;
import kea.dpang.qna.dto.request.QnaCreateRequestDto;
import kea.dpang.qna.dto.request.QnaUpdateRequestDto;
import kea.dpang.qna.dto.response.AllQnaGetResponseDto;
import kea.dpang.qna.dto.response.QnaGetResponseDto;
import kea.dpang.qna.entity.QnaEntity;
import kea.dpang.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService{
    private final QnaRepository qnaRepository;
    @Override
    public void createQna(QnaCreateRequestDto request) {
        QnaEntity qna = new QnaEntity(
                request.getUserID(),
                request.getTitle(),
                request.getInquiryType1(),
                request.getContents(),
                request.getAgree(),
                request.getItemId(),
                request.getImage()
        );
        qnaRepository.save(qna);
    }

    @Override
    public void updateQna(QnaUpdateRequestDto request) {
        QnaEntity qna = qnaRepository.findById(request.getQna_id()).get();
        qna.updateQnaEntity(
                request.getItem_id(),
                request.getTitle(),
                request.getCategory(),
                request.getQuestion(),
                request.getAttachment_url(),
                request.getIs_public()
                );
        qnaRepository.save(qna);
    }

    @Override
    public void deleteQna(List<Long> request) {
        for (Long id:request) {
            qnaRepository.deleteById(id);
        }
    }

    @Override
    public List<AllQnaGetResponseDto> getAllQna(){
        List<QnaEntity> qnaList = qnaRepository.findAll();
        List<AllQnaGetResponseDto> qnaReturn = new ArrayList<AllQnaGetResponseDto>();
        for (QnaEntity qna:qnaList){
            AllQnaGetResponseDto qnaGet = new AllQnaGetResponseDto(
                    qna.getId(),
                    qna.getCategory(),
                    qna.getTitle(),
                    qna.getState()
            );
            qnaReturn.add(qnaGet);
        }
        return qnaReturn;
    }

    @Override
    public QnaGetResponseDto getQna(Long request) {
        QnaEntity qna = qnaRepository.findById(request).get();
        return new QnaGetResponseDto(qna);
    }

    @Override
    public void updateAnswerQna(QnaAnswerRequest request){
        QnaEntity qna = qnaRepository.findById(request.getQna_id()).get();
        qna.updateAnswerQna(
                request.getResponder_id(),
                request.getAnswer()
        );
        qnaRepository.save(qna);
    }
}
