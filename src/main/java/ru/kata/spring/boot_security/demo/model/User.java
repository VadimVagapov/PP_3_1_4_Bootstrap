package ru.kata.spring.boot_security.demo.model;


import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id",
            referencedColumnName = "id"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Role> roles;

    public User() {
    }

    public User(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String lastname, String email, String password, Integer age, Set<Role> roles) {
        this.username = username;
        this.lastname = lastname;
        this.password = password;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }

    public String getFirstHello() {
        String nameRole = "";
        for(Role role : getRoles()) {
            nameRole += role.getName().replace("ROLE_", "").concat("  ");
        }
        return nameRole;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User { " +
                "id = " + id +
                ", password = '" + password + '\'' +
                ", username = '" + username + '\'' +
                ", lastname = '" + lastname + '\'' +
                ", email = '" + email + '\'' +
                ", roles = " + roles +
                '}';
    }
}
