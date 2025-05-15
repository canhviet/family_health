package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Medications;

@Repository
public interface MedicationRepository extends JpaRepository<Medications, Long>{
    @Query("SELECT m FROM Medications m WHERE m.prescription.user.userId = :userId " +
           "AND CURRENT_DATE BETWEEN m.startDate AND m.endDate")
    List<Medications> findMedicationsForTodayByUserId(@Param("userId") Long userId);
}
