package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph( attributePaths = {"building", "floor", "room", "group.permissions"})
    Page<User> findByUserNameContaining(String userName, Pageable pageable);

    @EntityGraph(attributePaths = {"group.permissions"})
    User findByUserName(String userName);
}
