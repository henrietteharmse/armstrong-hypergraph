package org.armstrong.hypergraph.mhs;

import org.armstrong.hypergraph.Hypergraph;
import org.armstrong.hypergraph.HypergraphImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class TestBergeMHS {
  private static Logger logger = LoggerFactory.getLogger(TestBergeMHS.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  
  private Hypergraph<String> input;
  private Set<Set<String>> expectedOutput;

  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
//      testDataSet1(),
//      testDataSet2(),
//      testDataSet3(),
//        testDataSet4(),
      testDataSet5()
    });
  }
  
  public TestBergeMHS(Hypergraph<String> input, Set<Set<String>> expectedOutput) {
    super();
    this.input = input;
    this.expectedOutput = expectedOutput;
  }

  public void testBergeMHS() {
    Set<Set<String>> actualOutput = null;
    try {
      MHS<String> mhs = new BergeMHS<String>();
      actualOutput = mhs.calcMHS(input);
      logger.trace("actualOutput = " + actualOutput);
    }  catch (Throwable t) {
      logger.error(WTF_MARKER, t.getMessage(), t);
    }
//    Assert.assertEquals(expectedOutput, actualOutput);
  }  
  
  private static Object[] testDataSet1() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Set<Set<String>> expectedOutput = new HashSet<Set<String>>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("a", "b", "c", "d")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("c", "d")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "c")),
        new HashSet<String>(Arrays.asList("a", "d")),
        new HashSet<String>(Arrays.asList("b", "c")),
        new HashSet<String>(Arrays.asList("b", "d")))));

    return new Object[]{input, expectedOutput};
  }
  
  private static Object[] testDataSet2() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Set<Set<String>> expectedOutput = new HashSet<Set<String>>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("a", "b")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a")),
        new HashSet<String>(Arrays.asList("a", "b")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a")))));

    return new Object[]{input, expectedOutput};
  }
  
  private static Object[] testDataSet3() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Set<Set<String>> expectedOutput = new HashSet<Set<String>>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("a", "b", "c")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a")),
        new HashSet<String>(Arrays.asList("a", "b")),
        new HashSet<String>(Arrays.asList("c")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "c")))));

    return new Object[]{input, expectedOutput};
  }
  
  private static Object[] testDataSet4() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Set<Set<String>> expectedOutput = new HashSet<Set<String>>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("name", "givenName", "surname", "gender", "description", 
        "birthDate", "birthPlace", "deathDate", "deathPlace")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("deathPlace", "birthDate")),
        new HashSet<String>(Arrays.asList("birthPlace", "deathPlace", "deathDate")),
        new HashSet<String>(Arrays.asList("birthPlace", "birthDate")),
        new HashSet<String>(Arrays.asList("name", "birthDate")),
        new HashSet<String>(Arrays.asList("name", "givenName", "deathPlace")),
        new HashSet<String>(Arrays.asList("name", "birthPlace", "surname")),
        new HashSet<String>(Arrays.asList("name", "surname", "deathDate")),
        new HashSet<String>(Arrays.asList("name", "givenName", "surname")),
        new HashSet<String>(Arrays.asList("description", "deathPlace", "deathDate")),
        new HashSet<String>(Arrays.asList("description", "birthDate")),
        new HashSet<String>(Arrays.asList("name", "givenName", "deathDate")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "c")))));

    return new Object[]{input, expectedOutput};
  }
  
  private static Object[] testDataSet5() {
    Hypergraph<String> input = new HypergraphImpl<String>();
    Set<Set<String>> expectedOutput = new HashSet<Set<String>>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("name", "givenName", "surname", "gender", "description", 
        "birthDate", "birthPlace", "deathDate", "deathPlace")));     
    input.addEdges(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("givenName", "surname", "birthDate", "deathDate")),
        new HashSet<String>(Arrays.asList("givenName", "surname", "description", "birthDate", "birthPlace")),
        new HashSet<String>(Arrays.asList("givenName", "birthDate", "birthPlace", "deathDate")),
        new HashSet<String>(Arrays.asList("givenName", "surname", "birthDate", "deathPlace")),
        new HashSet<String>(Arrays.asList("name", "description", "birthPlace", "deathPlace")),
        new HashSet<String>(Arrays.asList("name", "birthDate", "deathPlace")),
        new HashSet<String>(Arrays.asList("surname", "birthDate", "deathDate", "deathPlace")),
        new HashSet<String>(Arrays.asList("name", "birthDate", "deathDate")),
        new HashSet<String>(Arrays.asList("name", "description", "birthDate", "birthPlace")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "c")))));

    return new Object[]{input, expectedOutput};
  }     
}
