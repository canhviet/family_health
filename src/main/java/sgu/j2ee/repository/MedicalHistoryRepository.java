package sgu.j2ee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.MedicalHistory;
import sgu.j2ee.model.User;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByUser(User user);

    @Query("SELECT m FROM MedicalHistory m WHERE m.user.id = :userId AND m.revisitDate > CURRENT_DATE ORDER BY m.revisitDate ASC")
    Optional<MedicalHistory> findNearestRevisitDateByUserId(@Param("userId") Long userId);

}
