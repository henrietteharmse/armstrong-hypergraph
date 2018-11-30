package org.armstrong.hypergraph;

import java.util.Set;

public interface Hypergraph<N> {
  Set<N> getVertices();
  Set<Set<N>> getEdges(); 
   
  void addVertex(N vertex);
  void addVertices(Set<N> vertices);
  void addEdge(Set<N> edge);
  void addEdges(Set<Set<N>> edges); 
  
  
  Hypergraph<N> minimize();
}
