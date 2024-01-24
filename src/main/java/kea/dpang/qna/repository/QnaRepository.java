package kea.dpang.qna.repository;


import kea.dpang.qna.entity.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    /**
     * 사용자 ID로 QnA를 페이지네이션하여 조회합니다.
     *
     * @param authorId 작성자 ID
     * @param pageable 페이지네이션 정보
     * @return 페이지네이션된 QnA 정보
     */
    Page<Qna> findByAuthorId(Long authorId, Pageable pageable);
}
