package com.jinkme.framtest.core.service.test.impl;

import com.jinkme.framtest.common.entity.test.Student;
import com.jinkme.framtest.common.entity.test.Teacher;
import com.jinkme.framtest.core.dao.test.StudentDAO;
import com.jinkme.framtest.core.dao.test.TeacherDAO;
import com.jinkme.framtest.core.service.test.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : zhouhui
 * @date : 2019/2/26 19:56
 */
@Service("testDemoService")
public class TestDemoServiceImpl implements TestDemoService {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private TeacherDAO teacherDAO;


    @Override
    public void saveStudent(Student student) {
        int num = this.studentDAO.insert(student);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
       int num = this.teacherDAO.insert(teacher);
    }
}
