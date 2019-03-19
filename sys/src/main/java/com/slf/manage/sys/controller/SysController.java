package com.slf.manage.sys.controller;

import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.domain.dto.UserDto;
import com.slf.manage.sys.repositories.*;
import com.slf.manage.util.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDto userList(@RequestParam(defaultValue = "") String userName,
                                @RequestParam Integer building,
                                @RequestParam Integer floor,
                                @RequestParam Integer room,
                                @PageableDefault(size = 10, page = 0) Pageable pageable) {
        UserQuery userQuery = new UserQuery(null, userName, building, floor, room);
        Page<User> users = userRepository.findAll(userQuery.toSpec(), pageable);
        return new ResponseDto(HttpStatus.OK, users);
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public ResponseDto buildingList(@PageableDefault(size = 100, page = 0) Pageable pageable,
                                             @RequestParam(defaultValue = "") String sign) {
        Map<String, Page> map = new HashMap<>();
        map.put("buildings", buildingRepository.findAll(pageable));
        map.put("floors", floorRepository.findAll(pageable));
        map.put("rooms", roomRepository.findAllBySign(sign, pageable));
        return new ResponseDto(HttpStatus.OK, map);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto saveUser(UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
        User user = userRepository.save(User.tranUser(userDto));
        if (user.getId() != null) {
            return new ResponseDto(HttpStatus.OK, user);
        } else {
            return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }
}
