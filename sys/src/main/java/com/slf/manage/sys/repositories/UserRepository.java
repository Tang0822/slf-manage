package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.domain.User_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Override
    @EntityGraph(attributePaths = {"building", "floor", "room", "group.permissions"})
    Page<User> findAll(Specification<User> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"group.permissions"})
    User findByUserName(String userName);
}
