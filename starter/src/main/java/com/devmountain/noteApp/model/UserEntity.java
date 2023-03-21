package com.devmountain.noteApp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devmountain.noteApp.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    // @JsonManagedReference handles the other half of mitigating infinite recursion
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<NoteEntity> noteSet = new HashSet<>();

  


    // get, set user

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity(UserDto userDto) {
        if(userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if(userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }

    // public UserEntity() {

    // }
    
    // public UserEntity(Long id, String username, String password) {
    //     this.id = id;
    //     this.username = username;
    //     this.password = password;
    // }






   
}
