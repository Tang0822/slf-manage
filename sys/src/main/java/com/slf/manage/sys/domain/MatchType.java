package com.slf.manage.sys.domain;

public enum MatchType {
    // f = v
    equal,
    // 用于Number类型的比较f
    // f > v
    gt,
    // f >= v
    ge,
    // f < v
    lt,
    // f <= v
    le,
    // f != v
    notEqual,
    // flike v
    like,
    // f not like v
    notLike,
    // 比较类型(Comparable)的比较
    greaterThan,
    greaterThanOrEqualTo,
    lessThan,
    lessThanOrEqualTo
}
