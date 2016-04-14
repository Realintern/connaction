package com.connaction.db.pages;

import com.connaction.db.model.Users;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tamarap on 4/14/16.
 */
public class Registration {
    @Inject
    private Session session;
    @Persist
    private Users users;

    @InjectComponent
    private Form registration;

    @InjectComponent("username")
    private TextField userNameField;

    @Inject
    private Logger logger;
    @Property
    private List<String> listGropus= Arrays.asList("admin","user");
    @Property
    private String username;
    @Property
    private String password;
    @Property
    private String group;


    public void addUsers(Users users){
        session.save(users);
    }

    public void setUserFields(){
        users=new Users();
        users.setUserName(username);
        users.setUserGroup(group);
        users.setPassword(password);
    }
    void onValidateFromRegistration()
    {
        setUserFields();
        List<String> userNames=session.createQuery("Select userName from Users").list();
        for(String names:userNames) {
            if (users.getUserName().equals(names)){
                registration.recordError(userNameField,"This user name already used");
                return;
            }
        }
        addUsers(users);
    }
    Object onSuccessFromRegistration()
    {
        logger.info("Registration successful!");
        return RegisteredUsers.class;
    }
}
