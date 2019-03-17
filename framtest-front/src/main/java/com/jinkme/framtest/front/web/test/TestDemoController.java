package com.jinkme.framtest.front.web.test;

import com.jinkme.framtest.common.entity.test.Student;
import com.jinkme.framtest.common.entity.test.Teacher;
import com.jinkme.framtest.core.service.test.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : zhouhui
 * @date : 2019/2/27 10:30
 */
@Controller
@RequestMapping("/testDemo")
public class TestDemoController {

    @Autowired
    private TestDemoService testDemoService;

    @RequestMapping("/test")
   public void test(){
       System.out.println("哈哈哈");
   }

    /** 
     * @description 保存老师信息
     * @param       
     * @return      
     * @author      zhouhui
     * @date        2019/3/5 16:17
    */
    @RequestMapping("/saveTeacher")
    public void saveTeacherInfo(Teacher teacher){
        this.testDemoService.saveTeacher(teacher);
    }

    /**
     * @description //保存学生信息
     * @param
     * @return
     * @author      zhouhui
     * @date        2019/3/5 16:18
    */
    @RequestMapping("/saveStudent")
    public void saveStudentInfo(Student student){
        this.testDemoService.saveStudent(student);
    }





}
