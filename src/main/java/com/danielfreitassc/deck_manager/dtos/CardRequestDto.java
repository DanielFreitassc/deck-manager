package com.danielfreitassc.deck_manager.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public record CardRequestDto(
    String name,
    MultipartFile image,
    String type,
    String attribute,
    int level,
    int attack,
    int defense,
    String description,
    String cardType,
    @NotBlank(message = "Efeito não pode ser um campo vazio")
    String effect
) {
    
}
