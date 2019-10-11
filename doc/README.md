# Java Project for Blackjack and Trianta Ena

### Authority

- Author		: Dezhou Wang
- BU-ID          : U46937632
- Date-Start  : Sep 19, 2019
- BU Course : CS591 P1 OOD

### Specification

- Blackjack DOC
  - [https://learn.bu.edu/bbcswebdav/pid-7347965-dt-content-rid-32527948_1/courses/19fallcascs591_p1/BlackJackSpecification%282%29.pdf](https://learn.bu.edu/bbcswebdav/pid-7347965-dt-content-rid-32527948_1/courses/19fallcascs591_p1/BlackJackSpecification(2).pdf)
- Trianta Ena DOC
  - [https://learn.bu.edu/bbcswebdav/pid-7365337-dt-content-rid-33109903_1/courses/19fallcascs591_p1/TriantaEna%284%29.pdf](https://learn.bu.edu/bbcswebdav/pid-7365337-dt-content-rid-33109903_1/courses/19fallcascs591_p1/TriantaEna(4).pdf)

### Instruction on how to compile and run

```
tar -xf wang_dezhou_Trianta_Ena.tar.gz
```

```
javac src/*.java
```

```
cd src/
```

```
java Main
```

### Instruction on how to play

At the beginning, the program will prompt you to choose which game to play.

```
+------------------------+
| Welcome to Card Games  |
+------------------------+
+-----------------------------+
| Menu Options:               |
| * 1. Blackjack              | ex. 1
| * 2. Trianta Ena            | ex. 2
| * Exit                      |
+-----------------------------+
Enter your option: 
```

Here, it only accepts **3** different inputs.

- 1
  - then will begin play Blackjack games
- 2
  - then will begin play Trianta Ena games
- Exit
  - [ Ignore case ]
  - terminate the program

#### Blackjack games

When begin play Blackjack, the program will prompt you to add player, remove player, start game, or exit to upper menu.

```
+----------------------+
| Welcome to Blackjack |
+----------------------+
+-----------------------------+
| Menu Options:               |
| * Add,[player name],[money] | ex. Add,Jack,100
| * Remove,[player name]      | ex. Remove,Jack
| * Start                     |
| * Exit                      |
+-----------------------------+
|| *.Dealer-"Dealer"; games: 0
Enter your option: 
```

Here, it only accepts **4** different inputs.

- Add,[name],[money]
  - [ Ignore case for add ]
  - [ Case sensitive for name ]
  - [ No space allowed ]
  - each player's name **must not be identical**
  - money must be non-negative integers
  - ex. Add,Jack,100
    - add a player with name "Jack" with money "$100"
- Remove,[name]
  - [ Ignore case for remove ]
  - [ Case sensitive for name ]
  - [ No space allowed ]
  - ex. Remove,Jack
    - remove a player whose name is "Jack"
- Start
  - [ Ignore case ]
  - start play one game
- Exit
  - [ Ignore case ]
  - exit Blackjack to upper menu which is choosing what game to play

#### Blackjack player bet round

After distributing 2 cards to each people, the program will ask each player to enter the value of bet.

```
+------------------------+
| Bet requirement:       |
| * any non-negative int |
+------------------------+
Dealer-"Dealer"; games: 0
Cards number: 2
[♣ 10]
[Hidden card]

Jack; $100; games: 0; bet: $0
Cards number: 2
[♣ 1]
[♦ Q]
Optimal score: 21
BLACKJACK!!!
Player "Jack", enter your bet: 
```

Here, it only accepts **1** type of input.

- Integer
  - [ non-negative ]
  - accept 0
  - cannot have "," inside
  - ex. 100
    - set bet to be $100

#### Blackjack player regular round

After all players have made a bet, the program will ask each player to choose option in turn.

```
+-------------------+
| Player's Options: |
| * Hit             |
| * Stand           |
| * Split           |
| * DoubleUp        |
+-------------------+
Dealer-"Dealer"; games: 1
Cards number: 2
[♠ 7]
[Hidden card]

Jack; $102; games: 1; bet: $10
Cards number: 2
[♥ 6]
[♦ 4]
Optimal score: 10
Player "Jack", enter your option: 
```

Here, it only accepts **4** different inputs.

- Hit
  - [ Ignore case ]
  - draw another card into hand
- Stand
  - [ Ignore case ]
  - stop drawing extra card for current hand (one player may have 2 hands if he has split)
- Split
  - [ Ignore case ]
  - split cards in one hand into two hands
- DoubleUp
  - [ Ignore case ]
  - double the current bet, then draw another card, and then stand

#### Blackjack dealer round

After all players stand or bust or blackjack, the program will begin ask the dealer to enter option. This prompt will continue until the dealer stands or bust or blackjack.

```
+-------------------+
| Dealer's Options: |
| * Hit             |
| * Stand           |
+-------------------+
Dealer-"Dealer"; games: 0
Cards number: 2
[♣ 10]
[♦ J]
Optimal score: 20
Dealer "Dealer", enter your option: 
```

Here, it only accepts **2** different inputs.

- Hit
  - [ Ignore case ]
  - draw one another card into hand
- Stand
  - [ Ignore case ]
  - stop and maintain the cards in hand

#### Trianta Ena games

```
+------------------------+
| Welcome to Trianta Ena |
+------------------------+
+-----------------------------+
| Menu Options:               |
| * Add,[player name]         | ex. Add,Jack
| * Remove,[player name]      | ex. Remove,Jack
| * Set,[money]               | Set money for all players, and bankers = 3*money. ex. Set,100
| * Start                     |
| * Exit                      |
+-----------------------------+

Enter your option: 
```

Here, it only accepts **5** different inputs.

- Add,[name]
  - [ Ignore case for add ]
  - [ Case sensitive for name ]
  - Each player ( including banker ), the name **must not be identical**
  - the number of all players ( including banker ) cannot be larger than 10
  - ex. Add,Jack
    - add a player called "Jack", money is set to 0 (default)
- Remove,[name]
  - [ Ignore case for remove ]
  - [ Case sensitive for name ]
  - ex. Remove,Jack
    - remove a player called "Jack"
- Set,[money]
  - [ Ignore case for set ]
  - money must be positive integers
  - discard current money of all players in the group
  - pick first one player as a banker automatically
  - set all players' money as the given value, and set the money of the banker as the 3 * given value
  - ex. Set,100
    - set all players' money = $100, and set banker's = $300
- Start
  - [ Ignore case ]
  - start one game
- Exit
  - [ Ignore case ]
  - exit Trianta Ena and go to upper menu

#### Trianta Ena player bet or fold

After each player is given a card, the program will ask each player to give a bet or fold.

```
+------------------------+
| Bet or fold:           |
| * positive int         | ex. 10
| * fold                 | ex. fold
+------------------------+
BANKER-Jack; $300; games: 0
[♣ Q]
Optimal Score: 10

PLAYER-Mike; $100; games: 0; bet: $0
[♣ 7]
Optimal Score: 7
Player "Mike", enter a value for bet or 'fold': 
```

Here, it only accepts **2** different inputs.

- Integer
  - positive integer
  - ex. 10
    - set bet to $10
- Fold
  - [ Ignore case ]
  - exit current round

#### Trianta Ena player regular round

```
+-------------------+
| Player's Options: |
| * Hit             |
| * Stand           |
+-------------------+
BANKER-Jack; $300; games: 0
[♣ Q]
[♥ Q]
[♥ 2]
Optimal Score: 22

PLAYER-Mike; $100; games: 0; bet: $10
[♣ 7]
[♥ 8]
[♣ 9]
Optimal Score: 24
Player "Mike", enter your option: 
```

Here, it only accepts **3** different inputs.

- Hit
  - [ Ignore case ]
  - draw another card into hand
- Stand
  - [ Ignore case ]
  - don't draw any other card, and keep current