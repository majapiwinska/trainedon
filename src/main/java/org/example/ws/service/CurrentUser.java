package org.example.ws.service;

import org.example.ws.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * Created by maja on 26.10.16.
 */
public class CurrentUser extends  org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user){
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoles().toString()));
        this.user = user;

    }


    public User getUser() {
        return user;
    }

    public Long getId(){
        return user.getId();
    }

    public List<String> getRoles(){return user.getRoles();
    }

}
