package com.devmountain.noteApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmountain.noteApp.model.NoteEntity;
import com.devmountain.noteApp.model.UserEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    Optional<NoteEntity> findById(Long noteId);
    List<NoteEntity> findAllByUserEquals(UserEntity user);
}
