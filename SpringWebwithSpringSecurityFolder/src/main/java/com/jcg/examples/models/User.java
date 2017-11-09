package com.jcg.examples.models;

import javax.persistence.*;

/**
 *
 *  User is a class that helps to work with database table users.
 *  This class consists exclusively of private fields and public methods that set or get
 *  values of fields:
 *  - id
 *  - login
 *  - password
 *  - is_admin (field for user's role in the system)
 *
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String is_admin;
    private String login;
    private String password;

    /**
     * This method return the value of field id
     * @return Long id value
     */
	public Long getId() {
		return id;
	}

    /**
     * This method set the value of field id
     * @param id Long id value
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * This method return the value of field is_admin
     * @return String is_admin value
     */
	public String getIs_admin() {
		return is_admin;
	}

    /**
     * This method set the value of field is_admin
     * @param isAdmin String value, this is the name of the user's role in the system
     */
	public void setIs_admin(String isAdmin) {
		this.is_admin = isAdmin;
	}

    /**
     * This method return the value of field login
     * @return String login value
     */
	public String getLogin() {
		return login;
	}

    /**
     * This method set the value of field login
     * @param login String value
     */
	public void setLogin(String login) {
		this.login = login;
	}

    /**
     * This method return the value of field password
     * @return String password value
     */
	public String getPassword() {
		return password;
	}

    /**
     * This method set the value of field password
     * @param password String value
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
