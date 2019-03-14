package com.slf.manage.sys.controller;

import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.repositories.*;
import com.slf.manage.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jftang3
 */
@Slf4j
@RestController
@RequestMapping(value = "/users")
public class SysController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> userList(@RequestParam(defaultValue = "") String userName,
                                               @RequestParam Integer building,
                                               @RequestParam Integer floor,
                                               @RequestParam Integer room,
                                               @PageableDefault(size = 10, page = 0) Pageable pageable) {
        UserQuery userQuery = new UserQuery(null, userName, building, floor, room);
        Page<User> users = userRepository.findAll(userQuery.toSpec(), pageable);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(users,"/users/list");
        return new ResponseEntity<>(users, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public ResponseEntity<Map> buildingList(@PageableDefault(size = 100, page = 0) Pageable pageable,
                                             @RequestParam(defaultValue = "") String sign) {
        Map<String, Page> map = new HashMap<>();
        map.put("buildings", buildingRepository.findAll(pageable));
        map.put("floors", floorRepository.findAll(pageable));
        map.put("rooms", roomRepository.findAllBySign(sign, pageable));
        HttpHeaders httpHeaders = PaginationUtil.generateMapHttpHeaders(map,"/users/building");
        return new ResponseEntity<>(map, httpHeaders, HttpStatus.OK);
    }
}
