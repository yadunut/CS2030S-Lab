#include "mymalloc.h"
#include <stdio.h>

void testalloc(long size, char *ptrname, char **ptr) {
  printf("\nAllocating %ld bytes to %s\n", size, ptrname);
  *ptr = mymalloc(size);

  if (*ptr == NULL)
    printf("Allocation failed.\n");
  print_memlist();
}

void testfree(char *ptr, char *ptrname) {
  printf("\nFreeing %s\n", ptrname);
  myfree(ptr);
  print_memlist();
}

int main() {
  char *ptr1, *ptr2, *ptr3, *ptr4, *ptr5;

  testalloc(4, "ptr1", &ptr1);
  testalloc(32, "ptr2", &ptr2);
  testalloc(4, "ptr3", &ptr3);
  testfree(ptr2, "ptr2");
  testalloc(24, "ptr2", &ptr2);
  testalloc(6, "ptr4", &ptr4);
  testfree(ptr2, "ptr2");
  testalloc(32, "ptr5", &ptr5);
  testfree(ptr1, "ptr1");
  testfree(ptr2, "ptr2");
  testfree(ptr3, "ptr3");
  testfree(ptr4, "ptr4");
  testfree(ptr5, "ptr5");
}
