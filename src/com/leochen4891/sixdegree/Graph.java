package com.leochen4891.sixdegree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
	Map<String, Node> nodes;
	Map<String, Edge> edges;

	public Graph() {
		nodes = new HashMap<String, Node>();
		edges = new HashMap<String, Edge>();
	}

	// each line of input is expected to be
	// <movie name> <actor 1> <actor 2> ... <actor N>
	public void initWithMoviesAndActorsList(Map<String, String[]> map) {
		for (String movie : map.keySet()) {
			String[] actors = map.get(movie);
			// add all the nodes first
			for (String actor : actors) {
				if (!nodes.containsKey(actor)) {
					Node node = new Node(actor);
					nodes.put(actor, node);
				}
			}

			// add the edges between each pair of nodes
			for (int i = 0; i < actors.length; i++) {
				for (int j = i + 1; j < actors.length; j++) {
					String actor1 = actors[i];
					String actor2 = actors[j];
					Node n1 = nodes.get(actor1);
					Node n2 = nodes.get(actor2);
					Edge e = new Edge(movie, n1, n2); // edge is undirected
					n1.addEdge(e);
					n2.addEdge(e);
				}
			}
		}
	}

	public Node getNode(String actor) {
		return nodes.get(actor);
	}

	public Set<Edge> getAllEdges(String actor) {
		Node node = nodes.get(actor);
		if (null != node) {
			return node.getAllEdges();
		}
		return null;
	}

	public List<Edge> BFS(String actor1, String actor2) {
		if (!nodes.containsKey(actor1) || !nodes.containsKey(actor2)) {
			return null;
		}

		List<Edge> ret = new LinkedList<Edge>();
		HashMap<String, Node> searched = new HashMap<String, Node>();
		Queue<Node> queue = new LinkedList<Node>();

		if (actor1.equals(actor2)) {
			return ret;
		}

		// clear the bfsPrev in the nodes
		for (Node n : nodes.values()) {
			n.setBFSPrev(null);
		}

		// start the BFS search
		queue.offer(nodes.get(actor1));
		searched.put(actor1, nodes.get(actor1));
		while (!queue.isEmpty() && !searched.containsKey(actor2)) {
			Node prev = queue.poll();
			for (Edge e : prev.getAllEdges()) {
				Node node = e.getTheOtherNode(prev);

				if (searched.containsKey(node.name))// already touched
					continue;

				node.setBFSPrev(e);
				searched.put(node.name, node);
				queue.offer(node);
			}
		}

		Node node = searched.get(actor2);

		if (null == node)
			return null;

		Edge prevEdge = node.getBFSPrev();
		while (prevEdge != null && null != node) {
			ret.add(prevEdge);
			node = prevEdge.getTheOtherNode(node);
			prevEdge = node.getBFSPrev();
		}

		return ret;
	}
}