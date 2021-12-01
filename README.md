# Advent of code 2021
Welcome to my repo for this year's [advent-of-code](https://adventofcode.com/).  
I have decided to not put THAT much effort in it than [last year](https://github.com/janetschel/advent-of-go-2020) where I did a recap of the day and all.

## Project/day setup
- macOS Monterey 12.0.1
- Maven 4.0.0 project with Java 17 LTS
- [./Makefile](https://github.com/janetschel/advent-of-code-2021/blob/main/Makefile) to automatically create each new day from template files
- util package for whatever
- Day's ([dev/janetschel/calendar/dayx/](https://github.com/janetschel/advent-of-code-2021/tree/main/src/main/java/dev/janetschel/calendar)) are set up like this:
  - `input.txt` for the input → needs to be copy + pasted manually (no fetch utils this year around -- I couldn't be bothered)
  - `Puzzle.java` for the actual solution each day
  - `README.md` for solve times

<br/>
All you need to do if you want to create a new day to start coding is: 

- In the terminal navigate to `./`
- Type following: `make new`

## Utils
My [utils](https://github.com/janetschel/advent-of-code-2021/tree/main/src/main/java/dev/janetschel/utils) will have some handy functions/classes to work with the input.  
Most notably the "implementation" of my of `Optional<Long>` to speed up handling input. Feel free to PR this. Will be updated during AoC.

<hr/>

## (Public) Leaderboard appearances
Although each day will have its own `README.md` all of my leaderboard appearances (< 1000) for `<part1, part2>` will be listed here.  
For the exact solve times check out each day's `README.md`. 

- Day 1: #724, #675
- ...

