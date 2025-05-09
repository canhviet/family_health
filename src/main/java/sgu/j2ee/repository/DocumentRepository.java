package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Documents;

@Repository
public interface DocumentRepository extends JpaRepository<Documents, Long> {
    @Query("SELECT d FROM Documents d WHERE d.user.userId = :userId")
    List<Documents> findByUserId(@Param("userId") Long userId);
}
