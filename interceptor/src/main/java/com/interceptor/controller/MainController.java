package com.interceptor.controller;

import com.interceptor.model.BlogEntity;
import com.interceptor.model.UserEntity;
import com.interceptor.repository.BlogRepository;
import com.interceptor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by nzhou026 on 10/4/2016.
 */
@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap){
        List<UserEntity> userEntityList = userRepository.findAll();
        modelMap.addAttribute("userEntityList",userEntityList);
        return "admin/users";
    }

    /**
     * get请求，访问添加用户界面
     * @return
     */
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser(){
        return "admin/addUser";
    }

    @RequestMapping(value = "/admin/users/addP",method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user")UserEntity userEntity){
        userRepository.saveAndFlush(userEntity);
        return "redirect:/admin/users";
    }

    @RequestMapping(value="/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap){
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user",userEntity);
        return "admin/userDetail";
    }

    @RequestMapping(value = "/admin/users/update/{id}",method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId,ModelMap modelMap){
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user",userEntity);
        return "admin/updateUser";
    }

    @RequestMapping(value = "/admin/users/updateP",method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user){
        userRepository.updateUser(user.getNickname(),user.getFirstname(),user.getLastname(),user.getPassword(),user.getId());
        userRepository.flush();
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId){
        userRepository.delete(userId);
        userRepository.flush();
        return "redirect:/admin/users";
    }

    @RequestMapping(value="/admin/blogs",method = RequestMethod.GET)
    public String getBlogs(ModelMap modelMap){
        List<BlogEntity> blogList = blogRepository.findAll();
        modelMap.addAttribute("blogList",blogList);
        return "/admin/blogs";
    }

    @RequestMapping(value = "/admin/blogs/add",method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap){
        List<UserEntity> userEntityList = userRepository.findAll();
        modelMap.addAttribute("userEntityList",userEntityList);
        return "admin/addBlog";
    }

    @RequestMapping(value = "/admin/blogs/addP",method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blog") BlogEntity blogEntity){
        System.out.println(blogEntity.getTitle());
        System.out.println(blogEntity.getUserByUserId().getNickname());
        blogRepository.saveAndFlush(blogEntity);
        return "redirect:/admin/blogs";
    }

    @RequestMapping(value = "/admin/blogs/show/{id}",method = RequestMethod.GET)
    public String showBlog(@PathVariable("id") Integer userId, ModelMap modelMap){
        BlogEntity blogEntity = blogRepository.findOne(userId);
        modelMap.addAttribute("blog",blogEntity);
        return "admin/blogDetails";
    }

    @RequestMapping(value = "/admin/blogs/update/{id}",method = RequestMethod.GET)
    public String updateBlog(@PathVariable("id") Integer userId,ModelMap modelMap){
        BlogEntity blogEntity = blogRepository.findOne(userId);
        List<UserEntity> userEntityList = userRepository.findAll();
        modelMap.addAttribute("blog",blogEntity);
        modelMap.addAttribute("userList",userEntityList);
        return "admin/updateBlog";
    }

    @RequestMapping(value = "/admin/blogs/updateP" , method = RequestMethod.POST)
    public  String updateBlogP(@ModelAttribute("blogP") BlogEntity blogEntity){
        blogRepository.updateBlog(blogEntity.getTitle(), blogEntity.getUserByUserId().getId(),
                blogEntity.getContent(), blogEntity.getPubDate(), blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }

    @RequestMapping(value = "/admin/blogs/delete/{id}",method = RequestMethod.GET)
    public String deleteBlog(@PathVariable("id") Integer blogId){
        blogRepository.delete(blogId);
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }


}
