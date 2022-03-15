package org.armstrong.hypergraph;

import org.junit.jupiter.params.ParameterizedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestHypergraphWedge {
  private static Logger logger = LoggerFactory.getLogger(TestHypergraphWedge.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  
  private Set<Set<String>> inputEdges1;
  private Set<Set<String>> inputEdges2;
  private Set<Set<String>> expectedOutput;
  
//  @Parameters
//  public static Collection<Object[]> data() {
//      return Arrays.asList(new Object[][] {
//          testDataSet1(),
//         });
//  }

  @ParameterizedTest
  public void testHypergraphWedge(Set<Set<String>> inputEdges1, Set<Set<String>> inputEdges2, Set<Set<String>> expectedOutput) {
    Set<Set<String>> actualOutput = null;
    try {
      HypergraphOperations<String> op = new HypergraphOperationsImpl<String>();      
      actualOutput = op.wedge(inputEdges1, inputEdges2);
      logger.trace("actualOutput = " + actualOutput);
    }  catch (Throwable t) {
      logger.error(WTF_MARKER, t.getMessage(), t);
    }
//    Assert.assertEquals(expectedOutput, actualOutput);
  }  
  
  private static Object[] testDataSet1() {
    Set<Set<String>> inputEdges1 = new HashSet<Set<String>>();
    Set<Set<String>> inputEdges2 = new HashSet<Set<String>>();
    Set<Set<String>> expectedOutput = new HashSet<Set<String>>();
    
    inputEdges1.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a")),
        new HashSet<String>(Arrays.asList("a", "b")))));

    inputEdges2.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("1")),
        new HashSet<String>(Arrays.asList("2", "3")))));

    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<String>(Arrays.asList("a", "1")),
        new HashSet<String>(Arrays.asList("a", "2", "3")),
        new HashSet<String>(Arrays.asList("a", "b", "1")),
        new HashSet<String>(Arrays.asList("a", "b", "2", "3")))));

    return new Object[]{inputEdges1, inputEdges2, expectedOutput};
  }  
  
  
}
