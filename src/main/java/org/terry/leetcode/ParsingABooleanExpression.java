package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/parsing-a-boolean-expression/description/">Leetcode 1106: Parsing a boolean expression</a>
 * Tuition:
 * - Prepare 2 stacks, expression stack and Operation stack
 * - Prepare method execute(subExpressions, operation) to execute the sub expressions by logical `operation`.
 * - Iterate all character in `expression`:
 * -- ignore `,`
 * -- push operations `!&|(` to operation stack
 * -- push sub expression `t or f` to expression stack
 * -- if char at (ith) == ')': -> excute till expresion stack.peek == `(` then pop `(` and put `result` to expression stack
 * - Final result:
 * -- if (operation is not empty): execute(expression stack, operation.pop)
 * -- else : return expression stack. pop
 */

public class ParsingABooleanExpression {
    public static void main(String[] args) {
        new ParsingABooleanExpression().run();
    }

    public void run() {
        String expression;
        boolean expected;

        expression = "&(|(f))";
        expected = false;
        Assertions.assertEquals(expected, parseBoolExpr(expression));

        expression = "|(f,f,f,t)";
        expected = true;
        Assertions.assertEquals(expected, parseBoolExpr(expression));

        expression = "!(&(f,t))";
        expected = true;
        Assertions.assertEquals(expected, parseBoolExpr(expression));
    }

    public boolean parseBoolExpr(String expression) {
        expression = expression.replace(",", "");
        char[] arr = expression.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        for (char c : arr) {
            if (c=='t' || c=='f' || c=='(') {
                stack.push(c);
            } else if (c==')') {
                Queue<Character> queue = new ArrayDeque<>();
                while (!stack.peek().equals('(')) {
                    char val = stack.pop();
                    queue.offer(val);
                }
                stack.pop();
                if (execute(queue, ops.pop())) {
                    stack.push('t');
                } else {
                    stack.push('f');
                }
            } else {
                ops.push(c);
            }
        }
        if (ops.isEmpty()) return stack.pop().equals('t');
        return execute(new ArrayDeque<>(stack), ops.pop());
    }

    public boolean execute(Queue<Character> queue, char op) {
        boolean rs = Objects.equals(queue.poll(), 't');
        if (op=='!') return !rs;

        while (!queue.isEmpty()) {
            if (op=='|') {
                rs |= (queue.poll()=='t');
            } else {
                rs &= (queue.poll()=='t');
            }
        }

        return rs;
    }
}

