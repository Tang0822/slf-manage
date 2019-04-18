package com.slf.manage.sys.repositories;

import com.slf.manage.sys.domain.MatchType;
import com.slf.manage.sys.domain.QueryWord;
import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.domain.User_;
import com.slf.manage.sys.service.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends BaseQuery<User>{

    @QueryWord(column = "id", func = MatchType.equal)
    private Integer userId;

    @QueryWord(column = "real_name", func = MatchType.like)
    private String realname;

    @QueryWord(column = "building_id", func = MatchType.equal)
    private Integer buildingId;

    @QueryWord(column = "floor_id", func = MatchType.equal)
    private Integer floorId;

    @QueryWord(column = "room_id", func = MatchType.equal)
    private Integer room;

    @Override
    public Specification<User> toSpec() {
        Specification<User> specification = super.toSpecWithAnd();
        return ((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
            if (buildingId != 0) {
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(
                                        root.get(User_.building), buildingId
                                )
                        )
                );
            }
            if (floorId != 0) {
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(
                                        root.get(User_.floor), floorId
                                )
                        )
                );
            }
            if (room != 0) {
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(
                                        root.get(User_.room), room
                                )
                        )
                );
            }
            if (realname != null && !realname.equals("")) {
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.like(
                                        root.get(User_.realName), "%" + realname + "%"
                                )
                        )
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }
}
