.PHONY: run

bitmap: bitmap.c testmap.c
	gcc $^ -o $@

run-bitmap: bitmap
	./bitmap

mymalloc: mymalloc.c testmalloc.c bitmap.c
	gcc $^ -o $@

run-mymalloc: mymalloc
	./mymalloc

harness: harness.c mymalloc.c bitmap.c
	gcc $^ -o $@

run-harness: harness
	./harness
