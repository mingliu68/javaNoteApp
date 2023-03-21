package com.devmountain.noteApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name="notes")
@Data
@AllArgsConstructor 
@NoArgsConstructor  
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "text")
    private String body;

    // *** @JsonBackReference prevents infinite recursion when you deliver the resource up as JSON 
    // *** through the RESTful API endpoint you will create
    @ManyToOne
    @JsonBackReference 
    private UserEntity user;



    // get set note

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }


    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return body;
    }

    public NoteEntity(NoteDto noteDto) {
        if(noteDto.getBody() != null) {
            this.body = noteDto.getBody();
        }
    }
    // public NoteEntity() {

    // }

    // public NoteEntity(Long id, String body) {
    //     this.id = id; 
    //     this.body = body;
    // }
}
