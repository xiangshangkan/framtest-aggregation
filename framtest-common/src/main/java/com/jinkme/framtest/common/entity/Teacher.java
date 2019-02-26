package com.jinkme.framtest.common.entity;

import java.io.Serializable;

/**
 * @author : Administrator
 * @date : 2019/2/26 19:35
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 3449550793870568650L;

    /** 主键 */
    private Integer id;
    /** 老师编号*/
    private String teacherNumber;
    /** 老师名称*/
    private String teacherName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
