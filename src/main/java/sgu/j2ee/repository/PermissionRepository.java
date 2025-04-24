package sgu.j2ee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sgu.j2ee.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
    Permission findByPermissionName(String permissionName);
}
