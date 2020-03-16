# Copyright 2020 Grama Nicolae

.PHONY: clean run
.SILENT: clean

# Compiles the program
build:
	$(info Creating sources list)
	@find -name "*.java" -not -path "*/test/*" > sources.txt||:
	$(info Compiling code...)
	@javac @sources.txt -d out
	$(info Compilation successfull)
	-@rm -f sources.txt ||:

# Executes the binary
run: build
	@java -cp out com.carlsenbot.main.Main

# Deletes the binary and object files
clean:
	rm -rdf out/*
	echo "Deleted the binary and object files"

# Creates an archive of the project
pack:clean
	zip -FSr src lib test .gitignore Makefile Readme.md