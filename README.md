# Documentação da API Deck Manager

## Introdução
A API **Deck Manager** permite gerenciar cartas de um jogo de cartas colecionáveis. Esta documentação detalha como configurar o ambiente, os endpoints disponíveis e seus respectivos retornos.

---

## Configuração do MinIO
Para rodar o **MinIO**, utilize o seguinte comando:
```sh
docker compose up -d
```

Depois, acesse o painel de administração:
```
URL: http://localhost:9001
Usuário: ROOTUSER
Senha: CHANGEME123
```

No painel:
1. Crie um bucket chamado **images**.
2. Defina o bucket como **público**.

Agora o MinIO está pronto para armazenar imagens das cartas.

---

## Configuração do Backend
O backend da API roda na seguinte URL base:
```
http://localhost:8080
```

---

## Endpoints

### Criar Carta
**POST** `/cards`

Este endpoint recebe os seguintes parâmetros via **form-data**:

| Parâmetro  | Tipo     | Exemplo                     |
|------------|---------|-----------------------------|
| name       | string  |  Dark Magician              |
| image      | file    | foto.png                     |
| type       | string  |  Spellcaster                |
| attribute  | string  |  DARK                        |
| level      | integer | 7                             |
| attack     | integer | 2500                          |
| defense    | integer | 2100                          |
| description| string  |  The ultimate wizard...     |
| cardType   | string  |  Monster                     |
| effect     | string  |  Fire                         |

#### Exemplo de Resposta:
```json
{
    "id": "7b2a6f05-8b3d-4b09-9f5b-1cece2e96f0a",
    "name": "Dark Magician",
    "imageId": "c6c967ab-773d-4848-9f2f-2625716222c1",
    "type": "Spellcaster",
    "attribute": "DARK",
    "level": 7,
    "attack": 2500,
    "defense": 2100,
    "description": "The ultimate wizard in terms of attack and defense.",
    "cardType": "Monster",
    "effect": "Fire"
}
```

---

### Buscar Cartas (Paginado)
**GET** `/cards?page=0&size=10`

#### Exemplo de Resposta:
```json
{
    "content": [
        {
            "id": "e9510e4a-a3a8-4f4c-8716-cf75147d1e71",
            "name": "Dark Magician",
            "imageId": "e313f683-41db-42ce-98ae-07d96bdb6deb",
            "type": "Spellcaster",
            "attribute": "DARK",
            "level": 7,
            "attack": 2500,
            "defense": 2100,
            "description": "The ultimate wizard in terms of attack and defense.",
            "cardType": "Monster",
            "effect": "Fire"
        }
    ],
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "first": true,
    "last": true
}
```

---

### Buscar Carta por ID
**GET** `/cards/{id}`

#### Exemplo de Resposta:
```json
{
    "id": "e9510e4a-a3a8-4f4c-8716-cf75147d1e71",
    "name": "Dark Magician",
    "imageId": "e313f683-41db-42ce-98ae-07d96bdb6deb",
    "type": "Spellcaster",
    "attribute": "DARK",
    "level": 7,
    "attack": 2500,
    "defense": 2100,
    "description": "The ultimate wizard in terms of attack and defense.",
    "cardType": "Monster",
    "effect": "Fire"
}
```

---

### Deletar Carta por ID
**DELETE** `/cards/{id}`

#### Exemplo de Resposta:
```json
{
    "id": "e9510e4a-a3a8-4f4c-8716-cf75147d1e71",
    "name": "Dark Magician",
    "imageId": "e313f683-41db-42ce-98ae-07d96bdb6deb",
    "type": "Spellcaster",
    "attribute": "DARK",
    "level": 7,
    "attack": 2500,
    "defense": 2100,
    "description": "The ultimate wizard in terms of attack and defense.",
    "cardType": "Monster",
    "effect": "Fire"
}
```

---

### Buscar Imagem da Carta
**GET** `http://localhost:9000/images/{imageId}`

#### Resposta:
A imagem correspondente ao **imageId** será retornada no formato original (exemplo: `foto.png`).

---

## Conclusão
Esta documentação cobre a instalação, configuração e os endpoints da API **Deck Manager**. Se precisar de mais informações ou melhorias, entre em contato!

