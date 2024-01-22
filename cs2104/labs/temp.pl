% collatz(1).
% collatz(N) :- 
%   N > 1,
%   Next is (N mod 2 =:= 0 -> N // 2 ; 3 * N + 1),
%   collatz(Next).

collatz(N, CN) :-
  0 is N mod 2,
  CN is (N/2).

collatz(N, CN) :-
  1 is N mod 2,
  CN is (3*N+1).

collatzSeq(1, [1]) :- !.

collatzSeq(N, [N | L]) :-
  collatz(N, CN),
  collatzSeq(CN, L).

