# VirtJena literal search malfunction regenerator

## Description
I can add literals in the graph with Jena. They are queriable in ISQL but not in Jena. This code regenerates the result.
1. Add triples with Literal
2. Query through a simple SPARQL.
The correct behavior should print "success!" and "failed!" for the incorrect.

The same SPARQL query works in ISQL.

## Tested Configuration
- Ubuntu 16.04
- Jena 3.0.0, Jena 3.0.1
- VirtJena 3
- Virt jdbc 4
- Java openjdk 1.8

## Procedure
1. ``mvn install``
2. ``run.sh``

