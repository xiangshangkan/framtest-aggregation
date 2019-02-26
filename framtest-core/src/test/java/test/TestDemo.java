package test;

import com.jinkme.framtest.common.entity.Student;

/**
 * @author : zhouhui
 * @date : 2019/2/26 20:08
 */
public class TestDemo {

   public static void main(String[] args){
       Student student = new Student();
       student.setStudentName("your father");
       System.out.println(student.getStudentName());
   }
}
