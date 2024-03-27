# Trivial-Fitness OOP

## About the Project

This project was developed for the subject OOP (POO in portuguese) from University of Minho - Software Engineering degree.

#### Grade ⭐️ ⚠️⚠️ Complete this after the project is done ⚠️⚠️/20

### Demo 📽️

⚠️⚠️ Complete this after the project is done ⚠️⚠️
<!-- ![Demo Image](./demo.png) -->

### The goal ⛳️

In some words, the goal of this project was to create a Java app to manage athletes and their activities. The app should be able to add, remove, edit and list athletes, trains and plans.

If you want, you can read the [project statement](enunciado.pdf) (in Portuguese).

### About the Code 🧑‍💻

⚠️⚠️ Complete this after the project is done ⚠️⚠️

## Getting Started 🚀

#### Cloning the repository

```bash
$ git clone https://github.com/Darguima/Trivial-Fitness-OOP.git
$ git clone git@github.com:Darguima/Trivial-Fitness-OOP.git
```

### Installing dependencies 📦

```bash
# Debian / Ubuntu / Mint
$ sudo apt install openjdk-21-jdk

# Arch
$ sudo pacman -S jdk21-openjdk
```

#### Running the project 🏃‍♂️

Start by going to the project root folder:

```bash
$ cd Trivial-Fitness-OOP/
```

##### Compiling the code 🛠️

Java compiler will create a `bin/` folder with the compiled classes. Then you can execute the Main class, or pack the classes in a JAR file, as on the next command.

```bash
$ make

# With debug flags
$ DEBUG=1 make

# Running the class
$ cd bin && java Main <...params>
```

##### Packing the classes 🛠️📦️

If you want to distribute the project, you can pack the classes in a JAR file:

```bash
$ make compile-jar

# Executing program
$ java -jar trivial-fitness.jar
```

##### Developing

This function `clean`, `compile` in debug mode and `execute` the project

```bash
$ make dev <...params>
```

##### Cleaning classes files and JAR executable
```bash
$ make clean
```

## Developed by 🧑‍💻:

- [Afonso Pedreira](https://github.com/afooonso)
- [Dário Guimarães](https://github.com/darguima)
- [Hugo Rauber](https://github.com/HugoLRauber)
