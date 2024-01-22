%% In Prolog, atoms, numbers, and pairs (lists)
%% are data structures. Furthermore, We can construct a new
%% data structure using "function" symbols that are not
%% further interpreted. So
%% f(10, 20, 30)    
%% is a data structure with the label 'f' and three components,
%% the numbers 10, 20, and 30.
    
%% A binary number tree is either null or
%% a node(T1, V, T2) where T1 is
%% a binary number tree, V is an number, and T2 is a
%% binary number tree.

%% Note that 'node' is the label, and T1, V, and T2 are
%% the components of a node data structure.
    
%% Examples:

tree1(
    node(node(node(null,
		   1,
		   null),
	      2,
	      node(null,
		   3,
		   null)),
	 4,
	 node(node(null,
		   5,
		   null),
	      6,
	      node(null,
		   7,
		   null)))
).

tree2(null).
tree3(node(null,
	   4,
	   null)).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question 1, 5 points
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Consider the following predicate leftmost_element:

leftmost_element(node(null, V, _), V).
leftmost_element(node(T1, _, _), W) :-
    leftmost_element(T1, W).

test_leftmost_element :-
    tree1(T1), leftmost_element(T1, 1),
    tree2(T2), not(leftmost_element(T2,_)),
    tree3(T3), leftmost_element(T3, 4).

%% Explain the behavior of the query
%% leftmost_element(X, 4) in three or four sentences:
%% T = node(null, 4, _) ;
%% T = node(node(null, 4, _), _, _) ;
%% T = node(node(node(null, 4, _), _, _), _, _) ;
%% T = node(node(node(node(null, 4, _), _, _), _, _), _, _) 

%% Write your answer in comments here:
%% The first fact, leftmost_element is the base case of this recursive rule. It 
%% catches the case where there are no more left elements in the Tree, by checking
%% that the left most element is null. The other rule matches on anything which 
%% has a left element in the Node, and applies the same rule on the left most element.
%% 
%% 
%% 
%% 
%% 
%% 
%% 
%% 
    
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question 2, 10 points
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% The depth of a tree is defined to be the longest path
%% from the root of the tree to any value, and 0 if there
%% are no values in the tree.

%% Write a predicate depth(T, X) that holds if X is the
%% depth of T.

%%%%%%%%%
%% your solution goes here

depth(null, 0).
depth(node(Left, _, Right), Res) :- 
  depth(Left, F1), 
  depth(Right, F2), 
  Res is max(F1, F2) + 1.
%%%%%%%%%

test_depth :-
    tree1(T1), depth(T1, 3),
    tree2(T2), depth(T2, 0),
    tree3(T3), depth(T3, 1).

%% test your solution by
%% writing
%% ?- test_depth.    
%% in the SWI-Prolog REPL.
    
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question 3, 10 points
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write a predicate sum(T, S) that computes the sum S
%% of all elements of a tree T.

