# Trivial-Fitness OOP

## About the Project

This project was developed for the subject OOP (POO in portuguese) from University of Minho - Software Engineering degree.

#### Grade ⭐️ 17/20

### Demo 📽️

https://github.com/Darguima/Trivial-Fitness-OOP/assets/49988070/bbd2d70a-da1f-415c-ab43-aceebe0d9468

## Download 📥📲

You can download the app on [project's releases](https://github.com/Darguima/Trivial-Fitness-OOP/releases).

### The goal ⛳️

In some words, the goal of this project was to create a Java app to manage athletes and their activities. The app should be able to add, remove, edit and list athletes, trains and plans.

If you want, you can read the [project statement](enunciado.pdf) (in Portuguese).

### About the Code 🧑‍💻

The main classes of the project are `TrainingPlans`, `Activities`, and `Users`. The latter two are abstract classes since there will be different types of activities and users. From a broad perspective, users store completed activities and training plans, while training plans store planned activities.

The activity classes follow this hierarchy: `Activity > TypeOfActivity > SpecificActivity`. For example, `Activity -> RepetitionsActivity -> PushUps`.

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

##### Developing 🧑‍💻

```bash
$ ./gradlew run --console=plain
```

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

##### Cleaning build files 🧹
```bash
$ ./gradlew clean
```

## Developed by 🧑‍💻:

- [Afonso Pedreira](https://github.com/afooonso)
- [Dário Guimarães](https://github.com/darguima)
- [Hugo Rauber](https://github.com/HugoLRauber)
