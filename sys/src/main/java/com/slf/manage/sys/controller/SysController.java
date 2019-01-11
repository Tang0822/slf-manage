package com.slf.manage.sys.controller;

import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/sys/users")
public class SysController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> userList(@RequestParam(defaultValue = "") String userName, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        Page<User> users = userRepository.findByUserNameLike(userName, pageable);
        result.put("status", 200);
        result.put("message", "请求成功");
        result.put("date", users);
        return result;
    }
}
