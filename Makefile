day = $(shell date +'%-d')

new:
	@echo "Creating new file structure for day" $(day)"..."

	@if [ $(day) -lt 10 ] ; then \
  		mkdir src/main/java/dev/janetschel/calendar/year2021/day0$(day); \
  		cd src/main/java/dev/janetschel/calendar/year2021/day0$(day); \
  		cp ../../../../../../../template/Puzzle.java.template Puzzle.java; \
  		cp ../../../../../../../template/README.md.template README.md; \
  	else \
  		mkdir src/main/java/dev/janetschel/calendar/year2021/day$(day); \
  		cd src/main/java/dev/janetschel/calendar/year2021/day$(day); \
		cp ../../../../../../../template/Puzzle.java.template Puzzle.java; \
		cp ../../../../../../../template/README.md.template README.md; \
    fi
	@echo "Files successfully created.. happy hacking :)"
	@echo "INFO: puzzle input still needs to be pasted in manually"
	@git add src/main/java/dev/janetschel/calendar
