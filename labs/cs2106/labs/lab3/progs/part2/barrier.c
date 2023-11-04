#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>

int nproc = 0;
// count must be a semaphore
int *count;
sem_t *barrier;
sem_t *countMut;
int barrierShmId, countShmId, countMutShmId;

void init_barrier(int numproc) {
  nproc = numproc;
  barrierShmId = shmget(IPC_PRIVATE, sizeof(sem_t), IPC_CREAT | 0600);
  countMutShmId = shmget(IPC_PRIVATE, sizeof(sem_t), IPC_CREAT | 0600);

  countShmId = shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT | 0600);

  barrier = (sem_t *)shmat(barrierShmId, NULL, 0);
  countMut = (sem_t *)shmat(countMutShmId, NULL, 0);

  count = (int *)shmat(countShmId, NULL, 0);
  sem_init(barrier, 1, 0);
  sem_init(countMut, 1, 1);
}

void reach_barrier() {
  sem_wait(countMut);
  *count += 1;
  sem_post(countMut);
  if (*count == nproc) {
    sem_post(barrier);
  } else {
    sem_wait(barrier);
    sem_post(barrier);
  }
}

void destroy_barrier(int my_pid) {
  if (my_pid != 0) {
    sem_destroy(barrier);
    sem_destroy(countMut);
    shmdt(count);
    shmdt(barrier);
    shmdt(countMut);
    shmctl(countShmId, IPC_RMID, 0);
    shmctl(barrierShmId, IPC_RMID, 0);
    shmctl(countMutShmId, IPC_RMID, 0);
    // Destroy the semaphores and detach
    // and free any shared memory. Notice
    // that we explicity check that it is
    // the parent doing it.
  }
}
