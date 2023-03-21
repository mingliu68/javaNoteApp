package com.devmountain.noteApp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.devmountain.noteApp.dtos.NoteDto;

public interface NoteService {

	void addNote(NoteDto noteDto, Long userId);

	void deleteNoteById(Long noteId);

	void updateNoteById(NoteDto noteDto);

	Optional<NoteDto> getNoteById(Long noteId);

	List<NoteDto> getAllNotesByUserId(Long userId);

}