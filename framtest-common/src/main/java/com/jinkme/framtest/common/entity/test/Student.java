package com.jinkme.framtest.common.entity.test;

import com.jinkme.framtest.common.entity.Header;

import java.io.Serializable;

/**
 * @author : Administrator
 * @date : 2019/2/26 19:29
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -7876259629143961229L;
    /** 主键 */
   private Integer id;

   @Header(chieseName = "学生编号 ")
   private String studentNumber;
   @Header(chieseName = "学生姓名 ")
   private String studentName;
   @Header(chieseName = "老师编号")
   private String teacherNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
