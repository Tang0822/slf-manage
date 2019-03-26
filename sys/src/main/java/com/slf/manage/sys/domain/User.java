package com.slf.manage.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slf.manage.sys.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jftang3
 */
@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private String realName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private String mail;

    private String phone;

    private Integer isAble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private Date createTime;

    private Date updateTime;

    public static User tranUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        Building building = new Building();
        building.setId(userDto.getBuilding());
        Floor floor = new Floor();
        floor.setId(userDto.getFloor());
        Room room = new Room();
        room.setId(userDto.getRoom());
        user.setBuilding(building);
        Group group = new Group();
        group.setId(userDto.getGroup());
        user.setFloor(floor);
        user.setRoom(room);
        user.setGroup(group);
        user.setIsAble(userDto.getIsAble());
        user.setMail(userDto.getMail());
        user.setPhone(userDto.getPhone());
        user.setRealName(userDto.getRealName());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        return user;

    }
}
