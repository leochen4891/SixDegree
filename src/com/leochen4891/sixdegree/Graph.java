package com.leochen4891.sixdegree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	Set<Node> nodes;
	Set<Edge> edges;

	public Graph() {
		nodes = new HashSet<Node>();
		edges = new HashSet<Edge>();
	}

	// each line of input is expected to be
	// <movie name> <actor 1> <actor 2> ... <actor N>
	public void initWithMoviesAndActorsList(List<String[]> input) {

	}
}
