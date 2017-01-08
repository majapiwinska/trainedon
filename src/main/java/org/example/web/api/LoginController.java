package org.example.web.api;

import org.example.ws.model.User;
import org.example.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Created by maja on 26.10.16.
 */

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/login"
    )
    public String getLoginView(@RequestParam Optional<String> error, Model model, User user){

        model.addAttribute("user", user);

        model.addAttribute("error",error);
        return ("login");
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login"
    )
    public String handleLoginForm(User user, Model model){


        model.addAttribute("user", user);
        return "/user/userPage";
    };

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/loginVerification"
    )
    public String getHomePage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        List<String> currentUserRoles = user.getRoles();

        model.addAttribute("user", user);
        if(currentUserRoles.contains("ADMIN")){
            return ("/admin/adminHomePage");
        }else{
            return ("/user/userPage" );
        }
    }


}
