# Termination under fair scheduling

Proposed by [Robbert Krebbers](https://robbertkrebbers.nl).

## Background

Most verification tools and program logics for concurrency focus on _partial
correctness_, i.e. proving that programs do not get stuck, and once they
terminate, the postcondition holds.

Proving _total correctness_ for concurrency---i.e. that programs in addition to
safety and postcondition validity also terminate--is significantly harder.
Nearly all concurrent programs make use of busy loops, and as such, the usual
notion of termination (i.e. strong normalization) does not suffice. Consider the
following program that illustrates a simplified instance of the problem:

```
let x = ref(false) in
x := true  ||  while (!x != true);
```

This program creates a reference that is initially `false`. One thread will set
it to `true` and the other will perform a busy loop until the reference has
become `true` (`!` is derefencing in this code).

This program does not terminate in general. If an unfair scheduler is used that
never executes the first thread (that sets the reference to `true`) and only
executes the second thread (that performs the busy loop), the program will
never terminate. The notion of termination that should be used for concurrent
programs is termination under the assumption of fair scheduling, i.e. that any
thread eventually makes a step.

This challenge is to investigate the state of the art of verification tools
and logic w.r.t. proving termination under fair scheduling.

## First challenge

Prove that the following program terminate under fair scheduling.

```
let x = ref(false) in
x := true  ||  while (!x != true);
```

Or, alternatively, used `fork` instead of parallel composition (`||`).

```
let x = ref(false) in
fork { x := true };
while (!x != true);
```

## Second challenge

Instead of adding parallel composition as a primitive to a language, it can be
implemented using two auxiliary methods `spawn` and `join`. The method `spawn`
takes a method `f` and runs it in another thread, but it also allocates a
reference cell `c`, which is used to signal that the thread is finished. The
method `join` then performs a busy loop until the forked-off thread is finished.

```
spawn f :=
  let c := ref(None) in
  fork { c := Some (f ()) };
  c

join c :=
  match !c with
  | Some x => x
  | None => join(c)
  end
```

Parallel composition is then simply syntactic sugar:

```
e1 || e2 :=
  let c := spawn(λ_, e2);
  e1;
  join c
```

The goal of this exercise is to prove that when `e1` and `e2` are terminating
under fair scheduling, then so is `e1 || e2`.

In the case of tools for separation logic, the challenge is to prove the
following *total correctness* Hoare triple:

```
 [ P1 ] e1 [ Q1 ]      [ P2 ] e2 [ Q2 ]
----------------------------------------
     [ P1 ∗ P2 ] e1 || e2 [ Q1 ∗ Q2 ]
```

