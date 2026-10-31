# Searching for the Slime Breeder

A text adventure game with a graphical interface, built in Java at ESIEE Paris (2021, individual project) on top of the classic *World of Zuul* teaching project.

You play a slime lost in a cave, looking for its breeder. Meet the dragon, collect items, find the hidden door and get out before time runs out.

## Features

- Swing interface: an image for every room, buttons for the main commands, a text input
- 13 commands built with the **Command pattern** (`go`, `back`, `look`, `absorb`, `drop`, `eat`, `items`, `memorize`, `teleport`, `test`…)
- Items with weight and a carrying limit, a one-use teleporter (`Beamer`), locked doors, a room that sends you somewhere random
- Characters that follow the player once met
- Win and lose conditions, with a limit on commands and on play time
- **Replayable test scenarios**: `test <file>` runs a list of commands from `test/*.txt` to check that nothing broke after a change

## Run it

```bash
java -jar Game.jar
```

Or compile from source (Java 8+):

```bash
javac -encoding utf8 Game.java
java Game
```

## Run the test scenarios

In the game, type for example:

```
test win
test allrooms
test commands
```

## Project structure

```
pkg_game/               game engine, rooms, player, characters, GUI
pkg_game/pkg_command/   one class per command + parser
pkg_game/pkg_item/      items, item lists, teleporter
test/                   command scenarios
Images/                 one picture per room
```

Documentation is generated with Javadoc (`userdoc/`, `progdoc/`).