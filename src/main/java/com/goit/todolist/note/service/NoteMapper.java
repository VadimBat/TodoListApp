package com.goit.todolist.note.service;

import com.goit.todolist.note.controller.NoteRequest;
import com.goit.todolist.note.data.NoteEntity;
import com.goit.todolist.note.controller.NoteResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper service implements transfer-operations for note entity, dto, request and response.
 */
@Service
public class NoteMapper {
    public List<NoteEntity> toNoteEntities(Collection<NoteDto> dtos) {
        return dtos.stream()
                .map(this::toNoteEntity)
                .collect(Collectors.toList());
    }

    public NoteEntity toNoteEntity(NoteDto dto) {
        NoteEntity entity = new NoteEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return entity;
    }

    public List<NoteDto> toNoteDtos(Collection<NoteEntity> entities) {
        return entities.stream()
                .map(this::toNoteDto)
                .collect(Collectors.toList());
    }

    public NoteDto toNoteDto(NoteEntity entity) {
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        return dto;
    }

    public List<NoteResponse> toNoteResponses(Collection<NoteDto> dtos) {
        return dtos.stream()
                .map(this::toNoteResponse)
                .collect(Collectors.toList());
    }

    public NoteResponse toNoteResponse(NoteDto dto) {
        NoteResponse response = new NoteResponse();
        response.setId(dto.getId());
        response.setTitle(dto.getTitle());
        response.setContent(dto.getContent());
        return response;
    }

    public List<NoteDto> requestsToNoteDtos(Collection<NoteRequest> requests) {
        return requests.stream()
                .map(this::toNoteDto)
                .collect(Collectors.toList());
    }

    public NoteDto toNoteDto(NoteRequest request) {
        NoteDto dto = new NoteDto();
        dto.setTitle(request.getTitle());
        dto.setContent(request.getContent());
        return dto;
    }

    public NoteDto toNoteDto(long id, NoteRequest request) {
        NoteDto dto = new NoteDto();
        dto.setId(id);
        dto.setTitle(request.getTitle());
        dto.setContent(request.getContent());
        return dto;
    }

}
