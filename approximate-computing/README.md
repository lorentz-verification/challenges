# Approximate Computing

## Introduction

A nice summary of *approximate computing* is found on http://approximate.computer/approxbib:

> Approximate computing is the idea that computer systems can let applications trade off accuracy for efficiency. It includes any technique where the system intentionally exposes incorrectness to the application layer in return for conserving some resource.

In other words, certain program instructions might occasionally fail; for example due to low power supply.
It is often possible to model such instructions with probability distributions. That is, with some probability an instruction produces the correct result. With the remaining probability mass, we get incorrect results. For instance, some noise might be added to the correct result.

An interesting aspect of approximate computing is that the notion of program correctness becomes blurred: Rather than proving that a program satisfies a specification, we would like to quantify *how correct* a program is.
If we model uncertainty of instructions with probability distributions, then the question becomes ``how likely is it that a program still produces an adequate result?''


## Example

The program below is a pseudo-code implementation of a procedure that attempts to delete a binary tree with root x. With some probability, which we model by a biased coin flip, however, the program fails to check whether x equals null or not.
It may thus prematuraly abort deletion of subtrees.

    // x is the root of a binary tree with children x.left and x.right
    // bias is a floating pointer number between 0 and 1 that indicates the bias of a coin flip
    procedure delete(Tree x, float bias) {
        if(x != null) {
            // flipCoin(bias) produces true with probability bias and false with probability 1 - bias.
            if(flipCoin(bias)) {
                Tree l = x.left;
                Tree r = x.right;
                delete(l, bias);
                delete(r, bias);
                free(x); 
            }
        }
    }

## Challenge

The task now is to determine the probability that procedure delete successfully deletes the whole tree.
That is, what is the probability that, for a given initial state, the program terminates with an empty heap?

A lower bound on this probability for partial correctness can be derived with a quantitative variant of separation logic (https://dl.acm.org/citation.cfm?id=3290347). 

How do we prove a suitable upper bound for total correctness?
