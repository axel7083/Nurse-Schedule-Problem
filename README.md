# Nurse-Schedule-Problem

# Installation (Linux)

## Java environment
Make sure you have Java installed

```
$ sudo apt update
$ sudo apt install default-jre
$ sudo apt install default-jdk
$ java --version // Check everything is OK
```

## Maven

This project is using Maven, so you need to have it installed to compile the project.

```
$ sudo apt install maven
$ mvn --version // Check everything is OK
```

# Usage
 
## Packing

```
$ git clone https://github.com/axel7083/Nurse-Schedule-Problem
$ cd Nurse-Schedule-Problem
$ mvn package
```

## Run

```
$ cd target
$ java -cp IT45-Project-1.0-SNAPSHOT.jar Main
```
