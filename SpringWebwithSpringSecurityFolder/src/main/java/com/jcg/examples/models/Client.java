package com.jcg.examples.models;
import javax.persistence.*;

/**
 *  Client is a class that helps to work with database table clients.
 *  This class consists exclusively of private fields and public methods that set or get
 *  values of fields:
 *  - id
 *  - first_name
 *  - last_name
 *  - surname
 *  - user_id (id of the user)
 */
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String surname;
    private Integer user_id;


    /**
     * This method return the value of field id
     * @return id value
     */
    public Long getId() {
		return id;
	}

    /**
     * This method set the value of field id
     * @param id id value
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * This method return the value of field first_name
     * @return first name value
     */
	public String getFirst_name() {
		return first_name;
	}

    /**
     * This method set the value of field first_name
     * @param first_name first name value
     */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

    /**
     * This method return the value of field last_name
     * @return last name value
     */
	public String getLast_name() {
		return last_name;
	}

    /**
     * This method set the value of field last_name
     * @param last_name last name value
     */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

    /**
     * This method return the value of field surname
     * @return surname value
     */
	public String getSurname() {
		return surname;
	}

    /**
     * This method set the value of field surname
     * @param surname surname value
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}

    /**
     * This method return the value of field user_id, id of the user
     * @return id of the user
     */
	public Integer getUser_id() {
		return user_id;
	}

    /**
     * This method set the value of field user_id, id of the user
     * @param user_id id of the user
     */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}

