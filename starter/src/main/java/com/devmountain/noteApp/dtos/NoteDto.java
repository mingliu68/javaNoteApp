package com.devmountain.noteApp.dtos;

import java.io.Serializable;

import com.devmountain.noteApp.model.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto implements Serializable{
    private Long id;
    private String body;
    private UserDto userDto;

    public NoteDto(NoteEntity note) {
        if(note.getId() != null) {
            this.id = note.getId();
        }
        if(note.getBody() != null) {
            this.body = note.getBody();
        }
    }
}
