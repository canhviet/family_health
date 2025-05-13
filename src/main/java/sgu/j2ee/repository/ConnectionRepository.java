package sgu.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sgu.j2ee.dto.response.UserConnected;
import sgu.j2ee.model.MedicalConnections;
import sgu.j2ee.model.User;

@Repository
public interface ConnectionRepository extends JpaRepository<MedicalConnections, Long> {
    @Query("SELECT mc.doctor.firstName AS firstName, mc.doctor.lastName AS lastName, mc.doctor.username AS username, mc.connectAt AS connectAt, mc.doctor.userId AS userId " +
       "FROM MedicalConnections mc WHERE mc.user.userId = :userId")
    List<UserConnected> findDoctorsByUserId(@Param("userId") Long userId);

    @Query("SELECT mc.user FROM MedicalConnections mc WHERE mc.doctor.userId = :doctorId " +
           "AND (LOWER(mc.user.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(mc.user.lastName) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(mc.user.firstName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "ORDER BY mc.user.userId FETCH FIRST 10 ROWS ONLY")
    List<User> findPatientsByDoctorIdAndSearch(@Param("doctorId") Long doctorId, 
                                               @Param("search") String search);

    
    @Query("SELECT u FROM User u WHERE u.userId NOT IN (" +
    "SELECT mc.user.userId FROM MedicalConnections mc WHERE mc.doctor.userId = :userId " +
    "UNION " +
    "SELECT mc.doctor.userId FROM MedicalConnections mc WHERE mc.user.userId = :userId" +
    ") AND u.userId != :userId " +
    "AND u.role.roleName = 'DOCTOR' " +
    "AND (LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
    "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) " +
    "OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
    "ORDER BY u.userId FETCH FIRST 10 ROWS ONLY")
   List<User> findDoctorsNotConnectedBySearch(@Param("userId") Long userId, 
                                        @Param("search") String search);
}
