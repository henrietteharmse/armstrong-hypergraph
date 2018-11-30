package org.armstrong.hypergraph;

import java.util.Set;

public interface HypergraphOperations<N> {

  Hypergraph<N> vee(Hypergraph<N> h1, Hypergraph<N> h2);
  Set<Set<N>> vee(Set<Set<N>> edges1, Set<Set<N>> edges2);
  
  Hypergraph<N> wedge(Hypergraph<N> h1, Hypergraph<N> h2);  
  Set<Set<N>> wedge(Set<Set<N>> edges1, Set<Set<N>> edges2);
  
  Hypergraph<N> minimize(Hypergraph<N> h);
  Set<Set<N>> minimize(Set<Set<N>> edges);
}
