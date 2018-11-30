package org.armstrong.hypergraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HypergraphImpl<N> implements Hypergraph<N> {
  private Set<N> vertices;
  private Set<Set<N>> edges; 
  
  public HypergraphImpl(Set<N> vertices, Set<Set<N>> edges) {
    super();
    this.vertices = vertices;
    this.edges = edges;
  }

  public HypergraphImpl() {
    super();
    vertices = new HashSet<N>();
    edges = new HashSet<Set<N>>();
  }

  public Set<N> getVertices() {
    return vertices;
  }

  @Override
  public void addVertex(N vertex) {
    vertices.add(vertex);
  }

  @Override
  public void addVertices(Set<N> vertices) {
    this.vertices.addAll(vertices);    
  }
  
  
  @Override
  public Set<Set<N>> getEdges() {
    return edges;
  }

  @Override
  public void addEdges(Set<Set<N>> edges) {
    this.edges.addAll(edges);
  }

  @Override
  public void addEdge(Set<N> edge) {
    edges.add(edge);
  }

  @Override
  public Hypergraph<N> minimize() {
    HypergraphOperations<N> op = new HypergraphOperationsImpl<N>();
    return op.minimize(this);
  }
  
  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((edges == null) ? 0 : edges.hashCode());
    result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HypergraphImpl other = (HypergraphImpl) obj;
    if (edges == null) {
      if (other.edges != null)
        return false;
    } else if (!edges.equals(other.edges))
      return false;
    if (vertices == null) {
      if (other.vertices != null)
        return false;
    } else if (!vertices.equals(other.vertices))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "HypergraphImpl [vertices=" + vertices + ", edges=" + edges + "]";
  }  
  
  
}
