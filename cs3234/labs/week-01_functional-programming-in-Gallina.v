(* week-01_functional-programming-in-Gallina.v *)
(* LPP 2024 - CS3234 2023-2024, Sem2 *)
(* Olivier Danvy <danvy@yale-nus.edu.sg> *)
(* Version of 18 Jan 2024, with specifications *)
(* was: *)
(* Version of 18 Jan 2024 *)

(* ********** *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* name: 
   e-mail address: 
   student number: 
 *)

(* ********** *)

Check 0.

Check O.

(* Note: "nat" is the type of natural numbers. *)

(* ********** *)

Check 1.

Check (S 0).

Check (S O).

(* ********** *)

Check 2.


Check (S (S 0)).

Check (S (S O)).

(* ********** *)

Check 3.

Compute 3.

(* Note: natural numbers are self-evaluating. *)

(* ********** *)

Compute (4 + 6).

Check (4 + 6).

(* ********** *)

Compute (plus 4 6).

Check (plus 4 6).

(* Note: infix + is syntactic sugar for plus. *)

(* ********** *)

Check (plus 4).

(* Note: plus refers to a library function. *)

Compute (plus 4).
Compute (plus 3).
Compute (plus 2).
Compute (plus 1).
Compute (plus 0).

(* Note: functions are written as in OCaml,
   with the keyword "fun" followed by the formal parameter
   (and optionally its type), "=>", and the body. *)

Compute (fun m : nat => S m).


(*
   For comparison,
     fun m : nat => S m
   would be written
     fun m => succ m
   or
     fun m => m + 1
   or
     fun m => 1 + m
   in OCaml and
     (lambda (m) (1+ m))
   in Scheme.
 *)

Compute ((fun m : nat => S m) 3).

(* ********** *)

Definition three := 3.

Check three.

Compute three.

Definition ten := 4 + 6.

Check ten.

Compute ten.

(* ********** *)

(* The following definitions are all equivalent: *)

(* This adds type definitions to the definition*)
Definition succ_v0 : nat -> nat :=
  fun m : nat => S m.

(* Without Type definitions *)
Definition succ_v1 :=
  fun m => S m.

(* defines the variable m as part of params *)
Definition succ_v2 (m : nat) :=
  S m.

(* defines the variable m as part of params and specifies return type *)
Definition succ_v3 (m : nat) : nat :=
  S m.

(* infers the input tpye and return type from function S *)
Definition succ_v4 m :=
  S m.
Check S.

(* Note: the definition of succ_v3 is the recommended one here. *)

(* Note: variables are defined once and for all in a file. *)

(* ********** *)

(* Ditto for the following definitions: *)

Definition zerop_v0 : nat -> bool :=
  fun n =>
    match n with
    | O =>
      true
    | S n' =>
      false
    end.

Compute (zerop_v0 0). (* = true : bool *)
Compute (zerop_v0 7). (* = false : bool *)

Definition zerop_v1 (n : nat) : bool :=
  match n with
  | O =>
    true
  | S n' =>
    false
  end.

Compute (zerop_v1 0). (* = true : bool *)
Compute (zerop_v1 7). (* = false : bool *)

(* ********** *)

(* The addition function: *)

(* Unit tests: *)

Definition test_add (candidate: nat -> nat -> nat) : bool :=
  (Nat.eqb (candidate 0 0) 0)
  &&
  (Nat.eqb (candidate 0 1) 1)
  &&
  (Nat.eqb (candidate 1 0) 1)
  &&
  (Nat.eqb (candidate 1 1) 2)
  &&
  (Nat.eqb (candidate 1 2) 3)
  &&
  (Nat.eqb (candidate 2 1) 3)
  &&
  (Nat.eqb (candidate 2 2) 4)
  &&
  (* commutativity: *)
  (Nat.eqb (candidate 2 10) (candidate 10 2))
  &&
  (* associativity: *)
  (Nat.eqb (candidate 2 (candidate 5 10))
     (candidate (candidate 2 5) 10))
