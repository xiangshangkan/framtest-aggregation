package com.jinkme.framtest.common.form;

import java.io.Serializable;

/**
 * @author : Administrator
 * @date : 2019/2/27 17:38
 */
public class StudentTeacherForm implements Serializable {

    private static final long serialVersionUID = -8133189346181882871L;

    /** 学生编号 */
    private String studentNumber;
    /** 学生姓名*/
    private String studentName;
    /** 老师编号 */
    private String teacherNumber;
    /** 老师姓名*/
    private String teacherName;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "StudentTeacherForm{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
