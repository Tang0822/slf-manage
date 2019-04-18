package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.Permission;
import com.slf.manage.sys.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<User> {

    @Override
    @EntityGraph(attributePaths = {"groups"})
    List<Permission> findAll();
}
