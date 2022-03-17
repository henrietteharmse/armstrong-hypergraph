package org.armstrong.hypergraph.mhs;

import org.armstrong.hypergraph.Hypergraph;
import org.armstrong.hypergraph.HypergraphImpl;
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


public class TestBergeMHS {
  private static Logger logger = LoggerFactory.getLogger(TestBergeMHS.class);
  // Why This Failure marker
  private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");

  @ParameterizedTest
  @MethodSource
  public void testBergeMHS(Hypergraph<String> input, Set<Set<String>> expectedOutput) {
    Set<Set<String>> actualOutput = null;
    try {
      MHS<String> mhs = new BergeMHS<String>();
      actualOutput = mhs.calcMHS(input);
      logger.trace("actualOutput = " + actualOutput);
    }  catch (Throwable t) {
      logger.error(WTF_MARKER, t.getMessage(), t);
    }
    assertEquals(expectedOutput, actualOutput);
  }  

  private static Stream<Arguments> testBergeMHS() {
    return Stream.of(
            testDataSet1(),
            testDataSet2(),
            testDataSet3(),
            testDataSet4(),
            testDataSet5()
    );
  }

  private static Arguments testDataSet1() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Set<Set<String>> expectedOutput = new HashSet<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("a", "b", "c", "d")));
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("c", "d")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "c")),
        new HashSet<>(Arrays.asList("a", "d")),
        new HashSet<>(Arrays.asList("b", "c")),
        new HashSet<>(Arrays.asList("b", "d")))));

    return Arguments.of(input, expectedOutput);
  }
  
  private static Arguments testDataSet2() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Set<Set<String>> expectedOutput = new HashSet<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("a", "b")));
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a")),
        new HashSet<>(Arrays.asList("a", "b")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("a")))));

    return Arguments.of(input, expectedOutput);
  }
  
  private static Arguments testDataSet3() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Set<Set<String>> expectedOutput = new HashSet<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("a", "b", "c")));
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("a")),
        new HashSet<>(Arrays.asList("a", "b")),
        new HashSet<>(Arrays.asList("c")))));
    
    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("a", "c")))));

    return Arguments.of(input, expectedOutput);
  }
  
  private static Arguments testDataSet4() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Set<Set<String>> expectedOutput = new HashSet<>();
    
    input.addVertices(new HashSet<String>(Arrays.asList("name", "givenName", "surname", "gender", "description", 
        "birthDate", "birthPlace", "deathDate", "deathPlace")));     
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("deathPlace", "birthDate")),
        new HashSet<>(Arrays.asList("birthPlace", "deathPlace", "deathDate")),
        new HashSet<>(Arrays.asList("birthPlace", "birthDate")),
        new HashSet<>(Arrays.asList("name", "birthDate")),
        new HashSet<>(Arrays.asList("name", "givenName", "deathPlace")),
        new HashSet<>(Arrays.asList("name", "birthPlace", "surname")),
        new HashSet<>(Arrays.asList("name", "surname", "deathDate")),
        new HashSet<>(Arrays.asList("name", "givenName", "surname")),
        new HashSet<>(Arrays.asList("description", "deathPlace", "deathDate")),
        new HashSet<>(Arrays.asList("description", "birthDate")),
        new HashSet<>(Arrays.asList("name", "givenName", "deathDate")))));

    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("surname", "givenName", "deathDate", "birthDate")),
            new HashSet<>(Arrays.asList("birthPlace", "surname", "givenName", "description", "birthDate")),
            new HashSet<>(Arrays.asList("birthPlace", "givenName", "deathDate", "birthDate")),
            new HashSet<>(Arrays.asList("deathPlace", "surname", "givenName", "birthDate")),
            new HashSet<>(Arrays.asList("birthPlace", "deathPlace", "name", "description")),
            new HashSet<>(Arrays.asList("deathPlace", "name", "birthDate")),
            new HashSet<>(Arrays.asList("deathPlace", "surname", "deathDate", "birthDate")),
            new HashSet<>(Arrays.asList("name", "deathDate", "birthDate")),
            new HashSet<>(Arrays.asList("birthPlace", "name", "description", "birthDate"))
    )));

    return Arguments.of(input, expectedOutput);
  }
  
  private static Arguments testDataSet5() {
    Hypergraph<String> input = new HypergraphImpl<>();
    Set<Set<String>> expectedOutput = new HashSet<>();
    
    input.addVertices(new HashSet<>(Arrays.asList("name", "givenName", "surname", "gender", "description",
        "birthDate", "birthPlace", "deathDate", "deathPlace")));     
    input.addEdges(new HashSet<>(Arrays.asList(
        new HashSet<>(Arrays.asList("givenName", "surname", "birthDate", "deathDate")),
        new HashSet<>(Arrays.asList("givenName", "surname", "description", "birthDate", "birthPlace")),
        new HashSet<>(Arrays.asList("givenName", "birthDate", "birthPlace", "deathDate")),
        new HashSet<>(Arrays.asList("givenName", "surname", "birthDate", "deathPlace")),
        new HashSet<>(Arrays.asList("name", "description", "birthPlace", "deathPlace")),
        new HashSet<>(Arrays.asList("name", "birthDate", "deathPlace")),
        new HashSet<>(Arrays.asList("surname", "birthDate", "deathDate", "deathPlace")),
        new HashSet<>(Arrays.asList("name", "birthDate", "deathDate")),
        new HashSet<>(Arrays.asList("name", "description", "birthDate", "birthPlace")))));

    expectedOutput.addAll(new HashSet<Set<String>>(Arrays.asList(
        new HashSet<>(Arrays.asList("deathPlace", "deathDate", "description")),
            new HashSet<>(Arrays.asList("deathPlace", "birthDate")),
            new HashSet<>(Arrays.asList("name", "birthDate")),
            new HashSet<>(Arrays.asList("deathPlace", "givenName", "name")),
            new HashSet<>(Arrays.asList("description", "birthDate")),
            new HashSet<>(Arrays.asList("surname", "givenName", "name")),
            new HashSet<>(Arrays.asList("birthPlace", "deathPlace", "deathDate")),
            new HashSet<>(Arrays.asList("givenName", "name", "deathDate")),
            new HashSet<>(Arrays.asList("surname", "name", "deathDate")),
            new HashSet<>(Arrays.asList("birthPlace", "surname", "name")),
            new HashSet<>(Arrays.asList("birthPlace", "birthDate"))
    )));

    return Arguments.of(input, expectedOutput);
  }     
}
