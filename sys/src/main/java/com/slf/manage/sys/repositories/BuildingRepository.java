package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.Building;
import com.slf.manage.sys.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer>, JpaSpecificationExecutor<User> {

    Page<Building> findAll(Pageable pageable);
}
