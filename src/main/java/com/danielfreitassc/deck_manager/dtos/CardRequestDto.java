package com.danielfreitassc.deck_manager.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardRequestDto(
    @NotBlank(message = "Nome da carta não pode ser um campo vazio")
    String name,
    @NotNull(message = "Imagem da carta não pode ser um campo vazio")
    MultipartFile image,
    @NotBlank(message = "Tipo da carta não pode ser um campo vazio")
    String type,
    @NotBlank(message = "Atribudo da carta não pode ser um campo vazio")
    String attribute,
    @NotNull(message = "Nivel da carta não pode ser um campo vazio")
    int level,
    @NotNull(message = "Ataque da carta não pode ser um campo vazio")
    int attack,
    @NotNull(message = "Defesa da carta não pode ser um campo vazio")
    int defense,
    @NotBlank(message = "Descrição da carta não pode ser um campo vazio")
    String description,
    @NotBlank(message = "Tipo de carta não pode ser um campo vazio")
    String cardType,
    @NotBlank(message = "Efeito da carta não pode ser um campo vazio")
    String effect
) {

}
