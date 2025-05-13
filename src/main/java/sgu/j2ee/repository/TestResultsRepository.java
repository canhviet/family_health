// TestResultsRepository.java
package sgu.j2ee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sgu.j2ee.model.TestResults;
import sgu.j2ee.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TestResultsRepository extends JpaRepository<TestResults, Long> {
    List<TestResults> findByUser(User user);
    List<TestResults> findByDatePerformedBetween(LocalDate startDate, LocalDate endDate);
    List<TestResults> findByTestType(String testType);
    Page<TestResults> findByUser(User user, Pageable pageable);
}