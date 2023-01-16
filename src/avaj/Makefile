all:
    find . -name "*.java" > sources.txt
    javac -sourcepath . @sources.txt
    java avaj.Simulator scenario.txt

    # maybe just jaca scenario.txt

clean:
    find . -name '*.class' -delete
    rm simulation.txt sources.txt

cat:
    cat -e simulation.txt