%%%%%%%%%%%%%%
%% your solution goes here
collatz(1).
collatz(N) :- 
  N > 1,
  Next is N mod (2 =:= 0 -> N // 2; 3*N+1),
  collatz(Next).


sum(null, 0).
sum(node(Left, X, Right), Res) :-
  sum(Left, LeftRes),
  sum(Right, RightRes),
  Res is X + LeftRes + RightRes.
    
%%%%%%%%%%%%%%

test_sum :-
    tree1(T1), sum(T1, 28),
    tree2(T2), sum(T2, 0),
    tree3(T3), sum(T3, 4).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question X, 0 points, just practice
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write a predicate bt_member(T, V) that holds
%% if and only if V is a value in the tree T.

%%%%%%%%%%%%%%
%% solution at the end of this file
%%%%%%%%%%%%%%

test_bt_member :-
    tree1(T1), bt_member(T1, 5),
    tree2(T2), not(bt_member(T2, _)),
    tree3(T3), bt_member(T3, 4).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question Y, 0 points, just practice
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Note that the given trees in tree1, tree2, and tree3
%% are binary search trees: For every node, the values in
%% the left subtree of the node are all smaller than the
%% value, and the values in the right subtree of the tree
%% are all larger than the value.

%% Write a predicate bst_member(T, V) that exploits this fact
%% and that holds if and only if V is a value in the
%% binary search tree T. The number of recursive calls
%% should be limited by the depth of the binary search tree.

%%%%%%%%%%%%%%
%% solution at the end of this file
%%%%%%%%%%%%%%

test_bst_member :-
    tree1(T1), bst_member(T1, 5),
    tree2(T2), not(bst_member(T2, _)),
    tree3(T3), bst_member(T3, 4).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question Z, 0 points, just practice
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write a predicate make_bst(N, T) that makes a binary search
%% tree that contains the integers 1,..,N.
%% Hint: Remember Question 1

%%%%%%%%%%%%%%
%% solution at the end of this file
%%%%%%%%%%%%%%

test_make_bst :-
    make_bst(10, T1), bst_member(T1, 10),
                      not(bst_member(T1, 11)),
    make_bst(100, T2), bst_member(T2, 100),
                      not(bst_member(T2, 101)).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question 4, 10 points
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% Here is a length predicate for lists.
len([], 0).
len([_|T], N) :-
    len(T, N1), N is N1 + 1.

test_length :-
    len([1,2,3,4], 4),
    len([], 0),
    len([1,2,3,4,5,6,7,8,9,10], 10).

%% Write a predicate nth(I, L, V) that holds
%% if and only if V is the nth element of list L,
%% assuming we start counting at 0.
%% 

%%%%%%%%%%%%%%
%% your solution goes here
%% The 0th element of any list is V
nth(0, [V|_], V). 
nth(Idx, [_|Tail], Elem) :-
  Idx > 0, %% Catch for negative indices
  Idx1 is Idx - 1,
  nth(Idx1, Tail, Elem).

% Taufiq
nth(0, [V | _], V) :- !.
nth(I, [_|T], V) :- nth(I1, T, V), I is I1 + 1, I > 0.    

%%%%%%%%%%%%%%

test_nth :-
    nth(0, [1,2,3,4], 1),
    not(nth(0, [], 0)),
    nth(9, [1,2,3,4,5,6,7,8,9,10], 10).
    
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question 5, 10 points
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% Most likely, your nth predicate is not going
%% to work backwards. It doesn't allow you to
%% identify the position of a given value in
%% a list: nth(I, [10,20,30,40], 30) will not bind
%pos(
%% I to the value 2. Do you see why? (no submission)
%%
%% Write a predicate pos(X, L, I) that holds when
%% I is a position at which L has the value X.
%% Thus pos(30, [10,20,30,40], I) should bind
%% I to the value 2.

%% Make sure that your predicate will hold for
%% all positions of the given value X, not just
%% the first one. Thus pos(30, [10, 30, 30, 40], I)
%% should bind I to 1, and upon backtracking, it
%% should bind I to the value 2.

%%%%%%%%%%%%%%
%% your solution goes here


pos(Target, List, Index) :- pos_helper(Target, List, 0, Index).
pos_helper(Target, [Target|_], Acc, Acc).
pos_helper(Target, [_|Tail], Acc, Index) :-
  Acc1 is Acc + 1,
  pos_helper(Target, Tail, Acc1, Index).

% Taufiq
pos(X, [X | _], 0).
pos(X, [_ | T], I) :- pos(X, T, I1), I is I1 + 1.

%%%%%%%%%%%%%%

test_pos :-
    pos(10, [10,20,30,40], 0),
    not(pos(0, [], _)),
    not(pos(0, [1,2,3,4], _)),
    pos(20, [10,20,20,40], 2),
    pos(10, [1,2,3,4,5,6,7,8,9,10], 9).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Question 6, 10 points
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% Write a predicate all_pos(X, L, I) that
%% computes the list of all positions at which
%% L has the value X. Thus
%% all_pos(30, [10, 30, 30, 40], X) should bind
%% X to the list [1, 2].

%%%%%%%%%%%%%%
%% your solution goes here

all_pos(Target, List, Res) :- all_pos_helper(Target, List,0, Res).
all_pos_helper(_, [],_, []).
all_pos_helper(Target, [Target|Tail], Acc, [Acc|Rest]) :-
  Acc1 is Acc + 1,
  all_pos_helper(Target, Tail, Acc1, Rest).

all_pos_helper(Target, [_|Tail], Acc, Rest) :-
  Acc1 is Acc + 1,
  all_pos_helper(Target, Tail, Acc1, Rest).

% Taufiq
all_pos(X, L, []) :- not(member(X, L)).
all_pos(X, L, LI) :- bagof(I, pos(X, L, I), LI).

%%%%%%%%%%%%%%

test_all_pos :-
    all_pos(0, [1,2,3,4], []),
    all_pos(0, [], []),
    all_pos(10, [1,2,3,4,5,6,7,8,9,10], [9]),
    all_pos(4, [1,2,3,4,4,4,4,4,9,10], [3,4,5,6,7]).

%% Solution Question X    
bt_member(node(_, V,_), V).
bt_member(node(T1,_,_), V) :- bt_member(T1, V).	 	 
bt_member(node(_,_,T2), V) :- bt_member(T2, V).

%% Solution Question Y    
bst_member(node(_, V, _), V).
bst_member(node(T1,V,_), W) :- W < V, bst_member(T1, W).
bst_member(node(_,V,T2), W) :- W > V, bst_member(T2, W).

%% Solution Question Z
make_bst(0, null).
make_bst(N, node(T1, N, null)) :-
    N > 0, N1 is N - 1, make_bst(N1, T1).

   
