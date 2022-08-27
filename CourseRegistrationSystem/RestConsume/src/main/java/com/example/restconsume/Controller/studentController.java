package com.example.restconsume.Controller;

import com.example.restconsume.Entity.Stu.Course;
import com.example.restconsume.Entity.Stu.Student;
import com.example.restconsume.Feign.apiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/student")
@SessionAttributes({"student","students"})
public class studentController {
    @Autowired
    apiController apiC;

    @GetMapping("")
    public String getStudentAll(Model m) {
        List<Student> all = apiC.getAll();
        m.addAttribute("student",all);
//        all.forEach(a->a.getCourseList().forEach(b-> System.out.println(b) )  );

        return "student";
    }

    //needed a list of register courses related by student ID
    @GetMapping("/{studentId}")
    public String getStudentByID(Model m, @PathVariable int studentId, SessionStatus status) {
        Student student = apiC.getStudentById(studentId);
        m.addAttribute("student",student);
        System.out.println(student);
        return "regCourse";
    }


}