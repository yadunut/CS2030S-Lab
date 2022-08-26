CLASSES := $(wildcard *.java)

default: classes

Lab1:
	java Lab1

classes: $(CLASSES:.java=.class)

%.class : %.java
	javac "$<"

clean:
	$(RM) *.class
