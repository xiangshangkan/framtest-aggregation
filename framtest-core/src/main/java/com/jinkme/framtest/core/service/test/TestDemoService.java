package com.jinkme.framtest.core.service.test;

import com.jinkme.framtest.common.entity.test.Student;
import com.jinkme.framtest.common.entity.test.Teacher;

/**
 * @author : Administrator
 * @date : 2019/2/26 19:55
 */
public interface TestDemoService {

   /**
    * 保存学生
    * @param student
    */
   public void saveStudent(Student student);

   /**
    * 保存老师
    * @param teacher
    */
   public void saveTeacher(Teacher teacher);

}
