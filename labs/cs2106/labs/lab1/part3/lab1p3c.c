#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct {
    char *name;
    int age;
} TPerson;

TPerson *makeNewNode(char *name, int age) {
    TPerson *p = (TPerson *) malloc(sizeof(TPerson));
    strcpy(p->name, name);
    p->age = age;

    return p;
}

void freeNode(TPerson *node) {
    free(node);
}


#define NUM_PERSONS 3
int main() {
    TPerson persons[NUM_PERSONS] = {{"Tan Ah Kow", 65}, {"Sio Bak Pau", 23},
    {"Aiken Dueet", 21}};
    TPerson *list[NUM_PERSONS];

    int i;

    printf("ADDING PERSONS\n");

    for(i=0; i<NUM_PERSONS; i++) {
        printf("Adding %s aged %d\n", persons[i].name, persons[i].age);
        list[i] = makeNewNode(persons[i].name, persons[i].age);
    }

    printf("\nDELETING PERSONS\n");
    for(i=0; i<NUM_PERSONS; i++) {
        printf("Deleting %s aged %d\n", list[i]->name, list[i]->age);
        freeNode(list[i]);
    }
}

