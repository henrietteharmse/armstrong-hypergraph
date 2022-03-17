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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestHypergraphMinimization {
  private static Logger logger = LoggerFactory.getLogger(TestHypergraphMinimization.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  

  @ParameterizedTest
  @MethodSource
  public void testMinimization(Hypergraph<String> input, Hypergraph<String> expectedOutput) {
    Hypergraph<String> actualOutput = null;
    try {
      actualOutput = input.minimize();
      logger.trace("actualOutput = " + actualOutput);
    }  catch (Throwable t) {
      logger.error(WTF_MARKER, t.getMessage(), t);
    }
    assertEquals(expectedOutput, actualOutput);
  }
  
  private static Stream<Arguments> testMinimization() {
    return Stream.of(testDataSet1(), testDataSet2(), testDataSet3());
  }

  private static Arguments testDataSet1() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Hypergraph<String> expectedOutput = new HypergraphImpl<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("a", "b", "c", "d", "e")));
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a")),
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("a", "b", "c")),
        new HashSet<>(Arrays.asList("a", "d")),
        new HashSet<>(Arrays.asList("a", "d", "e")))));
    
    expectedOutput.addVertices(input.getVertices());
    expectedOutput.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a")))));

    return Arguments.of(input, expectedOutput);
  }
  
  private static Arguments testDataSet2() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Hypergraph<String> expectedOutput = new HypergraphImpl<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("a", "b", "c", "d")));
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("a", "c")),
        new HashSet<>(Arrays.asList("c", "d")))));
    
    expectedOutput.addVertices(input.getVertices());
    expectedOutput.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("a", "c")),
        new HashSet<>(Arrays.asList("c", "d")))));

    return Arguments.of(input, expectedOutput);
  }
  
  private static Arguments testDataSet3() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Hypergraph<String> expectedOutput = new HypergraphImpl<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("a", "b", "c", "d", "e")));
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("a", "b", "c")),
        new HashSet<>(Arrays.asList("a", "b", "d")),
        new HashSet<>(Arrays.asList("c", "d", "e")))));
    
    expectedOutput.addVertices(input.getVertices());
    expectedOutput.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("c", "d", "e")))));

    return Arguments.of(input, expectedOutput);
  }   
  
}
