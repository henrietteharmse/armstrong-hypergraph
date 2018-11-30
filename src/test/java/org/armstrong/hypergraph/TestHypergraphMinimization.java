package org.armstrong.hypergraph;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.armstrong.hypergraph.Hypergraph;
import org.armstrong.hypergraph.HypergraphImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@RunWith(Parameterized.class)
public class TestHypergraphMinimization {
  private static Logger logger = LoggerFactory.getLogger(TestHypergraphMinimization.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  
  private Hypergraph<String> input;
  private Hypergraph<String> expectedOutput;
  
  
  @Parameters
  public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] { 
          testDataSet1(),
          testDataSet2(),
          testDataSet3()
         });
  }
  
  public TestHypergraphMinimization(Hypergraph<String> input, Hypergraph<String> expectedOutput) {
    super();
    this.input = input;
    this.expectedOutput = expectedOutput;
  }
  
  @Test
  public void testMinimization() {
    Hypergraph<String> actualOutput = null;
    try {
      actualOutput = input.minimize();
      logger.trace("actualOutput = " + actualOutput);
    }  catch (Throwable t) {
      logger.error(WTF_MARKER, t.getMessage(), t);
    }
    Assert.assertEquals(expectedOutput, actualOutput);
  }
  
    
  private static Object[] testDataSet1() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Hypergraph<String> expectedOutput = new HypergraphImpl<String>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("a", "b", "c", "d", "e")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a")),
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("a", "b", "c")),
        new HashSet<String>(Arrays.asList("a", "d")),
        new HashSet<String>(Arrays.asList("a", "d", "e")))));
    
    expectedOutput.addVertices(input.getVertices());
    expectedOutput.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a")))));

    
    return new Object[]{input, expectedOutput};
  }
  
  private static Object[] testDataSet2() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Hypergraph<String> expectedOutput = new HypergraphImpl<String>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("a", "b", "c", "d")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("a", "c")),
        new HashSet<String>(Arrays.asList("c", "d")))));
    
    expectedOutput.addVertices(input.getVertices());
    expectedOutput.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("a", "c")),
        new HashSet<String>(Arrays.asList("c", "d")))));

    
    return new Object[]{input, expectedOutput};
  }
  
  private static Object[] testDataSet3() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Hypergraph<String> expectedOutput = new HypergraphImpl<String>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("a", "b", "c", "d", "e")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("a", "b", "c")),
        new HashSet<String>(Arrays.asList("a", "b", "d")),
        new HashSet<String>(Arrays.asList("c", "d", "e")))));
    
    expectedOutput.addVertices(input.getVertices());
    expectedOutput.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("c", "d", "e")))));

    
    return new Object[]{input, expectedOutput};
  }   
  
}
