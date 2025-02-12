package com.danielfreitassc.deck_manager.dtos;

public record CardResponseDto(
    String id,
    String name,
    String imageId,
    String type,
    String attribute,
    int level,
    int attack,
    int defense,
    String description,
    String cardType,
    String effect
) {
    
}
