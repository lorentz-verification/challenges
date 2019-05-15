# Deutsch-Schorr-Waite Tree Traversal 

## The problem

The [Deutsch-Schorr-Waite tree traversal algorithm (DSW)](https://doi.org/10.1145/363534.363554) traverses a tree or DAG without requiring an (explicit or implicit) stack, thus only requiring constant additional memory.
Here we consider the [Lindstrom variant](https://doi.org/10.1016/0020-0190(73)90012-4) of this algorithm. 
A Java implementation can be found [here](lindstrom.java).

The verification challenge consists in the following properties of increasing complexity. We assume that the traversal method is invoked with a pointer to the root of a (finite) binary tree.

* Absence of null-pointer dereferences: It is guaranteed that during runtime, never a null pointer will be dereferenced.
* Preservation of shape: Upon termination, the root variable still points to a tree.
* Completeness: Each node of the tree is visited during the traversal.
* Preservation of structure: The original tree is exactly retained after the traversal.
* Termination: The algorithm is going to terminate.

## A solution

All properties but termination can be verified using [ATTESTOR](https://github.com/moves-rwth/attestor), a graph-based tool for analysing Java programs operating on dynamic data structures. It first generates of the abstract state space of the given program employing a user-defined graph grammar that specifies the data structure(s) maintained by the program. In the next step, both structural and functional correctness properties can be verified by means of LTL model checking. The analysis is fully automated, procedure-modular, and provides visual feedback including counterexamples in case of property violations.

Our solution to the Lindstrom verification problem can be found in the separate [ATTESTOR Benchmark Collection](https://github.com/moves-rwth/attestor-examples) in the [CAV 2018 examples folder](https://github.com/moves-rwth/attestor-examples/tree/master/CAV2018Examples/configuration) (see also the corresponding [CAV 2018 tool paper](https://doi.org/10.1007/978-3-319-96142-2_1)).

More concretely, the first four properties listed above are handled as follows:

* [Absence of null-pointer dereferences](https://github.com/moves-rwth/attestor-examples/blob/master/CAV2018Examples/configuration/settings/lindstromTreeTraversal_M.attestor): This is a simple memory-safety property that is checked locally during state-space generation.
* [Preservation of shape](https://github.com/moves-rwth/attestor-examples/blob/master/CAV2018Examples/configuration/settings/lindstromTreeTraversal_S.attestor): This is verified by showing that after traversing the tree, the root variable still refers to a data structure that conforms to the graph grammar for binary trees.
* [Completeness](https://github.com/moves-rwth/attestor-examples/blob/master/CAV2018Examples/configuration/settings/lindstromTreeTraversal_V.attestor): This is established by a "visit" property expressing that each node of the tree is pointed to (at least once) by the cur variable.
* [Preservation of structure](https://github.com/moves-rwth/attestor-examples/blob/master/CAV2018Examples/configuration/settings/lindstromTreeTraversal_N.attestor): This is verified by checking that after traversal, the "neighbourhood" of each node of the tree is maintained, that is the left and right children are identical to those in the original tree.

## More Information

* [ATTESTOR Tool](https://github.com/moves-rwth/attestor)
* [ATTESTOR Benchmark Collection](https://github.com/moves-rwth/attestor-examples)
* [Verification of DSW using TVLA](https://doi.org/10.1007/11823230_17)
* [Challenge 2](https://docs.google.com/document/d/1TvvoRovDQtmuXth6vcWtUNCQCNmzZO9X_DdcxM9aXbQ/edit?usp=drive_web) of [VerifyThis @ ETAPS 2016](http://etaps2016.verifythis.org/)
* Contact person: [Thomas Noll](https://moves.rwth-aachen.de/people/noll/)
