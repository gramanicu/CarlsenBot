# Copyright 2020 Grama Nicolae

.PHONY: clean run
.SILENT: clean

# Compilation variables
CC = g++
CFLAGS = -Wno-unused-parameter -Wall -Wextra -pedantic -g -std=c++14
EXE = ArrayList
SRC = $(wildcard */*.cpp)
OBJ = $(SRC:.cpp=.o)

# Compiles the program
build: $(OBJ)
	$(info Compiling code...)
	@$(CC) -o $(EXE) $^ $(CFLAGS) ||:
	$(info Compilation successfull)
	-@rm -f *.o ||:
	@$(MAKE) -s gitignore ||:

%.o: %.cpp
	$(CC) -o $@ -c $< $(CFLAGS)

# Executes the binary
run: clean build
	./$(EXE)

# Deletes the binary and object files
clean:
	rm -f $(EXE) $(OBJ) ArrayList.zip
	echo "Deleted the binary and object files"

# Creates an archive of the project
pack:clean
	zip -FSr ArrayList.zip *