package com.edu.dao.dbservice.e1001;

import e1001.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-13
 * Time: 下午6:50
 * To change this template use File | Settings | File Templates.
 */
public interface E1001ServiceMapper {
    void insert(E100101InDto dto);

    List<E100102Dto> selectByUsrName(E100102InDto dto);

    List<E100103Dto> selectByUsrNameAndPass(E100103InDto dto);
}
