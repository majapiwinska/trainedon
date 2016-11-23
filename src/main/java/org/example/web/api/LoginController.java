package org.example.web.api;

import org.example.ws.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by maja on 26.10.16.
 */

@Controller
public class LoginController {

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

        return ("/user/userPage" );
    };


}