(* etc. *)
.

(* Testing the unit-test function: *)

Compute (test_add plus).

(* Version 1: lambda-dropped *)

Definition add_v1 (n j : nat) : nat :=
  let fix visit i :=
    match i with
      O =>
      j
    | S i' =>
      S (visit i')
    end
  in visit n.

Compute (test_add add_v1).

(* Version 2: recursive, lambda lifted *)

Fixpoint add_v2 (i j : nat) : nat :=
  match i with
    O =>
    j
  | S i' =>
    S (add_v2 i' j)
  end.

Compute (test_add add_v2).

(* Version 3: tail recursive *)

Fixpoint add_v3 (i j : nat) : nat :=
  match i with
    O =>
    j
  | S i' =>
    add_v3 i' (S j)
  end.

Compute (test_add add_v3).

(* ********** *)

(* The multiplication function: *)

(*
   Given an integer j,
 
   * base case: multiplying 0 and j yields 0;
 
   * induction step: given a number i' such that multiplying it and j yields ih
     (which is the induction hypothesis),
     multiplying S i' and j should yield j + ih.
 *)

(* ***** *)

(* Unit tests: *)

Definition test_mult (candidate: nat -> nat -> nat) : bool :=
  (Nat.eqb (candidate 0 0) 0)
  &&
  (Nat.eqb (candidate 0 1) 0)
  &&
  (Nat.eqb (candidate 1 0) 0)
  &&
  (Nat.eqb (candidate 2 1) 2)
  &&
  (Nat.eqb (candidate 2 2) 4)
  &&
  (Nat.eqb (candidate 4 3) (4+4+4))
  &&
  (let x := 5 in
   let y := 6 in
   (Nat.eqb (candidate x y) (candidate y x)))
  &&
  (let x := 5 in
   let y := 6 in
   let z := 7 in
   (Nat.eqb (candidate x (candidate y z)) (candidate z (candidate x y))))
  &&
  (
    (* Testing the inductive step *)
    let j := 10 in
    let i := 3 in
    Nat.eqb (candidate (S i) j) (j + candidate i j))
.

(* ***** *)

(* Testing the unit-test function: *)


Compute (test_mult mult).

(* ***** *)

(* Version 1: lambda-dropped *)


Definition mult_v1 (n j : nat) : nat :=
  let fix visit i :=
    match i with
    | 0 => 0
    | S i' => j + (visit i')
    end
  in visit n.

Compute (test_mult mult_v1).

(* ***** *)

(* Version 2: lambda-lifted *)

Fixpoint mult_v2 (i j : nat) : nat :=
  match i with
  | 0 => 0
  | (S i') => j + mult_v2 i' j
  end.

Compute (test_mult mult_v2).
Compute (mult_v2 2 6).

(* ***** *)

(* Version 3: lambda-dropped and tail recursive with an accumulator *)

Definition mult_v3 (n j : nat) : nat :=
  let fix visit i a:=
    match i with
    | 0 => a
    | (S i') => visit i' a + j
    end
  in visit n 0.
Compute (mult_v3 2 6).
Compute (test_mult mult_v3).

(* ***** *)

(* Version 4: lambda-lifted and tail recursive with an accumulator *)

Fixpoint mult_v4_aux (i j a : nat) : nat :=
  match i with
  | 0 => a
  | S i' => mult_v4_aux i' j (a+j)
  end.

Definition mult_v4 (n j : nat) : nat :=
  mult_v4_aux n j 0.

Compute (test_mult mult_v4).

(* ********** *)

(* The exponentiation function: *)

(*
   Given an integer x,
 
   * base case: exponentiating x with 0 yields 1;
 
   * induction step: given a number i' such that exponentiating x with i' it and j yields ih
     (which is the induction hypothesis),
     exponentiating x with S i' should yield x * ih.
 *)

(* Unit tests: *)


Definition test_power (candidate: nat -> nat -> nat) : bool :=
  (Nat.eqb (candidate 0 0) 1)
  &&
  (Nat.eqb (candidate 0 1) 0)
  &&
  (Nat.eqb (candidate 1 0) 1)
  &&
  (Nat.eqb (candidate 1 1) 1)
  &&
  (Nat.eqb (candidate 1 2) 1)
  &&
  (Nat.eqb (candidate 2 1) 2)
  &&
  (Nat.eqb (candidate 2 3) 8)
  &&
  (Nat.eqb (candidate 3 4) 81)
  &&
  (Nat.eqb (candidate 3 4) (3*3*3*3)).
(* ***** *)

(* Version 1: lambda-dropped *)


Definition power_v1 (x n : nat) : nat :=
  let fix visit i :=
    match i with
    | 0 => 1
    | S i' => x * visit i'
    end
  in visit n.

Compute (test_power power_v1).

(* ***** *)

(* Version 2: lambda-lifted *)


Fixpoint power_v2 (x n : nat) : nat :=
  match n with
  | 0 => 1
  | S n' => x * power_v2 x n'
  end.

Compute (test_power power_v2).

(* ***** *)

(* Version 3: lambda-dropped and tail recursive with an accumulator *)


Definition power_v3 (x n : nat) : nat :=
  let fix visit i a :=
    match i with
    | O => 1
    | S i' => visit i' a * x
    end
  in visit n 1.

Compute (test_power power_v3).

(* ***** *)

(* Version 4: lambda-lifted and tail recursive with an accumulator *)

Fixpoint power_v4_aux (x i a : nat) : nat :=
  match i with
  | O => a
  | S i' => power_v4_aux x i' x * a
  end.

Definition power_v4 (x n : nat) : nat :=
  power_v4_aux x n 1.

Compute power_v4 2 5.

Compute (test_power power_v4).

(* ********** *)

(* The factorial function: *)

(*
 * base case: the factorial of 0 is 1;
 
   * induction step: given a number i' such that the factorial of i' is ih
     (which is the induction hypothesis),
     the factorial of S i' is (S i') * ih.
 *)

(* Unit tests: *)

Definition test_fac (candidate: nat -> nat) : bool :=
  (Nat.eqb (candidate 0) 1)
  &&
  (Nat.eqb (candidate 1) 1)
  &&
  (Nat.eqb (candidate 2) 2)
  &&
  (Nat.eqb (candidate 3) 6)
  &&
  (Nat.eqb (candidate 4) 24)
  &&
  (Nat.eqb (candidate 5) 120)
  &&
  (Nat.eqb (candidate 6) 720)
  &&
  (Nat.eqb (candidate 7) 5040)
  &&
  (Nat.eqb (candidate 8) 40320).

(* ***** *)

(* Version 1: recursive *)

Fixpoint fac_v1 (n : nat) : nat :=
  match n with
  | 0 => 1
  | S n' => n * fac_v1 n'
  end.

Compute (test_fac fac_v1).

(* ***** *)

(* Version 2: tail recursive with an accumulator *)

Fixpoint fac_v2_aux (n a : nat) : nat :=
  match n with
  | 0 => a
  | S n' => fac_v2_aux n' a * n
  end.
Definition fac_v2 (n : nat) : nat :=
  fac_v2_aux n 1.

Compute (test_fac fac_v2).

(* ********** *)

(* The fibonacci function: *)

(*
 * base case #1: the fibonacci number at index 0 is 0
 
   * base case #2: the fibonacci number at index 1 is 1
 
   * induction step: given a number i' such that
     the fibonacci number at index i' is fib_i' (which is the first induction hypothesis)
     and
     the fibonacci number at index S i' is fib_Si' (which is the first induction hypothesis),
     the fibonacci number at index S (S i') is fib_i' + fib_Si'
 *)

(* ***** *)

(* Unit tests: *)


Definition test_fib (candidate: nat -> nat) : bool :=
  (Nat.eqb (candidate 0) 0)
  &&
  (Nat.eqb (candidate 1) 1)
  &&
  (Nat.eqb (candidate 2) 1)
  &&
  (Nat.eqb (candidate 3) 2)
  &&
  (Nat.eqb (candidate 4) 3)
  &&
  (Nat.eqb (candidate 5) 5)
  &&
  (Nat.eqb (candidate 6) 8)
  &&
  (Nat.eqb (candidate 7) 13)
  &&
  (Nat.eqb (candidate 8) 21)
.

(* ***** *)

(* Version 1: recursive *)

Fixpoint fib_v1 (n : nat) : nat :=
  match n with
  | 0 => 0
  | S n' => match n' with
            | 0 => 1
            | S n'' => fib_v1 n' + fib_v1 n''
            end
  end.

(* hint: make sure to read the interludes in the exercise chapter *)

Compute (test_fib fib_v1).

(* ********** *)

(* The even predicate: *)

Definition bool_eqb (b1 b2 : bool) : bool :=
  match b1 with
  | true =>
    match b2 with
    | true =>
      true
    | false =>
      false
    end
  | false =>
    match b2 with
    | true =>
      false
    | false =>
      true
    end
  end.

(* Unit tests: *)


Definition test_evenp (candidate: nat -> bool) : bool :=
  (bool_eqb true (candidate 0))
  &&
  (bool_eqb false (candidate 1))
  &&
  (bool_eqb true (candidate 2))
  &&
  (bool_eqb false (candidate 3))
.

(* ***** *)

(* Version 1: recursive *)

Fixpoint evenp_v1 (n : nat) : bool :=
  match n with
  | 0 => true
  | S n' => match n' with
            | 0 => false
            | S n'' => evenp_v1 n''
            end
  end.

Compute (test_evenp evenp_v1).

(* ***** *)

(* Version 2: tail recursive with an accumulator *)

Fixpoint evenp_v2_aux (n : nat)  (a : bool) : bool :=
  match n with
  | 0 => a
  | S n' => if a then evenp_v2_aux n' false else evenp_v2_aux n' true
  end.

Definition evenp_v2 (n : nat) : bool :=
  evenp_v2_aux n true.

Compute (test_evenp evenp_v2).

(*
...
 *)

(* ********** *)

(* The odd predicate: *)

Definition oddp_v1 (n : nat) : bool :=
  if evenp_v2 n
  then false
  else true.



(*
...
 *)

(* ********** *)

Inductive binary_tree_nat : Type :=
  Leaf_nat : nat -> binary_tree_nat
| Node_nat : binary_tree_nat -> binary_tree_nat -> binary_tree_nat.

Fixpoint beq_binary_tree_nat (t1 t2 : binary_tree_nat) : bool :=
  match t1 with
    Leaf_nat n1 =>
    match t2 with
      Leaf_nat n2 =>
      Nat.eqb n1 n2
    | Node_nat t21 t22 =>
      false
    end
  | Node_nat t11 t12 =>
    match t2 with
      Leaf_nat n2 =>
      false
    | Node_nat t21 t22 =>
      (beq_binary_tree_nat t11 t21) && (beq_binary_tree_nat t12 t22)
    end
  end.

(* ********** *)

(* How many leaves in a given binary tree? *)

(* Unit tests: *)

Definition test_number_of_leaves (candidate: binary_tree_nat -> nat) : bool :=
  (Nat.eqb (candidate (Leaf_nat 1))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Leaf_nat 1)
                         (Leaf_nat 2)))
     2)
