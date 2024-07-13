package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;
import java.util.*;

/*
Leetcode 2751. Robot collisions.
https://leetcode.com/problems/robot-collisions/description/

Tuition:
- Sort by the position.
- Use a queue to check every robot from the sorted list.
- if the robots positions are overlapped with each other, check:
- - the higher-health robot will be reduced 1 in health.
- - the robots have the same health will be removed.
- Sort the results again by its original index.

Complexity: O(nlogn)
space: O(n)
 */
public class RobotCollisions {
    public static void main(String[] args) {
        new RobotCollisions().test();
    }

    public void test() {
        int[] positions;
        int[] healths;
        String directions;
        List<Integer> output;

        positions = new int[]{5, 4, 3, 2, 1};
        healths = new int[]{2, 17, 9, 15, 10};
        directions = "RRRRR";
        output = List.of(2, 17, 9, 15, 10);
        Assertions.assertEquals(output, survivedRobotsHealths(positions, healths, directions));

        positions = new int[]{3, 5, 2, 6};
        healths = new int[]{10, 10, 15, 12};
        directions = "RLRL";
        output = List.of(14);
        Assertions.assertEquals(output, survivedRobotsHealths(positions, healths, directions));

        positions = new int[]{1, 2, 5, 6};
        healths = new int[]{10, 10, 11, 11};
        directions = "RLRL";
        output = Collections.emptyList();
        Assertions.assertEquals(output, survivedRobotsHealths(positions, healths, directions));

        positions = new int[]{1, 40};
        healths = new int[]{10, 11};
        directions = "RL";
        output = List.of(10);
        Assertions.assertEquals(output, survivedRobotsHealths(positions, healths, directions));

        positions = new int[]{3, 47};
        healths = new int[]{46, 26};
        directions = "LR";
        output = List.of(46, 26);
        Assertions.assertEquals(output, survivedRobotsHealths(positions, healths, directions));

    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Robot> robots = new ArrayList<>();
        for (int i = 1; i <= positions.length; i++) {
            int idx = i - 1;
            robots.add(new Robot(i, positions[idx], healths[idx], directions.charAt(idx)));
        }
        robots.sort(Comparator.comparingInt(Robot::position));
        Deque<Robot> queue = new LinkedList<>();
        for (Robot robot : robots) {
            if (queue.isEmpty()) {
                queue.addLast(robot);
                continue;
            }

            while (!queue.isEmpty()) {
                Robot cur = queue.peekLast();
                if (Robot.isNotCollided(cur, robot)) {
                    queue.offer(robot);
                    break;
                }

                if (cur.health() == robot.health()) {
                    queue.pollLast();
                    break;
                }

                if (cur.health() > robot.health()) {
                    cur.health(cur.health() - 1);
                    break;
                } else if (cur.health() < robot.health()) {
                    robot.health(robot.health() - 1);
                    queue.pollLast();
                    if (queue.isEmpty() && robot.health() > 0) {
                        queue.offerLast(robot);
                        break;
                    }
                }
            }
        }
        return queue.stream().sorted(Comparator.comparingInt(Robot::originalIndex)).map(Robot::health).toList();
    }

    static class Robot {
        private final int originalIndex;
        private final int position;
        private final int direction;
        private int health;

        public Robot(int originalIndex, int position, int health, int direction) {
            this.originalIndex = originalIndex;
            this.position = position;
            this.health = health;
            this.direction = direction;
        }

        public static boolean isNotCollided(Robot robot1, Robot robot2) {
            return (robot1.direction() == robot2.direction()) || (robot1.direction() == 'L' && robot2.direction() == 'R');
        }


        public int originalIndex() {
            return this.originalIndex;
        }

        public int position() {
            return this.position;
        }

        public int health() {
            return this.health;
        }

        public int direction() {
            return this.direction;
        }

        public void health(int health) {
            this.health = health;
        }
    }


}
