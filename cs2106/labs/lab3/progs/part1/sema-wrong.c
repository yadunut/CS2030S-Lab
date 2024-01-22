//
// Program to create shared semaphores
//

#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
  sem_t sem;
  int pid;

  sem_init(&sem, 1, 0);

  if ((pid = fork()) != 0) {
    printf("Parent! Making my child wait for 1 second.\n");
    sleep(1);
    sem_post(&sem);
  } else {
    sem_wait(&sem);
    printf("Child! Waited 1 second for parent.\n");
  }

  if (pid != 0) {
    wait(NULL);
    sem_destroy(&sem);
  }
}