(* etc. *)
.

(* ***** *)

(* Version 1: recursive *)

Fixpoint number_of_leaves_v1 (t : binary_tree_nat) : nat :=
  match t with
    Leaf_nat n =>
    1
  | Node_nat t1 t2 =>
    (number_of_leaves_v1 t1) + (number_of_leaves_v1 t2)
  end.

(* ***** *)

Compute (test_number_of_leaves number_of_leaves_v1).

(* Version 2: recursive with an accumulator *)

Definition number_of_leaves_v2 (t: binary_tree_nat) : nat :=
  let fix visit (n : binary_tree_nat) (a : nat) : nat :=
    match n with
    | Leaf_nat _ => S a
    | Node_nat t1 t2 => visit t1 (visit t2 a)
    end
  in visit t 0.

Compute (test_number_of_leaves number_of_leaves_v2).
(* ********** *)

(* How many nodes in a given binary tree? *)

(* Unit tests: *)

Definition test_number_of_nodes (candidate: binary_tree_nat -> nat) : bool :=
  (Nat.eqb (candidate (Leaf_nat 1))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Leaf_nat 1)
                         (Leaf_nat 2)))
     3)
.

(* ***** *)

(* Version 1: recursive *)


Fixpoint number_of_nodes_v1 (t : binary_tree_nat) : nat :=
  match t with
  | Leaf_nat _ => 1
  | Node_nat l r => 1 + (number_of_nodes_v1 l) + (number_of_nodes_v1 r)
  end.


