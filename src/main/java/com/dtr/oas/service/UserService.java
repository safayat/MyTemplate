package com.dtr.oas.service;

import com.dtr.oas.dao.UserDAO;
import com.dtr.oas.enums.Roles;
import com.dtr.oas.model.User;
import com.dtr.oas.model.UserRole;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService extends CommonService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) throws Exception{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());
        user.setUpdateDate(user.getCreationDate());
        userDAO.saveOrUpdate(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleName(Roles.ROLE_USER);
        userDAO.saveOrUpdate(userRole);
    }

    public void updateClientFieldsView(String clientFields, int userId) throws Exception{
        String hql = "update " + User.class.getSimpleName() + " set selectedClientFields = " + StringUtils.quote(clientFields) + " where  id = " + userId;
        userDAO.updateByHql(hql);
    }

    public void updateTicketFieldsView(String ticketFields, int userId) throws Exception{
        String hql = "update " + User.class.getSimpleName() + " set selectedTicketFields = " + StringUtils.quote(ticketFields) + " where  id = " + userId;
        userDAO.updateByHql(hql);
    }

    public User getUserByUserName(String userName) throws Exception{
        User user = (User)userDAO.getQuery("from " + User.class.getSimpleName() + " where username = " + StringUtils.quote(userName)).uniqueResult();
        if( user != null ){
            user.setUserRoleList(getUserRolesByUserId(user.getId()));
        }
        return user;
    }

    public User getUserByEmail(String email) throws Exception{
        User user = (User)userDAO.getQuery("from " + User.class.getSimpleName() + " where email = " + StringUtils.quote(email)).uniqueResult();
        if( user != null ){
            user.setUserRoleList(getUserRolesByUserId(user.getId()));
        }
        return user;
    }

    public List<UserRole> getUserRolesByUserId(int userId) throws Exception{
        List<UserRole> userRoleList = userDAO.getCriteria(UserRole.class, Restrictions.eq("userId", userId)).list();
        return userRoleList;
    }



}
