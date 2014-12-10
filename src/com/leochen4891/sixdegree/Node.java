package com.leochen4891.sixdegree;

public class Node {
	String name;
	int hash;

	public Node(String name) {
		this.name = name;
		this.hash = name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Edge))
			return false;

		Node n = (Node) obj;
		return name.equals(n.name);
	}

	@Override
	public int hashCode() {
		return hash;
	}

}
