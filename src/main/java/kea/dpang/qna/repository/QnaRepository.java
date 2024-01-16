package kea.dpang.qna.repository;


import kea.dpang.qna.entity.QnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaEntity,Long> {
}
