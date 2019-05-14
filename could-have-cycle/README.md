# Determining whether a linked list contains a cycle

## The idea

The following method takes a reference to a linked-list node, which (when following `next` fields) may describe either a null-terminated list, or a list with a cycle (note that the cycle may or may not include the original node). The idea is to analyse the code for various properties; ideally including a specification that the returned value is correct in all cases.

The method is written in Viper, but can hopefully be easily translated to other languages. The additional set-typed parameter (which was useful in my specification) is meant to include at least all nodes in the list (you might not need the parameter, in which case it's fine to delete it). It would also be fine to verify some alternative implementation which achieves the same thing.

The code is as follows:
```
method isCyclic(nodes : Set[Ref], root:Ref) 
  returns (cyclic : Bool) // true exactly if the list starting at root contains a cycle
{
  var seen : Set[Ref] := Set(root) // built-in Set[T]
  var current : Ref := root
  while(current.next != null && !(current.next in seen)) 
  {
    seen := seen union Set(current.next)  
    current := current.next
  }
  cyclic := (current.next != null)
}
```

## The tasks

Adapt the code to your language of choice and specify and verify as many as possible of the following properties:

* Crash-freedom / memory safety of the implementation; the code will not throw any kind of runtime error.
* The list shape is preserved; before and after executing the code, the list structure will be unchanged.
* If `false` is returned, a null-terminated acyclic list was originally input.
* If `true` is returned, a list with a cycle (somewhere) in its structure was originally input.
* The code always terminates (you can assume that the set is finite, if this helps).

## More Information

* I plan to upload links to a sample solution verifying all but the last property (encoded in [Viper](viper.ethz.ch)). 

* Contact person: [Alex Summers](http://people.inf.ethz.ch/summersa/)
