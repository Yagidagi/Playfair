all:
	javac *.java

run:
	java CipherRunner $(ARGS)

clean: 
	rm *.class