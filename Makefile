# The name of the final executable files
EXECUTABLE = trivial-fitness

# Find all .java files (SOURCES)
SOURCES = $(shell find ./src -type f -name '*.java')

# Storing the extra arguments passed to the make command, to pass it then to, for example, the executable (make dev)
MAKE_ARGS = $(filter-out $@,$(MAKECMDGOALS))

# Compiler Flags ====
JC = javac

# To activate DEBUG flags call the make file like this: `DEBUG=1 make ...`
DEBUG ?= 0
ifeq ($(DEBUG), 1)
	FLAGS = -g
endif

# Commands ===

# Compile projects
$(EXECUTABLE): $(OBJECTS)
	mkdir bin -p
	$(JC) $(SOURCES) -d bin $(FLAGS)

compile-jar:
	@make -s && echo "[Compiling] Java Classes"
	@jar cvfm $(EXECUTABLE).jar manifest.txt -C bin/ . &> /dev/null && echo "[Compiling] JAR"

dev:
	@printf "=====  Compiling classes and executing projects  =====\n"
	@DEBUG=1 make -s && echo "[Compiling] Java Classes"
	@printf "===================================================================\n"

	@cd bin && java Main $(MAKE_ARGS)

	@printf "===================================================================\n"

clean:
	@rm -rf ./bin; echo "[Cleaning] Bin Folder"
	@rm -rf ./*.jar; echo "[Cleaning] JAR Files"

# Ignore unmatched commands - to allow storing the arguments inside a variable MAKE_ARGS
%:
	@: