package com.example.ITSS.controllers;

import com.example.ITSS.config.OurUserDetailService;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.UserRole;
import com.example.ITSS.repositories.ProjectClassMemberRepository;
import com.example.ITSS.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @GetMapping("/defaultAdmin")
    public String admin() {
        return "viewAdminOK";
    }

    @GetMapping("/defaultTeacher")
    public String teacher() {
        return "viewClassTeacherOK";
    }

    @GetMapping("/defaultStudent")
    public String student() {
        return "viewClassStudentOK";
    }

    @GetMapping("/viewClassDetailTeacher")
    public String viewClassDetailTeacher(@RequestParam("classId") Long classId) {
        return "viewClassDetailTeacherOK";
    }

    @GetMapping("/viewClassDetailStudent")
    public String viewClassDetailStudent(@RequestParam("classId") Long classId, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUserName(username);
        return "viewClassDetailStudentOK";
    }

    @GetMapping("/viewProjectTeacher")
    public String viewProjectTeacher(@RequestParam("projectId") Long projectId) {
        return "viewProjectTeacher";
    }

    @GetMapping("/viewProjectStudent")
    public String viewProjectStudent(@RequestParam("projectId") Long projectId, Principal principal, Model model) {
        String username = principal.getName();
        User user = userRepository.findByUserName(username);
        ProjectClassMember projectClassMember = projectClassMemberRepository.findByProjectIdAndUserId(user.getId(), projectId);
        if (projectClassMember == null) {
            return null;
        }
        UserRole userRole = projectClassMember.getRole();
        String role = userRole.toString();
        model.addAttribute("role", role);
        return "viewProjectStudent";
    }

    @GetMapping("/viewProjectDetail")
    public String viewProjectDetail(@RequestParam("projectId") Long projectId, Authentication authentication) {
        boolean isTeacher = authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_TEACHER")
        );
        boolean isStudent = authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_STUDENT")
        );
        if (isTeacher) {
            return "viewProjectTeacher";
        } else if (isStudent) {
            return "viewProjectStudent";
        }
        return null;
    }

    @GetMapping("/viewClassDetail")
    public String viewClassDetail(@RequestParam("classId") Long classId, Authentication authentication) {
        boolean isTeacher = authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_TEACHER")
        );
        boolean isStudent = authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_STUDENT")
        );
        if (isTeacher) {
            return "viewClassDetailTeacherOK";
        } else if (isStudent) {
            return "viewClassDetailStudentOK";
        }
        return null;
    }

    @GetMapping("/viewMyTasks")
    public String viewMyTask(Authentication authentication) {
        return "viewMyTasks";
    }

    @GetMapping("/error")
    public String error() {
        return null;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
