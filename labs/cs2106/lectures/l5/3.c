#include <unistd.h>
#include <stdio.h>
#include <string.h>
#define READ_END 0
#define WRITE_END 1

int main() {
  int pipeFd[2], pid, len;

  char buf[100], *str = "Hello There!";

  pipe(pipeFd);

  if ((pid = fork()) > 0) {
    close(pipeFd[READ_END]);
    write(pipeFd[WRITE_END], str, strlen(str) +1);
    close(pipeFd[WRITE_END]);
  } else {
    close(pipeFd[WRITE_END]);
    len = read(pipeFd[READ_END], buf, sizeof(buf));
    printf("Proc %d read: %s\n", pid, buf);
    close(pipeFd[READ_END]);
  }
}
