package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/walking-robot-simulation">Leetcode 874: Walking robot simulation</a>
 * <p>
 * tuition:
 * - directions: [ [0,1], [1,0], [0, -1], [-1, 0]];
 * - Set of obstacles: [..];
 * - Iterate over the command (d = 0 : NORTH)
 * - - if command is LEFT: d = (d + 3) % 4.
 * - - else RIGHT: d = (d + 1) % 4.
 * - - x += directions[d], y += directions[d];
 * - - record Max of (x*x + y*y)
 */

public class WalkingRobotSimulation {
    public static void main(String[] args) {
        new WalkingRobotSimulation().run();
    }

    void run() {
        int[] commands;
        int[][] obstacles;

        commands = new int[]{4, -1, 3};
        obstacles = new int[][]{};
        assertEquals(25, robotSim(commands, obstacles));

        commands = new int[]{4, -1, 4, -2, 4};
        obstacles = new int[][]{{2, 4}};
        assertEquals(65, robotSim(commands, obstacles));

        commands = new int[]{6, -1, -1, 6};
        obstacles = new int[][]{};
        assertEquals(36, robotSim(commands, obstacles));
    }

    private void assertEquals(int expected, int actual) {
        Assertions.assertEquals(expected, actual);
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Set<List<Integer>> set = HashSet.newHashSet(obstacles.length);
        Arrays.stream(obstacles).forEach(o -> set.add(List.of(o[0], o[1])));
        int curX = 0;
        int curY = 0;
        int d = 0;
        int res = 0;
        for (int command : commands) {
            if (command==-1) d = (d + 1) % 4;
            else if (command==-2) d = (d + 3) % 4;
            for (int step = 0; step < command; step++) {
                int nextX = curX + directions[d][0];
                int nextY = curY + directions[d][1];
                if (set.contains(List.of(nextX, nextY))) continue;
                curX = nextX;
                curY = nextY;
            }
            res = Math.max(res, curX * curX + curY * curY);
        }
        return res;
    }

}
