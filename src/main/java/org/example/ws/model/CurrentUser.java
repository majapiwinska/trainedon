package org.example.ws.model;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by maja on 26.10.16.
 */
public class CurrentUser extends  org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user){
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;

    }

    public User getUser() {
        return user;
    }

    public Long getId(){
        return user.getId();
    }

    public User.Role getRole(){
        return user.getRole();
    }
}