Compute (test_number_of_nodes number_of_nodes_v1).

(* ********** *)

(* What is the smallest leaf in a given binary tree? *)

Compute (Nat.ltb 1 2).
Compute (Nat.leb 1 2).

Check Nat.ltb.

Definition test_smallest_leaf (candidate: binary_tree_nat -> nat) : bool :=
  (Nat.eqb (candidate (Leaf_nat 1))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Leaf_nat 1)
                         (Leaf_nat 2)))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Node_nat (Leaf_nat 5)
                                   (Leaf_nat 4))
                         (Node_nat (Leaf_nat 3)
                            (Leaf_nat 2))))
     2)
.

(*
   Given an Binary Tree t,

   * base case: If leaf node then the smallest value is value of leaf node;

   * induction step: given a number i' such that exponentiating x with i' it and j yields ih
     (which is the induction hypothesis),
     exponentiating x with S i' should yield x * ih.
 *)

Fixpoint smallest_leaf_v1 (t : binary_tree_nat) : nat :=
  match t with
  | Leaf_nat n => n
  | Node_nat l r =>
    let l_v := (smallest_leaf_v1 l) in
    let r_v := (smallest_leaf_v1 r) in
    if Nat.ltb l_v r_v
    then l_v
    else r_v
  end.


Compute (test_smallest_leaf smallest_leaf_v1).

