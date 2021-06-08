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

The version used for testing
```
$ java --version
openjdk 11.0.11 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)
OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
```
## Maven

This project is using Maven, so you need to have it installed to compile the project.

```
$ sudo apt install maven
$ mvn --version // Check everything is OK
```

The version used for testing

```
$ mvn --version
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 11.0.11, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "4.4.0-19041-microsoft", arch: "amd64", family: "unix"
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
## Using build.sh & run.sh script

```
$ chmod +x build.sh
$ ./build.sh
$ chmod +x run.sh
$ ./run.sh
```