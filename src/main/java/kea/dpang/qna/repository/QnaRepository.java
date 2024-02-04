package kea.dpang.qna.repository;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import kea.dpang.qna.dto.response.QnaDto;
import kea.dpang.qna.entity.Category;
import kea.dpang.qna.entity.Qna;
import kea.dpang.qna.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.JsonPath;

import java.time.LocalDate;
import java.util.Optional;

public interface QnaRepository extends JpaRepository<Qna, Long>, JpaSpecificationExecutor<Qna> {

    /**
     * 문의글을 페이지 형태로 반환하는 메서드입니다.
     * 필터링 가능 내용으로는 문의의글 작성 사용자 ID, 카테고리, 답변 상태가 있습니다.
     * @param userId 문의글을 작성한 사용자 ID (null 가능)
     * @param category 문의글이 속한 카테고리 (null 가능)
     * @param status 문의글의 답변 상태 (null 가능)
     * @param pageable 페이지네이션을 위한 정보
     * @return Page 타입의 Qna Entity
     */
    @Query("SELECT DISTINCT q " +
            "FROM Qna q " +
            "WHERE 1 = 1 " +
            "  AND (q.authorId = :userId OR :userId IS NULL) " +
            "  AND (q.category = :category OR :category IS NULL) " +
            "  AND (q.itemId = :itemId OR :itemId IS NULL)" +
            "  AND (q.status = :status OR :status IS NULL) " +
            "  AND (q.createdAt BETWEEN :startDate AND :endDate)")
    Page<Qna> findAllByUserIdAndCategoryAndStatus(
            @Param("userId") Long userId,
            @Param("category") Category category,
            @Param("itemId") Long itemId,
            @Param("status") Status status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
}