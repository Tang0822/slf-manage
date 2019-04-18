package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.Floor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer>, JpaSpecificationExecutor<Floor> {

    Page<Floor> findAll(Pageable pageable);
}
