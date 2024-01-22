.PHONY: run test

run: harness-wf
	./harness-wf

test: testmalloc
	./testmalloc

harness-wf: harness-wf.c llist.c mymalloc.c
	gcc $^ -o $@

testmalloc: testmalloc.c mymalloc.c llist.c
	gcc $^ -o $@
