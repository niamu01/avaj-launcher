JFLAGS = -g
JC = javac
RM = rm -rf

all: 
	find ./avaj -name "*.java" > sources.txt
	${JC} -sourcepath ./avaj @sources.txt
	java avaj.Simulator scenario.txt 
	
# > simulation.txt

clean: 
	${RM} *.class
	${RM} */*.class

cat:
	cat -e simulation.txt