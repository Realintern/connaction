package com.connaction.db.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    private int userId;
    @Validate("required")
    private String userName;
    @Validate("required")
    private String password;
    @Validate("required")
    private String userGroup;

}