package com.dtr.oas.service;

import com.dtr.oas.dao.UserDAO;
import com.dtr.oas.model.UserRole;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        com.dtr.oas.model.User user = (com.dtr.oas.model.User) userDAO.getCriteria(com.dtr.oas.model.User.class, Restrictions.eq("username", username)).uniqueResult();
        List<GrantedAuthority> authorities =
                buildUserAuthority(userDAO.getCriteria(UserRole.class, Restrictions.eq("userId",user.getId())).list());
        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.dtr.oas.model.User user,
                                            List<GrantedAuthority> authorities) {
        User user2 = new User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
        return user2;
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoleList) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for(UserRole userRole : userRoleList){
            setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName().toString()));
        }
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}


