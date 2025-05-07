package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Family;
import sgu.j2ee.model.User;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long>{
    @Query("SELECT f FROM Family f WHERE f.headOfHousehold.userId = :headId")
    Family findByHeadId(@Param("headId") Long headId);

    @Query("SELECT u FROM User u WHERE u.family.familyId = :familyId")
    List<User> findUserByFamilyId(@Param("familyId") Long familyId);

    @Query("SELECT u FROM User u WHERE (u.family.familyId IS NULL OR u.family.familyId != :familyId) " +
       "AND (LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
       "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) " +
       "OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
       "ORDER BY u.userId FETCH FIRST 10 ROWS ONLY")
        List<User> findUsersNotInFamilyBySearch(@Param("familyId") Long familyId, 
                                        @Param("search") String search);
}
