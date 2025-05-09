package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Prescriptions;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescriptions, Long> {
    @Query("SELECT p FROM Prescriptions p LEFT JOIN FETCH p.medications m WHERE p.user.userId = :userId")
    List<Prescriptions> findWithUserId(@Param("userId") Long userId);
}
