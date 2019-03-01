package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 解决 懒加载 JPA 典型的 N + 1 问题
     */
    @EntityGraph(value = "Group.Graph", type = EntityGraph.EntityGraphType.FETCH)
    Page<User> findByUserNameLike(String userName, Pageable pageable);

    User findByUserName(String userName);
}
