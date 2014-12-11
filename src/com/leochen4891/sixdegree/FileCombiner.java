package com.leochen4891.sixdegree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileCombiner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("movies.txt")));
			for (int i = 0; i < 250; i++) {
				String filename = "movies/movie_" + i + ".txt";
				System.out.println(filename);
				br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
				String line = br.readLine();
				while (null != line) {
					bw.write(line);
					bw.write(System.lineSeparator());
					line = br.readLine();
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
