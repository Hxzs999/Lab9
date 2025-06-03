package app.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.spring.model.SectionEntity;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Integer> {
    List<SectionEntity> findByDeletedFalse();
    boolean existsById(Integer id);
}
