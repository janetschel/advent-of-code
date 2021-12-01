day = 12

new:
	@echo "Creating new file structure for day" $(day)"..."

	@if [ $(day) -lt 10 ] ; then \
  		mkdir src/main/java/dev/janetschel/calendar/day0$(day); \
  		cd src/main/java/dev/janetschel/calendar/day0$(day); \
  		cp ../../../../../..//template/Puzzle.java.template Puzzle.java; \
  		cp ../../../../../../../template/README.md.template README.md; \
		touch input.txt; \
  	else \
  		mkdir src/main/java/dev/janetschel/calendar/day$(day); \
  		cd src/main/java/dev/janetschel/calendar/day$(day); \
		cp ../../../../../../../template/Puzzle.java.template Puzzle.java; \
		cp ../../../../../../../template/README.md.template README.md; \
		touch input.txt; \
    fi
	@echo "Files successfully created.. happy hacking :)"
	@echo "INFO: puzzle input still needs to be pasted in manually"
	@git add src/main/java/dev/janetschel/calendar
