package com.danielfreitassc.deck_manager.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.deck_manager.dtos.CardRequestDto;
import com.danielfreitassc.deck_manager.dtos.CardResponseDto;
import com.danielfreitassc.deck_manager.services.CardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponseDto create(@ModelAttribute @Valid CardRequestDto cardRequestDto) throws Exception {
        return cardService.create(cardRequestDto);
    }

    @PutMapping("/{id}")
    public CardResponseDto update(@PathVariable String id,@ModelAttribute @Valid CardRequestDto cardRequestDto) throws Exception {
        return cardService.update(id, cardRequestDto);
    }

    @GetMapping
    public Page<CardResponseDto> getCards(Pageable pageable) {
        return cardService.getCards(pageable);
    }

    @GetMapping("/{id}")
    public CardResponseDto getCardById(@PathVariable String id) {
        return cardService.getCardById(id);
    }

    @DeleteMapping("/{id}")
    public CardResponseDto deleteById(@PathVariable String id) {
        return cardService.deleteById(id);
    }
}
