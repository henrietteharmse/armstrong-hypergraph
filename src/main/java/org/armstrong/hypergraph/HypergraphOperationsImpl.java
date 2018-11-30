package org.armstrong.hypergraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HypergraphOperationsImpl<N> implements HypergraphOperations<N> {

  @Override
  public Hypergraph<N> vee(Hypergraph<N> h1, Hypergraph<N> h2) {
    Hypergraph<N> h = new HypergraphImpl<N>();
    
    h.addVertices(h1.getVertices());
    h.addVertices(h2.getVertices());
    
    h.addEdges(vee(h1.getEdges(), h2.getEdges()));
    
    return h;
  }
  
  @Override
  public Set<Set<N>> vee(Set<Set<N>> edges1, Set<Set<N>> edges2){
    Set<Set<N>> edges = new HashSet<Set<N>>();  
    
    edges.addAll(edges1);
    edges.addAll(edges2);
    
    return edges;
  }

  @Override
  public Hypergraph<N> wedge(Hypergraph<N> h1, Hypergraph<N> h2) {
    Hypergraph<N> h = new HypergraphImpl<N>();
    
    h.addVertices(h1.getVertices());
    h.addVertices(h2.getVertices());
    
    h.addEdges(wedge(h1.getEdges(), h2.getEdges()));
   
    return h;
  }
  
  @Override
  public Set<Set<N>> wedge(Set<Set<N>> edges1, Set<Set<N>> edges2) {
    Set<Set<N>> edges = new HashSet<Set<N>>(); 
    
    for (Iterator iterator1 = edges1.iterator(); iterator1.hasNext();) {
      Set<N> e1 = (Set<N>) iterator1.next();
      for (Iterator iterator2 = edges2.iterator(); iterator2.hasNext();) {
        Set<N> e2 = (Set<N>) iterator2.next();
        Set<N> e = new HashSet<N>();
        e.addAll(e1);
        e.addAll(e2);
        edges.add(e);
      }
    }
    return edges;
  }
  
  @Override
  public Set<Set<N>> minimize(Set<Set<N>> edges) {
    Set<Set<N>> minEdges = new HashSet<Set<N>>();    
    minEdges.addAll(edges);
    
    for (Iterator iterator1 = edges.iterator(); iterator1.hasNext();) {
      Set<N> edge1 = (Set<N>) iterator1.next();
      for (Iterator iterator2 = minEdges.iterator(); iterator2.hasNext();) {
        Set<N> edge2 = (Set<N>) iterator2.next();
        if (!edge1.equals(edge2)) {
          if (edge2.containsAll(edge1)) {
            iterator2.remove();
          } 
        }
      }      
    }
    
    return minEdges;
  }

  @Override
  public Hypergraph<N> minimize(Hypergraph<N> h) {
    Hypergraph<N> minh = new HypergraphImpl<N>();
    
    minh.addVertices(h.getVertices());
    minh.addEdges(minimize(h.getEdges()));

    return minh;
  }
}
