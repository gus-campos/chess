# Chess

Jogo de xadrez que recebe, valida e faz jogadas a partir de movimentos informados pelo terminal, incluindo movimento especiais como o roque (ou castling), ou o en pasant. Para funcionar, o programa recebe movimentações em coordenadas de tabuleiro, repetindo a solicitação se a jogada for inválida. Internamente cria abstração a partir de várias classes, records, enums, iterators, para permitir a escrita de um programa simples, enquanto os detalhes da implementação permanecem encapsulados. 

Por enquanto verificar o cheque e o cheque-mate ainda cabe ao jogador, mas seráem breve implementada como funcionalidade.

## Exemplo de partida

```
8   r n b q k b n r 
7   p p p p p p p p 
6   . . . . . . . . 
5   . . . . . . . . 
4   . . . . . . . . 
3   . . . . . . . . 
2   P P P P P P P P 
1   R N B Q K B N R 

    a b c d e f g h 

Next Player: WHITE

Insert move: a2 a4

8   r n b q k b n r 
7   p p p p p p p p 
6   . . . . . . . . 
5   . . . . . . . . 
4   P . . . . . . . 
3   . . . . . . . . 
2   . P P P P P P P 
1   R N B Q K B N R 

    a b c d e f g h 

Next Player: BLACK

Insert move: e7 e5

8   r n b q k b n r 
7   p p p p . p p p 
6   . . . . . . . . 
5   . . . . p . . . 
4   P . . . . . . . 
3   . . . . . . . . 
2   . P P P P P P P 
1   R N B Q K B N R 

    a b c d e f g h 

Next Player: WHITE

Insert move: b1 c3

8   r n b q k b n r 
7   p p p p . p p p 
6   . . . . . . . . 
5   . . . . p . . . 
4   P . . . . . . . 
3   . . N . . . . . 
2   . P P P P P P P 
1   R . B Q K B N R 

    a b c d e f g h 

Next Player: BLACK

Insert move: g8 f6

8   r n b q k b . r 
7   p p p p . p p p 
6   . . . . . n . . 
5   . . . . p . . . 
4   P . . . . . . . 
3   . . N . . . . . 
2   . P P P P P P P 
1   R . B Q K B N R 

    a b c d e f g h 

Next Player: WHITE

Insert move: d2 d4

8   r n b q k b . r 
7   p p p p . p p p 
6   . . . . . n . . 
5   . . . . p . . . 
4   P . . P . . . . 
3   . . N . . . . . 
2   . P P . P P P P 
1   R . B Q K B N R 

    a b c d e f g h 

Next Player: BLACK

Insert move: e5 d4

8   r n b q k b . r 
7   p p p p . p p p 
6   . . . . . n . . 
5   . . . . . . . . 
4   P . . p . . . . 
3   . . N . . . . . 
2   . P P . P P P P 
1   R . B Q K B N R 

    a b c d e f g h 

Next Player: WHITE

```
