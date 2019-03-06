package com.slf.manage.sys.controller;

import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.repositories.UserRepository;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> userList(@RequestParam(defaultValue = "") String userName,
                                               @PageableDefault Pageable pageable) {
        Page<User> users = userRepository.findByUserNameContaining(userName, pageable);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(users,"/users/list");
        return new ResponseEntity<>(users, httpHeaders, HttpStatus.OK);
    }
}
