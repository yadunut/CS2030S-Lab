parent(a, b).
parent(a, c).
parent(b, d).
parent(b, e).
parent(c, f).
parent(c, g).
parent(f, h).
parent(f, i).

ancestor(X, Y) :- parent(X, Y).
ancestor(X, Y) :- parent(X, Z), ancestor(Z, Y).

factorial(1, 1).
factorial(N, F) :- N > 1, N1 is N - 1, factorial(N1, F1), F is F1 * N.

faciter(1, Res, Res).
faciter(N, Acc, Res) :- N > 1, N1 is N -1, Acc1 is N * Acc, faciter(N1, Acc1, Res).

fib(0, 0).
fib(1, 1).
fib(N, F) :- N > 1, N1 is N-1, N2 is N-2, fib(N1, F1), fib(N2, F2), F is F1 + F2.

fibiter(X, X, F1, F2, F1).
fibiter(X, N, F1, F2, Result) :- X < N, X1 is X + 1, Aux is F1 + F2, fibiter(X1, N, F2, Aux, Result).

head([H|_], H).
tail([_|T], T).

member(X, [X|_]).
member(X, [_|T]) :- member(X, T).

append([], L, L).
append([H|T], L, [H|R]) :- append(T, L, R).

reverse([], []).
reverse([H|T], R) :- 
  reverse(T, R1), append(R1, [H], R).
