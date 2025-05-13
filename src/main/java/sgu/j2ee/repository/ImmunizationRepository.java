package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Immunizations;
import sgu.j2ee.model.User;

@Repository
public interface ImmunizationRepository extends JpaRepository<Immunizations, Long> {
    List<Immunizations> findByUser(User user);
}
