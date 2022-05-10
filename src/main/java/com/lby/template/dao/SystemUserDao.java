package com.lby.template.dao;

import com.lby.template.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserDao extends JpaRepository<SystemUser,Long> {

    Optional<SystemUser> findByUsername(@Param("username") String username);
}
