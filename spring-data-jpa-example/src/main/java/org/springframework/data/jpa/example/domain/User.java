package org.springframework.data.jpa.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.joda.time.LocalDate;


/**
 * Sample user class.
 * 
 * @author Oliver Gierke
 */
@Entity
@NamedQuery(name = "User.findByTheUsersName", query = "from User u where u.username = ?")
public class User {

    private static final long serialVersionUID = -2952735933715107252L;

    @Id @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String username;

    private String firstname;
    private String lastname;
    
    private int age;
    private LocalDate creationDate;


    public User() {

        this(null);
    }


    /**
     * Creates a new user instance.
     */
    public User(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }
    

    /**
     * Returns the username.
     * 
     * @return
     */
    public String getUsername() {

        return username;
    }


    /**
     * @param username the username to set
     */
    public void setUsername(String username) {

        this.username = username;
    }


    /**
     * @return the firstname
     */
    public String getFirstname() {

        return firstname;
    }


    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }


    /**
     * @return the lastname
     */
    public String getLastname() {

        return lastname;
    }


    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {

        this.lastname = lastname;
    }
    
    
    
    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        User that = (User) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
    
    @Override
    public String toString() {
        return username;
    }
}
