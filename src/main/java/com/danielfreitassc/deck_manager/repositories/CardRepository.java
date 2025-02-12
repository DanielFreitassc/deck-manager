package com.danielfreitassc.deck_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.deck_manager.models.CardEntity;

public interface CardRepository extends JpaRepository<CardEntity, String>{
    
}
