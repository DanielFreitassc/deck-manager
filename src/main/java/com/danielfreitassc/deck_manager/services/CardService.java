package com.danielfreitassc.deck_manager.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.deck_manager.dtos.CardRequestDto;
import com.danielfreitassc.deck_manager.dtos.CardResponseDto;
import com.danielfreitassc.deck_manager.mappers.CardMapper;
import com.danielfreitassc.deck_manager.models.CardEntity;
import com.danielfreitassc.deck_manager.repositories.CardRepository;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final MinioClient minioClient;

    public CardResponseDto create(CardRequestDto cardRequestDto) throws Exception{
        //Gerando um UUID randomico para a imagem
        String objectId = UUID.randomUUID().toString();

        // Salvando a foto no bucket do min.io
        minioClient.putObject(
            PutObjectArgs.builder()
            .bucket("images")
            .object(objectId)
            .stream(cardRequestDto.image().getInputStream(), cardRequestDto.image().getSize(), -1)
            .contentType(cardRequestDto.image().getContentType())
            .build()
        );

        CardEntity cardEntity = cardMapper.toEntity(cardRequestDto);
        cardEntity.setImageId(objectId);

        return cardMapper.toDto(cardRepository.save(cardEntity));        
    }

    public CardResponseDto update(String id, CardRequestDto cardRequestDto) throws Exception {
        CardEntity existingCard = checkId(id);
        CardEntity cardEntity = cardMapper.toEntity(cardRequestDto);

        cardEntity.setId(id);
        cardEntity.setImageId(existingCard.getImageId());

        // Verifica se imagem foi modificada
        if(cardRequestDto.image() != null) {
            String objectId = existingCard.getImageId();

            minioClient.putObject(
                PutObjectArgs.builder()
                .bucket("images")
                .object(objectId)
                .stream(cardRequestDto.image().getInputStream(), cardRequestDto.image().getSize(), -1)
                .contentType(cardRequestDto.image().getContentType())
                .build()
            );
        }

        return cardMapper.toDto(cardRepository.save(cardEntity));
    }

    public Page<CardResponseDto> getCards(Pageable pageable) {
        return cardRepository.findAll(pageable).map(cardMapper::toDto);
    }

    public CardResponseDto getCardById(String id) {
        CardEntity cardEntity = checkId(id);
        return cardMapper.toDto(cardEntity);
    }

    public CardResponseDto deleteById(String id) throws Exception {
        CardEntity cardEntity = checkId(id);

        minioClient.removeObject(
            RemoveObjectArgs.builder()
            .bucket("images")
            .object(cardEntity.getImageId())
            .build()
        );

        cardRepository.delete(cardEntity);
        return cardMapper.toDto(cardEntity);
    }

    // Verifica se o id existe no banco de dados
    public CardEntity checkId(String id) {
        Optional<CardEntity> card = cardRepository.findById(id);
        if(card.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Card não encontrado");
        return card.get();
    }
}
