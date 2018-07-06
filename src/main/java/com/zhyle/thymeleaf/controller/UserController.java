package com.zhyle.thymeleaf.controller;


import com.zhyle.thymeleaf.dao.UserRepository;
import com.zhyle.thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //查询所有用户
    @GetMapping
    public ModelAndView list(Model model){
        model.addAttribute("userList",userRepository.listUser());
        model.addAttribute("title","用户管理");
        return new ModelAndView("user/list","userModel",model);
    }

    //根据id查询用户
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        User user = userRepository.getUserId(id);
        model.addAttribute("user",user);
        model.addAttribute("title","查看用户");
        return new ModelAndView("user/view","userModel",model);
    }

    //获取创建表单页面
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("title","创建用户");
        return new ModelAndView("user/form","userModel",model);
    }

    @PostMapping
    public ModelAndView saveOrUpdate(User user){
        userRepository.SaveOrUpdate(user);
        return new ModelAndView("redirect:/users");
    }

    //删除用户
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        userRepository.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }

    //修改用户
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model){
        User user = userRepository.getUserId(id);
        model.addAttribute("user",user);
        model.addAttribute("title","修改用户");
        return new ModelAndView("user/form","userModel",model);
    }
}
