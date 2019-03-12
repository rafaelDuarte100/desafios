# Desafio 1: Strings

Após ler o [coding style do kernel  Linux](https://www.kernel.org/doc/html/v4.10/process/coding-style.html), você descobre a mágica que é ter linhas de código com no máximo 80 caracteres cada uma.

### Proposta

Criar um plugin que receba:
1. Um texto qualquer
2. Um limite de comprimento de linha
3. Um boolean que informa se deseja que o texto seja justificado, e que retorne o texto de entrada formatado de forma que:

    3.1 Cada linha tenha no máximo o comprimento informado no item 2,
    
    3.2 O texto deve ser justificado dependendo do valor informado no item 3.

#### Acessando o Projeto

```
$ cd strings/JavaTemplate/
$ mvn package
```
## Executando Aplicação
### Com Texto Padrão

`java -jar target/StringFormatter-1.0-SNAPSHOT.jar `
```
+---------------------------------------------------+
|                    STRING FORMATTER               |
| DIGITE:                                           |
| 1 - USAR O TEXO PADRÃO.                           |
| 2 - IMPORTAR UM ARQUIVO TXT.                      |
|                                                   |
+---------------------------------------------------+
1
QUAL O TAMANHO LIMITE?
> 40
O TEXTO DEVE SER JUSTIFICADO? (true, false)
> true
Inputs:
=========================
Text: In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.

And God said, "Let there be light," and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light "day," and the darkness he called "night." And there was evening, and there was morning - the first day.
=========================
Limit: 40
Should justify: true
=========================
Output:
In the beginning God created the heavens
and   the   earth.  Now  the  earth  was
formless  and  empty,  darkness was over
the  surface of the deep, and the Spirit
of  God  was  hovering  over the waters.

And  God said, "Let there be light," and
there  was light. God saw that the light
was  good,  and  he  separated the light
from  the darkness. God called the light
"day,"   and   the  darkness  he  called
"night."  And  there  was  evening,  and
there  was  morning  -  the  first  day.
=========================
```

### Com Texto em Arquivo

`java -jar target/StringFormatter-1.0-SNAPSHOT.jar `
+---------------------------------------------------+
|                    STRING FORMATTER               |
| DIGITE:                                           |
| 1 - USAR O TEXO PADRÃO.                           |
| 2 - IMPORTAR UM ARQUIVO TXT.                      |
|                                                   |
+---------------------------------------------------+
2
QUAL O CAMINHO COMPLETO DO ARQUIVO?
> C:\Desenvolvimento\texto.txt
QUAL O TAMANHO LIMITE?
> 40
O TEXTO DEVE SER JUSTIFICADO? (true, false)
> false
Inputs:
=========================
Text: Text: In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.

And God said, "Let there be light," and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light "day," and the darkness he called "night." And there was evening, and there was morning - the first day.
=========================
Limit: 40
Should justify: false
=========================
Output:
Text: In the beginning God created the
heavens and the earth. Now the earth was
formless and empty, darkness was over
the surface of the deep, and the Spirit
of God was hovering over the waters.

And God said, "Let there be light," and
there was light. God saw that the light
was good, and he separated the light
from the darkness. God called the light
"day," and the darkness he called
"night." And there was evening, and
there was morning - the first day.
=========================
