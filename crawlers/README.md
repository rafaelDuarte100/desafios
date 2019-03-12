# Desafio 2: Crawlers

Parte do trabalho na IDwall inclui desenvolver *crawlers/scrapers* para coletar dados de websites.
Como nós nos divertimos trabalhando, às vezes trabalhamos para nos divertir!

O Reddit é quase como um fórum com milhares de categorias diferentes. Com a sua conta, você pode navegar por assuntos técnicos, ver fotos de gatinhos, discutir questões de filosofia, aprender alguns life hacks e ficar por dentro das notícias do mundo todo!

Subreddits são como fóruns dentro do Reddit e as postagens são chamadas *threads*.

Para quem gosta de gatos, há o subreddit ["/r/cats"](https://www.reddit.com/r/cats) com threads contendo fotos de gatos fofinhos.
Para *threads* sobre o Brasil, vale a pena visitar ["/r/brazil"](https://www.reddit.com/r/brazil) ou ainda ["/r/worldnews"](https://www.reddit.com/r/worldnews/).
Um dos maiores subreddits é o "/r/AskReddit".

Cada *thread* possui uma pontuação que, simplificando, aumenta com "up votes" (tipo um like) e é reduzida com "down votes".

### Proposta

Encontrar e listar as *threads* que estão bombando no Reddit naquele momento!
Consideramos como bombando *threads* com 5000 pontos ou mais.

## Entrada
- Lista com nomes de subreddits separados por ponto-e-vírgula (`;`). Ex: "askreddit;worldnews;cats"

## Executando Aplicação


## Parte 1
Gerar e imprimir uma lista contendo:
1. Número de upvotes
2. Subreddit
3. título da thread
4. Link para os comentários da thread
5. Link da thread

```
$ cd crawlers/crawlers/
$ mvn package
```

`java -jar target/crawlers-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
> Inputs: 
```
Digite a lista com nomes de sibreddits separados por (';'). Ex: "askreddit;worldnews;cats"
askreddit;worldnews;cats

```

> Outputs: 
```
Buscando...


 Resultado da Busca:
------------------------------------------------------------
 Número de Upvotes: 61000
 Subreddit: AskReddit
 Título: What is an adult life equivalent of calling your teacher "mom"?
 Link para os Comentários: https://old.reddit.com/r/AskReddit/comments/azfgz0/what_is_an_adult_life_equivalent_of_calling_your/
 Link da Thread: https://old.reddit.com/r/AskReddit/comments/azfgz0/what_is_an_adult_life_equivalent_of_calling_your/
---------------------------------------------------------------------------------

```

## Parte 2

Construir um robô que nos envie essa lista via Telegram sempre que receber o comando /NadaPraFazer [+ Lista de subrredits] (ex.: /NadaPraFazer askreddit;worldnews;cats)

```
$ cd crawlers/TelegramBotCrawler/
$ mvn package
```

`java -jar target/TelegramBotCrawler-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

```
Iniciando bot...

```
