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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
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
    public void updateQna(Long qnaId, QnaUpdateRequestDto request) {
        QnaEntity qna = qnaRepository.findById(qnaId).get();
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
        List<AllQnaGetResponseDto> qnaReturn = new ArrayList<>();
        for (QnaEntity qna:qnaList){
            AllQnaGetResponseDto qnaGet = new AllQnaGetResponseDto(
                    qna.getId(),
                    qna.getCategory(),
                    qna.getTitle(),
                    qna.getState(),
                    qna.getCreated_at()
            );
            qnaReturn.add(qnaGet);
        }
        return qnaReturn;
    }
    @Override
    public List<AllQnaGetResponseDto> getUserQna(Iterable<Long> request){
        List<QnaEntity> qnaList = qnaRepository.findAllById(request);
        List<AllQnaGetResponseDto> qnaReturn = new ArrayList<>();
        for (QnaEntity qna:qnaList){
            AllQnaGetResponseDto qnaGet = new AllQnaGetResponseDto(
                    qna.getId(),
                    qna.getCategory(),
                    qna.getTitle(),
                    qna.getState(),
                    qna.getCreated_at()
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
    public void updateAnswerQna(Long qnaId, QnaAnswerRequest request){
        QnaEntity qna = qnaRepository.findById(qnaId).get();
        qna.updateAnswerQna(
                request.getResponder_id(),
                request.getAnswer()
        );
        qnaRepository.save(qna);
    }
}
