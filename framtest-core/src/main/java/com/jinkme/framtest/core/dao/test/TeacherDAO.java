package com.jinkme.framtest.core.dao.test;

import com.jinkme.framtest.common.entity.test.Teacher;

/**
 * @author : Administrator
 * @date : 2019/2/27 09:44
 */
public interface TeacherDAO {

    /**
     * 插入
     * @param teacher
     * @return
     */
    public int insert(Teacher teacher);
}
