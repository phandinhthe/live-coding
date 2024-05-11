package org.terry.datastructure.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Random;

public class Quicksort {

	public static final String INPUT_FILE = "Quicksort/Quicksort.input";
	public static final String OUTPUT_FILE = "Quicksort/Quicksort.output";

	public static void main(String[] args) {

		Quicksort quicksort = new Quicksort();
		Solution solution = quicksort.new Solution();

		// generate values
		int size = 1000;
		int batch = 1000;

		solution.generate(size, 0, size);

		try (BufferedWriter writer = quicksort.new FileWriter(OUTPUT_FILE).writer();
			 BufferedReader reader = quicksort.new FileReader(INPUT_FILE).reader()) {
			int[] numbers;
			numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseUnsignedInt)
					.toArray();
			numbers = solution.sort(numbers, size);

			writer.write(String.valueOf(numbers[0]));
			for (int i = 1; i < size; i++) {
				writer.write(" ".concat(String.valueOf(numbers[i])));
				if (i % batch == 0) {
					writer.flush();
				}
			}

			solution.test(numbers);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	class Solution {

		int[] sort(int[] numbers, int size) {
			return quicksort(numbers, 0, size - 1);
		}

		int[] quicksort(int[] numbers, int left, int right) {
			if (left >= right) return numbers;

			int iLeft = left, iRight = right;
			int mid = (left + right) / 2;
			int pivot = numbers[mid];

			while (iLeft <= iRight) {
				while (numbers[iLeft] < pivot) iLeft++;
				while (numbers[iRight] > pivot) iRight--;
				if (iLeft <= iRight) {
					int tmp = numbers[iLeft];
					numbers[iLeft] = numbers[iRight];
					numbers[iRight] = tmp;
					iLeft++;
					iRight--;
				}

			}
			quicksort(numbers, left, iLeft - 1);
			quicksort(numbers, iLeft, right);
			return numbers;
//      if (left >= right) {
//        return numbers;
//      }
//
//      int mid = partition(numbers, left, right);
//      quicksort(numbers, left, mid - 1);
//      quicksort(numbers, mid + 1, right);
//      return numbers;
		}

		int partition(int[] numbers, int left, int right) {
			int i = left, j = left;
			int pivot = numbers[right];

			while (i < right) {
				if (numbers[i] < pivot) {
					swap(numbers, i, j);
					j++;
				}

				i++;
			}
			swap(numbers, j, right);
			return j;
		}

		void swap(int[] numbers, int i, int j) {
			int tmp = numbers[i];

			numbers[i] = numbers[j];
			numbers[j] = tmp;
		}

		void test(int[] numbers) {
			for (int i = 0; i < numbers.length - 1; i++) {
				if (numbers[i] > numbers[i + 1]) {
					System.out.printf("Wrong at in index %d and %d \n", i, i + 1);
					System.out.printf("%d > %d", numbers[i], numbers[i + 1]);
					return;
				}
			}

			System.out.print("Passed\n");
		}

		void generate(int size, int smallest, int largest) {

			try (BufferedWriter writer = new FileWriter(INPUT_FILE).writer();) {
				Random random = new Random();
				int randNumber = random.nextInt(largest - smallest + 1);
				writer.write("".concat(String.valueOf(randNumber)));
				for (int i = 1; i < size; i++) {
					randNumber = random.nextInt(largest - smallest);
					writer.write((" ") + randNumber);
					if (i % 1_000 == 0) {
						writer.flush();
					}
				}
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}


	private class ConsoleReader {

		private BufferedReader reader() {
			return new BufferedReader(new InputStreamReader(System.in));
		}

	}

	private class ConsoleWriter {

		private BufferedWriter writer() {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			return writer;
		}
	}

	private class FileReader {

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

	private class FileWriter {

		String path;

		FileWriter(String path) {
			this.path = path;
		}

		private BufferedWriter writer() throws IOException {
			String path = this.getClass().getResource("/").getPath().concat(this.path);
			new File(path).createNewFile();
			File file = new File(path);
			BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file));
			return writer;
		}
	}
}
