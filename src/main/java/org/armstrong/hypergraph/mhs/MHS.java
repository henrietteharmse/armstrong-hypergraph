package org.armstrong.hypergraph.mhs;

import java.util.Set;

import org.armstrong.hypergraph.Hypergraph;

public interface MHS<N> {
  Set<Set<N>> calcMHS(Hypergraph<N> h);
}
