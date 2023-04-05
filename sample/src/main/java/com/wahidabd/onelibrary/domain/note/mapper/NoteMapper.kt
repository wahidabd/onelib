package com.wahidabd.onelibrary.domain.note.mapper

import com.wahidabd.onelibrary.data.note.local.NoteEntity
import com.wahidabd.onelibrary.domain.note.model.Note


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


fun NoteEntity.toDomain(): Note =
    Note(id, title, description)

fun Note.toEntity(): NoteEntity =
    NoteEntity(id, title, description)