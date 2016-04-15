package com.connaction.db.pages;

import com.connaction.db.model.Users;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by tamarap on 4/14/16.
 */
public class RegisteredUsers {
    @Inject
    private Session session;

    @Property
    private Users users;

    @Property
    private Long idus;

    @Property
    private List<Users>  usersList;
    @Property
    private List<Users>  adminsList;

    @InjectPage
    private Login login;


    private String userAcount;




    public List<Users> createUsersList(){
        return session.createQuery("from Users where userGroup='user'").list();
    }
    public List<Users> createAdminsList(){
        return session.createQuery("from Users where userGroup='admin' ").list();
    }

    public void setupRender(){
        usersList=createUsersList();
        adminsList=createAdminsList();
    }

    public void set(String firstName) {
        this.userAcount = firstName;

    }


    void onActionFromDelete(String id) {
        idus = Long.parseLong(id);
        Query query = session.createQuery("select user_delete from Users where userName=:userAcount");
        query.setParameter("userAcount", userAcount);
        List list = query.list();

        if(list.size()==0){
            deleteUser(idus);
        }
    }

    @CommitAfter
    private void deleteUser(Long id) {
        session.createQuery("delete from Users where userId=id");
    }



}
