package com.luluShop.customerservice.login.controller;


import com.luluShop.customerservice.login.entity.User;
import com.luluShop.customerservice.login.repo.UserRepo;
import com.luluShop.customerservice.login.rmq.CustomMessage;
import com.luluShop.customerservice.login.rmq.MQConfig;
import com.luluShop.customerservice.login.rmq.MessagePublisher;
import com.luluShop.customerservice.login.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserService service;

    @Autowired
    private RabbitTemplate template;

    private static Integer id;

    private static String userEmail;

    public static String getTheUserEmail(){
        return userEmail;
    }

    public static Integer getTheUserId(){
        return id;
    }


    @GetMapping("/")
    public String login( Model model ){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){

        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){

        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute("user") User user){
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        User userData = repo.findByEmailAndPassword(user.getEmail(),user.getPassword());
        System.out.println("userData: "+userData);
        System.out.println("First Password "+user.getPassword());
        System.out.println("Second Password"+userData.getPassword());

        userEmail = userData.getEmail();
        id = userData.getId();

        if (userEmail.equals("admin@admin")){
            return "Admin";
         }

        else if(user.getPassword().equals(userData.getPassword())){

            CustomMessage message = new CustomMessage();
            message.setMessageId(userEmail); // Oder verwende getUserInfoEmail() hier, wenn erforderlich
            message.setMessage(String.valueOf(id)); // Oder verwende getUserInfoId() hier, wenn erforderlich
            message.setMessageDate(new Date());
            template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, message);

            return "loginSuccess";
        }else {
            return "error";
        }

    }

    @GetMapping("/logout")
    public String logOut(){

        CustomMessage message = new CustomMessage();
        message.setMessageId(null); // Oder verwende getUserInfoEmail() hier, wenn erforderlich
        message.setMessage(null); // Oder verwende getUserInfoId() hier, wenn erforderlich
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, message);

        return "login";
    }


    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user){
        String result = "error";
        System.out.println(user);
        if (user.getPassword().equals(user.getCpassword())){
            try {
                service.registerUser(user);
                result =  "loginSuccess";
            }catch (Exception e){
                result =  "error";
            }
        }
        return result;
    }


    @RequestMapping("/showEditUsers")
    public String editUser(Model model, @Param("keyword") String keyword) {
        List<User> listUsers = service.getAllUsers(keyword);

        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);

        return "Edit-Users";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer id) {
        this.service.deleteUserBYId(id);
        return "redirect:http://localhost:8081/showEditUsers";
    }

    @GetMapping("/showFormForUpdateUser/{id}")
    public String updateUser(@PathVariable(value = "id") Integer id, Model model) {
        User user = service.getUserById(id);

        model.addAttribute("product", user);
        return "";
    }


    @GetMapping("/contact")
    public String showContactPage(){

        return "contact";
    }

}
