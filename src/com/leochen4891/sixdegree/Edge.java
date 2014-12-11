package com.leochen4891.sixdegree;

public class Edge {
	String name;
	Node n1;
	Node n2;

	int hash;

	public Edge(String name, Node n1, Node n2) {
		this.name = name;
		boolean reverse = n1.name.compareTo(n2.name) > 0;
		this.n1 = reverse ? n2 : n1;
		this.n2 = reverse ? n1 : n2;
		this.hash = (n1.name + n2.name).hashCode();
	}

	public Node getTheOtherNode(Node node) {
		boolean b1 = n1.equals(node);
		boolean b2 = n2.equals(node);

		if ((b1 && b2) || (!b1 && !b2)) {
			return null; // error
		}

		return b1 ? n2 : n1;
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
