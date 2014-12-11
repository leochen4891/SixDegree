package com.leochen4891.sixdegree;

import java.util.HashSet;
import java.util.Set;

public class Node {
	String name;
	Set<Edge> edges;

	Edge bfsPrev;

	int hash;

	public Node(String name) {
		this.name = name;
		this.hash = name.hashCode();
		this.edges = new HashSet<Edge>();
		this.bfsPrev = null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Node))
			return false;

		Node n = (Node) obj;
		return name.equals(n.name);
	}

	@Override
	public int hashCode() {
		return hash;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public Set<Edge> getAllEdges() {
		return edges;
	}

	public Edge getBFSPrev() {
		return bfsPrev;
	}

	public void setBFSPrev(Edge e) {
		this.bfsPrev = e;
	}

}
