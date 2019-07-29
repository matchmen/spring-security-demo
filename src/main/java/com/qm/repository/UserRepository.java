package com.qm.repository;

import com.qm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author: liqm
 * 2019-07-23
 */
public interface UserRepository extends JpaRepository<User, User> {

    User findByUsername(String username);

}
