package org.terry.datastructure.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://codeforces.com/problemset/problem/1385/A
 */
public class ThreePairwiseMaximums {

  public static void main(String[] args) {

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(System.out))) {
      int t = Integer.parseInt(reader.readLine());
      while (t >= 1) {
        String[] numbers = reader.readLine().split(" ");
        solution(
            Integer.parseInt(numbers[0]),
            Integer.parseInt(numbers[1]),
            Integer.parseInt(numbers[2]),
            writer
        );
        writer.flush();
        t--;
      }
    } catch (Exception exception) {
      System.err.println(exception);
    }

  }

  public static void solution(int x, int y, int z, BufferedWriter writer) throws IOException {
    Deque<Integer> deque = new LinkedList<>();
    add(deque, x);
    add(deque, y);
    add(deque, z);

    Result rs = solve(deque);
    if (rs.hasRes) {
      writer.write("YES\n");
      writer.write("" + rs.a);
      writer.write(" " + rs.b);
      writer.write(" " + rs.c);
      writer.write("\n");
      return;
    }

    writer.write("NO");
    writer.write("\n");
  }

  public static Result solve(Deque<Integer> deque) throws IOException {
    int x = deque.pollLast();
    int y = deque.pollLast();
    int z = deque.pollLast();

    int total = x + y + z;
    int a, b, c;

    if (x == y) {
      y = z;
    }

    Result rs = Result.EMPTY;
    if (x * 2 + y != total) {
      return rs;
    }
    a = x;
    b = y;
    c = y;

    rs = new Result(true, a, b, c);
    return rs;
  }

  private static void add(Deque<Integer> priorityQueue, int number) {
    if (priorityQueue.isEmpty()) {
      priorityQueue.add(number);
      return;
    }

    if (number > priorityQueue.getLast()) {
      priorityQueue.addLast(number);
      return;
    }

    if (number < priorityQueue.getFirst()) {
      priorityQueue.addFirst(number);
      return;
    }

    int tmp = priorityQueue.removeFirst();
    priorityQueue.addFirst(number);
    priorityQueue.addFirst(tmp);
  }

  private static class Result {

    boolean hasRes;
    int a, b, c;

    private static final Result EMPTY = new Result(false, 0, 0, 0);

    private Result(boolean hasRes, int a, int b, int c) {
      this.hasRes = hasRes;
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }
}
