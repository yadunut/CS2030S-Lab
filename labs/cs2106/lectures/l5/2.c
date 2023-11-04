#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/shm.h>

int main() {
    int shmid, input, *shm;
  printf("Shared memory id: ");
  scanf("%d", &shmid);

  shm = (int*) shmat(shmid, NULL, 0);
  if (shm == (int*) -1) {
    printf("Error: Cannot attach!\n");
    exit(1);
  }

  for(int i = 0; i < 3; i++) {
    scanf("%d", &input);
    shm[i+1] = input;
  }
  shm[0] = 1;

  shmdt((char*) shm);
  return 0;

}
