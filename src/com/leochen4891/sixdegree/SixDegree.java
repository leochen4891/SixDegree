package com.leochen4891.sixdegree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SixDegree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SixDegree six = new SixDegree();
		Map<String, String[]> map = six.getInputMoviesAndActorsList();

		for (String movie : map.keySet()) {
			// System.out.println(movie);
			String[] actors = map.get(movie);
			for (String actor : actors) {
				// System.out.print(actor + ", ");
			}
			// System.out.println();
			// System.out.println();
		}

		Graph graph = new Graph();
		graph.initWithMoviesAndActorsList(map);

		printConnection(graph, "Morgan Freeman", "Arnold Schwarzenegger");
		printConnection(graph, "Morgan Freeman", "Al Pacino");
		printConnection(graph, "Leonardo DiCaprio", "Robert Redford");
	}

	public static void printConnection(Graph graph, String actor1, String actor2) {
		Node n1 = graph.getNode(actor1);
		Node n2 = graph.getNode(actor2);

		List<Edge> list = graph.BFS(n1.name, n2.name);
		if (null == list || list.size() <= 0) {
			System.out.println("No connection between " + n1.name + " and " + n2.name);
			return;
		}

		Collections.reverse(list);

		System.out.println("Connection Found between " + n1.name + " and " + n2.name);
		Node prev = n1;
		for (Edge e : list) {
			Node next = e.getTheOtherNode(prev);
			System.out.println(prev.name + "    <" + e.name + ">    " + next.name);
			prev = next;
		}
		System.out.println();
	}

	private Map<String, String[]> getInputMoviesAndActorsList() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		File folder = new File("movies/");
		File[] files = folder.listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				String filename = files[i].getAbsolutePath();
				// System.out.println("reading file " + filename);
				BufferedReader br = null;
				try {
					// br = new BufferedReader(new FileReader(filename)); // <---- cause encoding problem here
					br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					while (line != null) {
						sb.append(line);
						sb.append(System.lineSeparator());
						line = br.readLine();
					}
					String str = sb.toString();
					JSONParser parser = new JSONParser();
					JSONObject obj = (JSONObject) parser.parse(str);
					Object titleObj = obj.get("Title");
					Object actorsObj = obj.get("Actors");
					if (null == titleObj || null == actorsObj)
						continue;
					String title = titleObj.toString().trim();
					String[] actors = obj.get("Actors").toString().replaceAll(", ", ",").split(",");
					ret.put(title, actors);

				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (null != br) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return ret;
	}
}
