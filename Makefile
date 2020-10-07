all:
	javac *.java
	java Cipher

analyze:
	java Analyze $(ARGS)
decode:
	java Decode $(ARGS)
decode2:
	java Decode2 $(ARGS)

clean: 
	rm *.class