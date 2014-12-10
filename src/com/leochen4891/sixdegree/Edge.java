package com.leochen4891.sixdegree;

public class Edge {
	String name;
	Node n1;
	Node n2;

	int hash;

	public Edge(String name, Node n1, Node n2) {
		this.name = name;
		this.n1 = n1;
		this.n2 = n2;
		this.hash = (n1.name + n2.name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Edge))
			return false;

		Edge e = (Edge) obj;
		return n1.equals(e.n1) && n2.equals(e.n2);
	}

	@Override
	public int hashCode() {
		return hash;
	}
}
