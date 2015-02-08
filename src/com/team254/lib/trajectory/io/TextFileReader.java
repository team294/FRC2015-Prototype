package com.team254.lib.trajectory.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Read a text file into a string.
 *
 * @author Jared341
 */

public class TextFileReader {

	private BufferedReader reader_ = null;
	//	private List<String> records = new ArrayList<String>();

	/**
	 * Open and read a file, and return the lines in the file as a list
	 * of Strings.
	 * (Demonstrates Java FileReader, BufferedReader, and Java5.)
	 */

	public TextFileReader(String filename)
	{
		try
		{
			reader_ = new BufferedReader(new FileReader(filename));
			//			String line;
			//			while ((line = reader_.readLine()) != null)
			//			{
			//				records.add(line);
			//			}
			//			reader_ = new BufferedReader(new InputStreamReader(System.in));
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			System.err.println("Could not find specified file!");
			e.printStackTrace();
			return;
		}
	}

	public String readLine() {
		String line = null;
		try {
			line = reader_.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not read line!");
		}
		return line;
	}

	public String readWholeFile() {
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = readLine()) != null) {
			buffer.append(line);
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
