% concat/3
concat(L1, [], L1).

concat([H|T_L1], L2, [H|T_L3]) :-
  concat(T_L1, L2, T_L3).
