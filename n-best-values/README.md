# Storing N best values efficiently

## The idea

This problem comes from a much-passed-around interview question, originally about choosing a data structure to store the N closest points to a given origin, where N is some number much smaller than the total number of points in the data set (these will be supplied one at a time, via individual function calls). If you'd like to think about what data structure you'd choose, do that before reading on...

The solution explored here is to use a [max-heap data structure](https://en.wikipedia.org/wiki/Heap_(data_structure)) to store the N best points so far (you can assume for convenience that N is one less than some power of 2, if it helps). You could implement the heap structure using references/pointers in the heap, an array-based representation, or even a mathematical tree (but the code below performs in-place updates to the nodes). Whatever your implementation, it should be possible to get from a node to its left and right children (if any) and to its parent (if any) in the tree structure. See also the bonus task; although we focus on only one operation here, it is important to be able to conveniently traverse and update the tree both top-down and bottom-up.

For simplicity, we'll store integers rather than pairs of coordinates; the smallest N integer values seen so far should be kept in our data structure (once we've already seen at least N values).

## Processing points when the heap is full

For a max-heap with fixed size, *once the heap is full* (stores N points), one can efficiently process the next integer value using a `processWhenFull` procedure (taking the integer as one of its parameters), implementing the following high-level algorithm:
1. Compare the value with that stored in the root; unless it's smaller, we can simply discard it.
2. If the value is smaller than that in the root, directly *replace* the root value with this value.
3. This replacement may have broken the data structure invariants; check whether the newly-written value is smaller than either of its children (if they exist). If so, *swap* this value with that of the largest of the two children.
4. Repeat step 3 with the lower of the two previously-swapped nodes.

## The tasks

Implement `processWhenFull` using your language and data structure representation of choice, and verify as many as possible of the following properties:

* Crash-freedom / memory safety of the implementation; the code will not throw any kind of runtime error.
* The tree shape is preserved; before and after executing `processWhenFull` the tree will have the same number of nodes with the same depth, children etc. The precise definition of this property depends on your representation, but it should be enough to imply that if the heap is initially a full binary tree, it will be so after the operation.
* The `processWhenFull` procedure preserves the heap's invariants; assuming the structure is initially a valid heap, it should be one afterwards.
* The `processWhenFull` procedure always terminates.
* After executing `processWhenFull`, the data structure stores the N smallest values from those initially stored plus the value passed as parameter.

  
## More Information

* I plan to upload links to a sample solution verifying all but the last property (encoded in [Viper](viper.ethz.ch)). For an additional challenge, I can also get hold of a more-generic implementation in Java, which is parametric with the type of values stored and their comparator (but I need to check with the author).

* Contact person: [Alex Summers](http://people.inf.ethz.ch/summersa/)
