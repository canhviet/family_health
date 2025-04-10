package sgu.j2ee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long>{

}
