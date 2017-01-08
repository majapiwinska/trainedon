package org.example.web.api;

import org.example.ws.model.Training;
import org.example.ws.model.User;
import org.example.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public String handleUserCreateForm(User user, Model model, Principal principal){

        User createdUser = userService.create(user);
        model.addAttribute("user", createdUser);
        return "/user/userPage";
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
    public String getUserUpdateView(Model model, Principal principal){
        String email = principal.getName();
        User updatedUser = userService.findUserByEmail(email);
        model.addAttribute("user", updatedUser);
        return "/user/updateUser";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/deleteUser/{id}"
    )
    public String getDeleteUserView(@PathVariable("id") Long id, Model model){
        User deletedUser = userService.findUserById(id);
        model.addAttribute("user", deletedUser);
        return "/user/deleteUser";
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/deleteUser"
    )
    public String deleteUser(User user, Model model){
        Long id = user.getId();
        userService.delete(id);
        return "/index";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/userPage"
    )
    public String getUsersTrainings(Model model, Principal principal){
        String userEmail = principal.getName();
        User user = userService.findUserByEmail(userEmail);

        List<Training> trainingList = user.getTrainingList();
        model.addAttribute("user", user);
        model.addAttribute("trainingList", trainingList);
        return "/user/userPage" ;

    };

    @ExceptionHandler
    public String error(Model model, Exception e) {
        model.addAttribute("error", e);
        return "error";
    }
}
