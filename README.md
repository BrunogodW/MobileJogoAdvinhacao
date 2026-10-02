# Jogo de Adivinhação (Android / Kotlin)

App de duas telas feito para a Aula 15 de Mobile. O jogador informa o nome e o limite (10, 50 ou 100), e o app sorteia um número secreto para ele adivinhar com dicas.

## Telas

- **MainActivity**: lê o nome e o limite e envia os dois para a próxima tela com `putExtra()`.
- **JogoActivity**: sorteia o número com `Random.nextInt(1, maximo + 1)`, confere cada palpite, mostra a dica e conta as tentativas.

## Regras da dica

| Dica    | Condição                   |
|---------|----------------------------|
| Acertou | palpite == secreto         |
| Quente  | diferença <= 10% do limite |
| Frio    | diferença maior que isso   |

Quente e Frio também informam se o número é MAIOR ou MENOR que o palpite.

**Desafio:** ao acertar, aparece "Ana acertou em 4 tentativas!" e o botão "Jogar de novo", que chama `finish()` e volta à primeira tela.

## Como abrir e rodar

1. Extraia o `.zip`.
2. No Android Studio, use **File > Open** e selecione a pasta do projeto (a que tem o `settings.gradle.kts`).
3. Aguarde o Gradle Sync terminar (precisa de internet na primeira vez).
4. Escolha um emulador ou celular e clique em **Run**.

## Estrutura

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/jogoadivinhacao/
│   ├── MainActivity.kt
│   └── JogoActivity.kt
└── res/
    ├── layout/activity_main.xml
    ├── layout/activity_jogo.xml
    └── values/themes.xml
```

## Requisitos

- Android Studio recente (JDK 17 embutido)
- Android SDK 34
- minSdk 24
