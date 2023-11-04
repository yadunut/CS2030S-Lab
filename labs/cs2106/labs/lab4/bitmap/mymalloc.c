#include "mymalloc.h"
#include "bitmap.h"
#include <stdio.h>
#include <stdlib.h>

char _heap[MEMSIZE] = {0};
unsigned char _bitmap[BITMAP_SIZE] = {};
char lens[MEMSIZE] = {0};

// Do not change this. Used by the test harness.
// You may however use this function in your code if necessary.
long get_index(void *ptr) {
  if (ptr == NULL)
    return -1;
  else
    return (long)((char *)ptr - &_heap[0]);
}

void print_memlist() { print_map(_bitmap, BITMAP_SIZE); }

// Allocates size bytes of memory and returns a pointer
// to the first byte.
void *mymalloc(size_t size) {
  long start = search_map(_bitmap, BITMAP_SIZE, size);
  if (start == -1)
    return NULL;
  allocate_map(_bitmap, start, size);
  lens[start] = size;
  return (void *)(&_heap[start]);
}

// Frees memory pointer to by ptr.
void myfree(void *ptr) {
  if (ptr == NULL)
    return;
  long ptrIdx = get_index(ptr);
  long size = lens[ptrIdx];
  free_map(_bitmap, ptrIdx, size);
  lens[ptrIdx] = 0;
}
