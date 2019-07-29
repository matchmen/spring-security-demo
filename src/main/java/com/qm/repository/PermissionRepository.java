package com.qm.repository;

import com.qm.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author: liqm
 * 2019-07-23
 */
public interface PermissionRepository extends JpaRepository<Permission,Permission> {

    Permission findByUrl(String url);

}