(* ********** *)

(* What is the sum of the payloads in the leaves of a given binary tree? *)

(* Unit tests: *)


Definition test_weight (candidate: binary_tree_nat -> nat) : bool :=
  (Nat.eqb (candidate (Leaf_nat 1))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Leaf_nat 1)
                         (Leaf_nat 2)))
     3)
  &&
  (Nat.eqb (candidate (Node_nat (Node_nat (Leaf_nat 5)
                                   (Leaf_nat 4))
                         (Node_nat (Leaf_nat 3)
                            (Leaf_nat 2))))
     14)
.

(* ***** *)

(* Version 1: recursive *)

Fixpoint weight_v1 (t : binary_tree_nat) : nat :=
  match t with
  | Leaf_nat n =>
    n
  | Node_nat l r =>
    weight_v1 l + weight_v1 r
  end.


Compute (test_weight weight_v1).

(* ***** *)

(* Version 2: recursive with an accumulator *)

Definition weight_v2 (t : binary_tree_nat) : nat :=
  let fix visit n a :=
    match n with
    | Leaf_nat v => a + v
    | Node_nat l r => visit l a + (visit r a)
    end
  in visit t 0.

Compute (test_weight weight_v2).

(* ********** *)

(* What is the length of the longest path from the root of a given binary tree to its leaves? *)

(* ***** *)


Definition test_length_of_longest_path (candidate: binary_tree_nat -> nat) : bool :=
  (Nat.eqb (candidate (Leaf_nat 1))
     0)
  &&
  (Nat.eqb (candidate (Node_nat (Leaf_nat 1)
                         (Leaf_nat 2)))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Node_nat (Leaf_nat 5)
                                   (Leaf_nat 4))
                         (Leaf_nat 2)))
     2)
