CC = gcc
FLAGS += -Wall -g
PLIBS = array.o
CLIBS = -lfann -lm -L /usr/local/include/

all: train.o test.o

train.o: train.c
	$(CC) $(FLAGS) train.c -o train.exe $(CLIBS)
	
test.o: test.c
	$(CC) $(FLAGS) test.c -o test.exe $(CLIBS)
	
clean:
	rm -f *.exe
