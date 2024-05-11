package org.terry.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Common {
	public class Solution {


	}

	private static class ConsoleReader {

		private BufferedReader reader() {
			return new BufferedReader(new InputStreamReader(System.in));
		}

	}

	private static class ConsoleWriter {

		private BufferedWriter writer() {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			return writer;
		}
	}

	private static class FileReader {

		private String path;

		private FileReader(String path) {
			this.path = path;
		}

		private BufferedReader reader() throws FileNotFoundException {
			File file = new File(this.getClass().getResource("/".concat(path)).getFile());
			BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
			return reader;
		}
	}
}
