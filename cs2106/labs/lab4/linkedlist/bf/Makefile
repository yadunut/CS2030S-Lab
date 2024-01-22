.PHONY: run test

run: harness-bf
	./harness-bf

test: testmalloc
	./testmalloc

harness-bf: harness-bf.c llist.c mymalloc.c
	gcc $^ -o $@

testmalloc: testmalloc.c mymalloc.c llist.c
	gcc $^ -o $@
