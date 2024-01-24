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
        QnaEntity qna = QnaEntity.builder()
                .author_id(request.getUserID())
                .title(request.getTitle())
                .category(request.getInquiryType1())
                .question(request.getContents())
                .is_public(request.getAgree())
                .item_id(request.getItemId())
                .attachment_url(request.getImage())
                .state("1")
                .build();
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
        return qnaReturn(qnaList);
    }
    @Override
    public List<AllQnaGetResponseDto> getUserQna(Iterable<Long> request){
        List<QnaEntity> qnaList = qnaRepository.findAllById(request);
        return qnaReturn(qnaList);
    }

    private List<AllQnaGetResponseDto> qnaReturn(List<QnaEntity> qnaList) {
        List<AllQnaGetResponseDto> qnaReturn = new ArrayList<>();
        for (QnaEntity qna:qnaList){
            AllQnaGetResponseDto qnaGet = AllQnaGetResponseDto.builder()
                    .qna_id(qna.getId())
                    .category(qna.getCategory())
                    .title(qna.getTitle())
                    .status(qna.getState())
                    .created_at(qna.getCreated_at())
                    .build();
            qnaReturn.add(qnaGet);
        }
        return qnaReturn;
    }

    @Override
    public QnaGetResponseDto getQna(Long request) {
        QnaEntity qna = qnaRepository.findById(request).get();
        return QnaGetResponseDto.builder()
                .qna_id(qna.getId())
                .author_id(qna.getAuthor_id())
                .responder_id(qna.getResponder_id())
                .item_id(qna.getItem_id())
                .title(qna.getTitle())
                .category(qna.getCategory())
                .question(qna.getQuestion())
                .attachment_url(qna.getAttachment_url())
                .state(qna.getState())
                .answer(qna.getAnswer())
                .is_public(qna.getIs_public())
                .created_at(qna.getCreated_at())
                .updated_at(qna.getUpdated_at())
                .build();
    }

    @Override
    public void updateAnswerQna(Long qnaId, QnaAnswerRequest request){
        QnaEntity qna = qnaRepository.findById(qnaId).get();
        qna.updateAnswerQna(
                request.getResponder_id(),
                request.getAnswer()
        );
    }
}
