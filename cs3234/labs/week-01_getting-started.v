(* week-01_getting-started.v *)
(* LPP 2024 - CS3234 2023-2024, Sem2 *)
(* Olivier Danvy <danvy@yale-nus.edu.sg> *)
(* Version of 18 Jan 2024 *)

(* ********** *)

Require Import Arith.

(* The Arith library offers addition, multiplication, and comparison predicates: *)

Compute (1 + 2).
Compute (plus 1 2).
Check plus.

Compute (2 * 3).
Compute (mult 2 3).
Check mult.

Compute (2 =? 2).
Compute (2 =? 3).
Compute (Nat.eqb 2 3).
Check Nat.eqb.

Compute (2 <? 3).
Check Nat.ltb.

Compute (2 <=? 2).
Check Nat.leb.

(* ********** *)

Require Import Bool.

(* The Bool library offers true, false, comparison, negation, conjunction, and disjunction: *)

Compute true.
Compute false.

Check Bool.eqb.

Check negb.
Compute (negb true).

Check (true && true).
Check (true && false).

Check (false || true).
Check (false || false).

(* ********** *)

(* end of week-01_getting-started.v *)
