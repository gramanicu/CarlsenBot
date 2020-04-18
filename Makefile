# Copyright 2020 Grama Nicolae

.PHONY: clean run
.SILENT: clean

# Install dependencies
install:
	sudo apt update
	sudo apt-get install -y xboard
	sudo apt install openjdk-13-jdk

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
	@xboard -debug -nameOfDebugFile debug.txt -fcp "java -jar CarlsenBot.jar"

# Starts xboard using the bot and make it play against fairymax bot
xboard2: build
	@xboard -debug -nameOfDebugFile debug.txt -fcp "make run" -scp "fairymax" -secondInitString "new\nrandom\nsd 2\n" -tc 5 -inc 2 -autoCallFlag true -mg 10 -sgf partide.txt -reuseFirst false

# Deletes the "out" directory and sources.txt
clean:
	@rm -rdf out sources.txt debug.txt

# Creates an archive of the project
pack:clean
	@zip -FSr CarlsenBot.zip src .gitignore Makefile Readme.md
