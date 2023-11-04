#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>

#define NUM_PROCESSES 5

int main() {

  int i, j, pid;

  int *turn;
  int shmid;

  shmid = shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT | 0600);
  turn = shmat(shmid, NULL, 0);
  turn[0] = 0;

  for (i = 0; i < NUM_PROCESSES; i++) {
    if ((pid = fork()) == 0) {
      break;
    }
  }

  if (pid == 0) {
    while (turn[0] != i)
      ;
    printf("I am child %d\n", i);

    for (j = i * 10; j < i * 10 + 10; j++) {
      printf("%d ", j);
      fflush(stdout);
      usleep(250000);
    }

    printf("\n\n");
    turn[0] = i + 1;
  } else {
    for (i = 0; i < NUM_PROCESSES; i++)
      wait(NULL);
  }
}
