#include "mymalloc.h"

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/_types/_null.h>

#include "llist.h"

char _heap[MEMSIZE] = {0};
TNode *_memlist = NULL; // To maintain information about length

// Do not change this. Used by the test harness.
// You may however use this function in your code if necessary.
long get_index(void *ptr) {
  if (ptr == NULL)
    return -1;
  else
    return (long)((char *)ptr - &_heap[0]);
}

// Allocates size bytes of memory and returns a pointer
// to the first byte.
void *mymalloc(size_t size) {
  if (_memlist == NULL) {
    TData *data = malloc(sizeof(TData));
    data->occupied = false;
    data->size = MEMSIZE;
    _memlist = make_node(0, data);
  }

  // loop through the list to find a free block
  // if found, split the block and return the pointer
  // if not found, return NULL
  TNode *curr = _memlist;
  while (curr != NULL) {
    TData *data = curr->pdata;
    if (data->occupied == false && data->size >= size) {
      data->occupied = true;
      if (data->size > size) {
        // 	Create a new node with the leftover space
        TData *newData = malloc(sizeof(TData));
        newData->occupied = false;
        newData->size = data->size - size;
        TNode *newNode = make_node(curr->key + size, newData);
        // insert new node after the current one in the list.
        insert_node(&_memlist, newNode, 0);
        // change the size of the current node.
        data->size = size;
      }
      return &_heap[curr->key];
    }
    curr = curr->next;
  }

  return NULL;
}

// Frees memory pointer to by ptr.
void myfree(void *ptr) {
  if (ptr == NULL)
    return;
  long index = get_index(ptr);
  TNode *node = find_node(_memlist, index);
  if (node == NULL)
    return;
  node->pdata->occupied = false;
  // merge with the next node if it is free
  while (node->next != NULL && node->next->pdata->occupied == false) {
    node->pdata->size += node->next->pdata->size;
    merge_node(_memlist, node, 1);
  }

  while (node->prev != NULL && node->prev->pdata->occupied == false) {
    TNode *prev = node->prev;
    prev->pdata->size += node->pdata->size;
    merge_node(_memlist, node, 0);
  }
}

void print_node(TNode *node) {
  printf("Status: %s Start index: %d Length: %zu\n",
         node->pdata->occupied ? "ALLOCATED" : "FREE", node->key,
         node->pdata->size);
}

void print_memlist() { process_list(_memlist, print_node); }
