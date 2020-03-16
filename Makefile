# Copyright 2020 Grama Nicolae

.PHONY: clean run
.SILENT: clean

# Compiles the program
build:
	$(info Creating sources list)
	@find -name "*.java" -not -path "*/test/*" > sources.txt||:
	$(info Compiling code...)
	@javac @sources.txt -d out
	$(info Creating .jar file)
	@jar --create --file CarlsenBot.jar --manifest ./src/META-INF/MANIFEST.MF -C out/ .
	-@$(MAKE) clean||:

# Runs the jarfile
run: build
	@java -jar CarlsenBot.jar

# Starts xboard using the bot
xboard: build
	@xboard -debug -nameOfDebygFile debug.txt -fcp "java -jar CarlsenBot.jar"

# Deletes the "out" directory and sources.txt
clean:
	@rm -rdf out sources.txt

# Creates an archive of the project
pack:clean
	@zip -FSr CarlsenBot.zip src .gitignore Makefile Readme.md