.

(* ***** *)

(* Version 1: recursive *)


Fixpoint length_of_longest_path_v1 (t : binary_tree_nat) : nat :=
  match t with
  | Leaf_nat v => 0
  | Node_nat l r =>
    let l_v := length_of_longest_path_v1 l in
    let r_v := length_of_longest_path_v1 r in
    if Nat.ltb l_v r_v
    then 1 + r_v
    else 1 + l_v
  end.

Compute (test_length_of_longest_path length_of_longest_path_v1).

(* ********** *)

(* What is the length of the shortest path from the root of a given binary tree to its leaves? *)

(* ***** *)


Definition test_length_of_shortest_path (candidate: binary_tree_nat -> nat) : bool :=
  (Nat.eqb (candidate (Leaf_nat 1))
     0)
  &&
  (Nat.eqb (candidate (Node_nat (Leaf_nat 1)
                         (Leaf_nat 2)))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Node_nat (Leaf_nat 5)
                                   (Leaf_nat 4))
                         (Leaf_nat 2)))
     1)
  &&
  (Nat.eqb (candidate (Node_nat (Node_nat (Leaf_nat 5)
                                          (Node_nat (Leaf_nat 1) (Leaf_nat 2)))
                                (Node_nat (Leaf_nat 5)
                                          (Leaf_nat 4)))
     ) 2)
  .

(* ***** *)

(* Version 1: recursive *)

(* Right now I'm DFS-ing through the tree, is it possible to BFS? *)

Fixpoint length_of_shortest_path_v1 (t : binary_tree_nat) : nat :=
  match t with
  | Leaf_nat v => 0
  | Node_nat l r =>
    let l_v := length_of_shortest_path_v1 l in
    let r_v := length_of_shortest_path_v1 r in
    if Nat.ltb l_v r_v
    then 1 + l_v
    else 1 + r_v
  end.

Compute (test_length_of_shortest_path length_of_shortest_path_v1).

(* ********** *)

(* The mirror function: *)

(* Unit tests: *)

Definition test_mirror (candidate: binary_tree_nat -> binary_tree_nat) : bool :=
  (beq_binary_tree_nat (candidate (Leaf_nat 1))
     (Leaf_nat 1))
  &&
  (beq_binary_tree_nat (candidate (Node_nat (Leaf_nat 1)
                                            (Leaf_nat 2)))
                                  (Node_nat (Leaf_nat 2)
                                  (Leaf_nat 1)))
(* etc. *)
.

(* ***** *)

(* Version 1: recursive *)


Fixpoint mirror_v1 (t : binary_tree_nat) : binary_tree_nat :=
  match t with
  | Leaf_nat v => Leaf_nat v
  | Node_nat l r => Node_nat (mirror_v1 r) (mirror_v1 l)
  end.

Compute (test_mirror mirror_v1).

(* ********** *)

(* Calder mobiles: *)

(*
   base case:
   a leaf is well balanced

   induction step:
   given a first tree t1 that is well balanced
   and a second tree t2 that is well balanced,
   the tree
     Node t1 t2
   is well balanced if t1 and t2 have the same weight
 *)

(* ***** *)

(* Unit tests: *)

(*
...
 *)

(* ***** *)

(* Version 1: recursive *)

Fixpoint calder (t1 t2 : binary_tree_nat) : bool :=
  match t1 with
    | Leaf_nat t1v =>
      match t2 with
        | Leaf_nat t2v => t1v = t2v
        | Node_nat t2l t2r =>
    | Node_nat t1l t1r =>
      match t2 with
        | Leaf_nat t2v =>
        | Node_nat t2l t2r =>

(*
...
 *)

(* challenge: traverse the given tree only once, at most *)

(* ********** *)

(* end of week-01_functional-programming-in-Gallina.v *)
