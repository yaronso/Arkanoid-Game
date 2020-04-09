# 313293532
# soferya
# Yaron Sofer

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*.java 

jar:
	jar cfm ass7game.jar manifest.txt -C bin . -C resources . 

run:
	java -cp biuoop-1.4.jar:bin:resources Ass7Game

bin:
	mkdir bin
