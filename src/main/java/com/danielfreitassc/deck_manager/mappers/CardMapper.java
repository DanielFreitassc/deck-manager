package com.danielfreitassc.deck_manager.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.deck_manager.dtos.CardRequestDto;
import com.danielfreitassc.deck_manager.dtos.CardResponseDto;
import com.danielfreitassc.deck_manager.models.CardEntity;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardResponseDto toDto(CardEntity cardEntity);
    CardEntity toEntity(CardRequestDto cardRequestDto);
}
