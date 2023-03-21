package com.devmountain.noteApp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmountain.noteApp.dtos.NoteDto;
import com.devmountain.noteApp.model.NoteEntity;
import com.devmountain.noteApp.model.UserEntity;
import com.devmountain.noteApp.repositories.NoteRepository;
import com.devmountain.noteApp.repositories.UserRepository;


@Service
public class NoteServiceImpl implements NoteService {
    // add a note
    // find a note
    // update a note
    // delete a note
    // all notes by a user

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
	@Transactional  
    public void addNote(NoteDto noteDto, Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        NoteEntity note = new NoteEntity(noteDto);
        userOptional.ifPresent(note::setUser);
        noteRepository.saveAndFlush(note);
    }

    @Override
	@Transactional
    public void deleteNoteById(Long noteId) {
        Optional<NoteEntity> noteOptional = noteRepository.findById(noteId);
        noteOptional.ifPresent(note -> noteRepository.delete(note));
;    } 

    @Override
	@Transactional
    public void updateNoteById(NoteDto noteDto) {
        Optional<NoteEntity> noteOptional = noteRepository.findById(noteDto.getId());
        noteOptional.ifPresent(note -> {
            note.setBody(noteDto.getBody());
            noteRepository.saveAndFlush(note);
        });
    }

    @Override
	public Optional<NoteDto> getNoteById(Long noteId) {        Optional<NoteEntity> noteOptional = noteRepository.findById(noteId);
        if(noteOptional.isPresent()) {
            return Optional.of(new NoteDto(noteOptional.get()));
        }
        return Optional.empty();
    }

    @Override
	public List<NoteDto> getAllNotesByUserId(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            List<NoteEntity> noteList = noteRepository.findAllByUserEquals(userOptional.get());
            return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
