package org.armstrong.hypergraph.mhs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.armstrong.hypergraph.Hypergraph;
import org.armstrong.hypergraph.HypergraphOperations;
import org.armstrong.hypergraph.HypergraphOperationsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class BergeMHS<N> implements MHS<N> {
  private static Logger logger = LoggerFactory.getLogger(BergeMHS.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  
  
  private Set<Set<N>> calcMHS(Set<N> edge) {
    Set<Set<N>> mhs = new HashSet<Set<N>>();
    
    for (N n : edge) {
      Set<N> e = new HashSet<N>();
      e.add(n);
      mhs.add(e);
    }
    
    return mhs;
  }

  @Override
  public Set<Set<N>> calcMHS(Hypergraph<N> h) {
    Set<Set<N>> prevMHS = new HashSet<Set<N>>();
    HypergraphOperations<N> op = new HypergraphOperationsImpl<N>();
    
    //Order edges E = {e1, ...., en}
    List<Set<N>> orderedEdges = new ArrayList<Set<N>>(h.getEdges().size());
    orderedEdges.addAll(h.getEdges());

    // Tr(H1) = {{v} | v \in e1}
    prevMHS = calcMHS(orderedEdges.get(0));    
    logger.trace("prevMHS[1] = " + prevMHS);
    
    
    //for i = 2, ..., n
    for (int i = 1; i < orderedEdges.size(); i++) {
      Set<Set<N>> mhs = calcMHS(orderedEdges.get(i));
      prevMHS = op.minimize(op.wedge(prevMHS, mhs));
    }
        
    return prevMHS;
  } 

}
