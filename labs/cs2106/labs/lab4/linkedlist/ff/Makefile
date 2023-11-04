.PHONY: run test

run: harness-ff
	./harness-ff

test: testmalloc
	./testmalloc

harness-ff: harness-ff.c llist.c mymalloc.c
	gcc $^ -o $@

testmalloc: testmalloc.c mymalloc.c llist.c
	gcc $^ -o $@

