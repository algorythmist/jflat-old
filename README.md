## What is JFlat

JFlat is a Java API that helps developers map flat files to and from Java Objects. It includes support for CSV, fixed width format, arbitrarily delimited data, and Excel.

JFlat is highly extensible and customizable so you can extend it to support any kind of flat file format. With a minimal configuration, JFlat can read a flat file into a list of Java beans. You can also read with flat file with a callback to implement custom handling.
JFlat framework philosophy

I believe that a successful development framework should have the following characteristics:

* Things that are easy to do without the framework should remain easy to do within the framework.
* Some things that are hard to do without the framework should be easy to do with the framework.
* There should be nothing that is easier to do outside the framework than within it.
* The framework should be lightweight, i.e. not introduce too many dependencies.
* The framework should be extensible. 

JFlat adheres to this philosophy. Reading a CSV file into a list of String items is a relatively easy thing to do and with JFlat you can accomplish it with a single line of code. Reading a flat file into a list of arbitrary Java beans is tedious work, yet it is much simplified by using JFlat. JFlat components communicate through well-defined interfaces. By implementing these interfaces of extending the base classes, programmers can painlessly extend the framework to suit their needs.

## Getting started with JFlat

You can get started by reading this short introduction [Getting started with JFlat](GettingStarted.md) and by browsing the Javadoc
