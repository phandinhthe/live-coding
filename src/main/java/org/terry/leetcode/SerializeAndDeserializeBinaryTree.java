package org.terry.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
	public static void main(String[] args) {
		new SerializeAndDeserializeBinaryTree().test();
	}

	public void test() {
		TreeNode root = new TreeNode(1);
		TreeNode node = root;
		node.left = new TreeNode(2);
		node.right = new TreeNode(3);
		node = node.right;
		node.left = new TreeNode(4);
		node.right = new TreeNode(5);

		Codec codec = new Codec();
		String data = codec.serialize(root);
		assert "1 2 null null 3 4 null null 5 null null".equals(data);

		TreeNode deserialized = codec.deserialize(data);
		assert deserialized.equals(root);
		assert codec.deserialize(codec.serialize(null)) == null;
	}

	/*
		297. Serialize and Deserialize Binary Tree.
		To implement the serialize/deserialize functions, we should be aware of the order of every element serialized.
		After that, the order of serialized elements will affect the deserialization process also.
	 */
	public class Codec {
		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			if (root == null) return "null";
			return root.val +
					" " + serialize(root.left) +
					" " + serialize(root.right);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			Queue<String> queue = new LinkedList<>(List.of(data.split(" ")));
			return deserialize(queue);
		}

		private TreeNode deserialize(Queue<String> queue) {
			if (queue.isEmpty()) return null;

			String val = queue.poll();
			if ("null".equals(val)) return null;

			TreeNode root = new TreeNode(Integer.parseInt(val));
			root.left = deserialize(queue);
			root.right = deserialize(queue);
			return root;
		}
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		public boolean equals(TreeNode other) {
			if (other == null) return true;
			if (this.val != other.val) return false;
			return (this.left == other.left || this.left.equals(other.left)) &&
					(this.right == other.right || this.right.equals(other.right));
		}
	}


}
