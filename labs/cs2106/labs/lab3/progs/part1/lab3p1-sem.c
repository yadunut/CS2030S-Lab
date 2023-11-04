#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>

#define NUM_PROCESSES 5

int main() {
  int shmid;
  int i, j, pid;
  sem_t* sems[NUM_PROCESSES];

  for (int i = 0; i < NUM_PROCESSES; i++) {
    shmid = shmget(IPC_PRIVATE, sizeof(sem_t), IPC_CREAT | 0600);
    sems[i] = (sem_t*)shmat(shmid, NULL, 0);
  }

  sem_post(sems[0]);

  for (int i = 0; i < NUM_PROCESSES; i++) sem_init(sems[i], 1, 0);
  // allow the first process to run
  sem_post(sems[0]);

  for (i = 0; i < NUM_PROCESSES; i++) {
    if ((pid = fork()) == 0) {
      break;
    }
  }

  if (pid == 0) {
    sem_wait(sems[i]);
    printf("I am child %d\n", i);

    for (j = i * 10; j < i * 10 + 10; j++) {
      printf("%d ", j);
      fflush(stdout);
      usleep(250000);
    }
    printf("\n\n");
    sem_post(sems[i + 1]);
  } else {
    for (i = 0; i < NUM_PROCESSES; i++) wait(NULL);
  }
}
