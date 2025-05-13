package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Allergies;
import sgu.j2ee.model.User;

@Repository
public interface AllergyRepository extends JpaRepository<Allergies, Long> {
    List<Allergies> findByUser(User user);
}
