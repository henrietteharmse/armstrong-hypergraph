package org.armstrong.hypergraph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHypergraphWedge {
  private static Logger logger = LoggerFactory.getLogger(TestHypergraphWedge.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");

  @ParameterizedTest
  @MethodSource("testDataSet1")
  public void testHypergraphWedge(Set<Set<String>> inputEdges1, Set<Set<String>> inputEdges2, Set<Set<String>> expectedOutput) {
    Set<Set<String>> actualOutput = null;
    try {
      HypergraphOperations<String> op = new HypergraphOperationsImpl<String>();      
      actualOutput = op.wedge(inputEdges1, inputEdges2);
      logger.trace("actualOutput = " + actualOutput);
    }  catch (Throwable t) {
      logger.error(WTF_MARKER, t.getMessage(), t);
    }
    assertEquals(expectedOutput, actualOutput);
  }  
  
  private static Stream<Arguments> testDataSet1() {
    Set<Set<String>> inputEdges1 = new HashSet<>();
    Set<Set<String>> inputEdges2 = new HashSet<>();
    Set<Set<String>> expectedOutput = new HashSet<>();
    
    inputEdges1.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("a")),
        new HashSet<>(Arrays.asList("a", "b")))));

    inputEdges2.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("1")),
        new HashSet<>(Arrays.asList("2", "3")))));

    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "1")),
        new HashSet<>(Arrays.asList("a", "2", "3")),
        new HashSet<>(Arrays.asList("a", "b", "1")),
        new HashSet<>(Arrays.asList("a", "b", "2", "3")))));

    return Stream.of(Arguments.of(inputEdges1, inputEdges2, expectedOutput));
  }
}
