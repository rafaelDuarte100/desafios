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

### Descrição da Solução
Para esta parte do problema, foi criada uma entidade que representasse uma thread, para recuperar as informações foi utilizada a API ["jsoup"](https://jsoup.org/), para cada *subreddit* foi utilizada a url https://old.reddit.com/r/**subreddit**/top/?sort=top , após recuperar a página, as threads são processadas de cima para baixo, caso todas as threads da página atendam o pre-requisito de possuir 5k upvotes ou mais, então é recuperada a página seguinte e assim sucessivamente até que não tenham mais threads válidas ou que não existam mais páginas para aquele subreddit.

```
$ cd crawlers/crawlers/
$ mvn package
```

`java -jar target/crawlers-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
> Inputs: 
```
Digite a lista com nomes de sibreddits separados por (';'). Ex: "askreddit;worldnews;cats"
cats;dogs

```

> Outputs: 
```
Buscando...


 Resultado da Busca:
------------------------------------------------------------
 Número de Upvotes: 26000
 Subreddit: cats
 Título: My husband says I can?t adopt a cat. He didn?t say I couldn?t find a stray in our yard and lure it into the house with cheese.
 Link para os Comentários: https://old.reddit.com/r/cats/comments/aztxrt/my_husband_says_i_cant_adopt_a_cat_he_didnt_say_i/
 Link da Thread: https://old.reddit.com/r/cats/comments/aztxrt/my_husband_says_i_cant_adopt_a_cat_he_didnt_say_i/
---------------------------------------------------------------------------------

 Número de Upvotes: 6613
 Subreddit: cats
 Título: who else has conversations like that ^^?
 Link para os Comentários: https://old.reddit.com/r/cats/comments/azzz0l/who_else_has_conversations_like_that/
 Link da Thread: https://old.reddit.com/r/cats/comments/azzz0l/who_else_has_conversations_like_that/
---------------------------------------------------------------------------------

Quantidade de conteudos encontrados: 2

```

## Parte 2

Construir um robô que nos envie essa lista via Telegram sempre que receber o comando /NadaPraFazer [+ Lista de subrredits] (ex.: /NadaPraFazer askreddit;worldnews;cats)

### Descrição da Solução:
Para esta parte do problema foi utulizada a API ["java-telegram-bot-api"](https://github.com/pengrad/java-telegram-bot-api), foi criada uma entidade **Command** que representa as mensagens/comandos que podem ser enviados no chat com o bot, e para tratar esses comandos foi criado o listener **UpdatesListenerImpl** que é uma implementação da interface **UpdatesListener**, ele contém a lógica de tratamento de cada comando e também realiza a chamada ao **crawler** que já é uma utilização da solução da parte 1 deste desafio, ela foi incorporada como uma dependencia neste projeto.

```
$ cd crawlers/TelegramBotCrawler/
$ mvn package
```

`java -jar target/TelegramBotCrawler-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

```
Iniciando bot...
Bot iniciado...
```

## Link do bot
(http://t.me/crawler_reddit_bot)

## Demonstração:
![alt /Start](https://github.com/rafaelDuarte100/desafios/blob/master/bot-start.PNG)
![alt /Start](https://github.com/rafaelDuarte100/desafios/blob/master/bot-help.PNG)
![alt /Start](https://github.com/rafaelDuarte100/desafios/blob/master/bot-nadaprafazer.PNG)
