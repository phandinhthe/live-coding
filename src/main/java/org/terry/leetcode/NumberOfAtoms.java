package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 726. Number of Atoms.
 * <a href="https://leetcode.com/problems/number-of-atoms">...</a>.
 * <p>
 * Tuition:
 * - Use a Stack of Map (String, Integer), with Map is to record all atoms and their counts in brackets.
 * - Iterate every char c in 's',
 * - - c = '(' - open, add a new map to stack, assign 'current' to the top of stack.
 * - - c = ')' - close,  merge the current map to prevous map in stack, assign 'current' to the previous/top of stack now
 * - Sort elements in the only Stack's Map.
 * <p>
 * Complexity   :  O(nlogn)
 * Space        :  O(n)
 */
public class NumberOfAtoms {
    public static void main(String[] args) {
        new NumberOfAtoms().test();
    }
    public void test() {
        String formula;
        String output;

        formula = "H20";
        output = "H20";
        Assertions.assertEquals(output, countOfAtoms(formula));

        formula = "Mg(OH)2";
        output = "H2MgO2";
        Assertions.assertEquals(output, countOfAtoms(formula));

        formula = "K4(ON(SO3)2)2";
        output = "K4N2O14S4";
        Assertions.assertEquals(output, countOfAtoms(formula));

        formula = "H11He49NO35B7N46Li20";
        output = "B7H11He49Li20N47O35";
        Assertions.assertEquals(output, countOfAtoms(formula));

        formula = "((N7Li31C7B10Be37B23H2H11Li40Be15)26(OBLi48B46N4)25(O48C22He)2N10O34N15B33Li39H34H26B15B23C31(C36N38O33Li38H15H46He21Be38B50)8)3";
        output = "B7512Be4968C1635H2658He510Li10167N1833O1257";
        Assertions.assertEquals(output, countOfAtoms(formula));

    }

    public String countOfAtoms(String formula) {

        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> cur = new HashMap<>();
        stack.push(cur);
        char[] arr = formula.toCharArray();
        int i = 0;
        while (i < formula.length()) {
            if (arr[i] == '(') {
                cur = new HashMap<>();
                stack.push(cur);
                i++;
            } else if (arr[i] == ')') {
                i++;
                StringBuilder num = new StringBuilder();
                while (i < formula.length() && Character.isDigit(arr[i])) {
                    num.append(arr[i++]);
                }
                if (num.isEmpty()) num = new StringBuilder("1");
                cur = stack.pop();
                Map<String, Integer> prev = stack.peek();
                cur = merge(prev, cur, Integer.parseInt(num.toString()));
            } else {
                StringBuilder atom = new StringBuilder(String.valueOf(arr[i++]));
                while (i < formula.length() && Character.isLowerCase(arr[i])) {
                    atom.append(arr[i++]);
                }
                StringBuilder num = new StringBuilder();
                while (i < formula.length() && Character.isDigit(arr[i])) {
                    num.append(arr[i++]);
                }
                if (num.isEmpty()) num = new StringBuilder("1");
                Integer numInt = Integer.parseInt(num.toString());
                cur.compute(atom.toString(), (k, v) -> v == null ? numInt: v + numInt);
            }
        }
        Map<String, Integer> map = stack.pop();
        StringBuilder result = new StringBuilder();
        map.keySet().stream().sorted(String::compareTo).forEach(key -> {
            result.append(key);
            if (map.get(key) > 1) result.append(map.get(key));
        });
        return result.toString();
    }

    Map<String, Integer> merge(Map<String, Integer> map1, Map<String, Integer> map2, int num) {
        for (String key : map2.keySet()) {
            if (map1.containsKey(key)) {
                map1.compute(key, (k, v) -> v = v + (map2.get(key) * num));
            } else map1.put(key, map2.get(key) * num);
        }

        return map1;
    }

}
