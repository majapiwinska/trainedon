package org.example.web.api;

import org.example.ws.model.User;
import org.example.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by maja on 19.10.16.
*/



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{id}"
    )
    public String getUser(Model model, @PathVariable("id") Long id){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "/user/userResult";
    }


    //ma obsłużyć formularz
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/createUser"
    )
    public String handleUserCreateForm(User user, Model model){

        User createdUser = userService.create(user);
        model.addAttribute("user", createdUser);
        return "/user/userResult";
    }

    //ma wyswietlic usera
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/createUser"
    )
    public String getUserCreateView(@ModelAttribute User user){
        return "/user/createUser";
    }
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/updateUser"
    )
    public String handleUserUpdateForm(User user, Model model){
        User updatedUser = userService.update(user);
        model.addAttribute("user", updatedUser);
        if(updatedUser == null){
            return "There's no such user!";
        }
        return "/user/userResult";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/updateUser"
    )
    public String getUserUpdateView(Model model, @PathVariable("id") Long id){
        User updatedUser = userService.findUserById(id);
        model.addAttribute("user", updatedUser);
        return "/updateUser";
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/deleteUser/{id}"
    )
    public String deleteUserView(@PathVariable("id") Long id, Model model){
        User deletedUser = userService.findUserById(id);
        model.addAttribute("user", deletedUser);
        return "/deleteUser";
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/deleteUser"
    )
    public String deleteUser(User user, Model model){
        Long id = user.getId();
        userService.delete(id);
        return "index";
    }


}
