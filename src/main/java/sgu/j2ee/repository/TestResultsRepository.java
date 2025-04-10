// TestResultsRepository.java
package sgu.j2ee.repository;

import sgu.j2ee.model.TestResults;
import sgu.j2ee.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TestResultsRepository extends JpaRepository<TestResults, Long> {
    List<TestResults> findByUser(User user);
    List<TestResults> findByDatePerformedBetween(LocalDate startDate, LocalDate endDate);
    List<TestResults> findByTestType(String testType);
    Page<TestResults> findByUser(User user, Pageable pageable);
}