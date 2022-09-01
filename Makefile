CLASSES := $(wildcard *.java)

default: classes

lab1:
	java Lab1

lab2:
	java Lab2

classes: $(CLASSES:.java=.class)

%.class : %.java
	javac "$<"

clean:
	$(RM) *.class
