package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<Room, String>, JpaSpecificationExecutor<Room> {

    Page<Room> findAllBySign(String sing, Pageable pageable);
}
