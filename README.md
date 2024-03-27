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

This project uses Gradle to manage dependencies and tasks. To get a full list of tasks, you can run:

```bash
$ ./gradlew tasks
```

Although, I explain here the most important ones.

##### Compiling the code 🛠️

```bash
$ ./gradlew build
```

##### Packing the classes 🛠️📦️

If you want to distribute the project, you can pack the classes in an executable file:

```bash
$ ./gradlew distZip

# Unzipping the file
$ unzip app/build/distributions/trivial-fitness.zip

# Executing program - needs be with Java 21
$ ./trivial-fitness/bin/app
```

##### Developing 🧑‍💻

```bash
$ ./gradlew run
```

##### Cleaning build files 🧹
```bash
$ ./gradlew clean
```

## Developed by 🧑‍💻:

- [Afonso Pedreira](https://github.com/afooonso)
- [Dário Guimarães](https://github.com/darguima)
- [Hugo Rauber](https://github.com/HugoLRauber)
