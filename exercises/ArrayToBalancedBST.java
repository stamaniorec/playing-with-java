import java.util.LinkedList;
import java.util.Queue;


public class ArrayToBalancedBST {

	private int[] arr;
	
	public ArrayToBalancedBST() {
		/*
		 *       9
		 *     5   17
		 *    3 7 12 22 
		 */
		arr = new int[] { 3, 5, 7, 9, 12, 17, 22 };
		TreeNode root = toBalancedBST(arr, 0, arr.length-1);
		printBFS(root);
		printInorderTraversal(root);
	}
	
	private TreeNode toBalancedBST(int[] arr, int start, int end) {
		if(start > end) // or == 
			return null; // or new TreeNode(arr[start]);
		
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		root.setLeft(toBalancedBST(arr, start, mid-1));
		root.setRight(toBalancedBST(arr, mid+1, end));
		return root;
	}
	
	private void printBFS(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			TreeNode front = q.poll();
			System.out.print(front.getData() + " ");
			if(front.getLeft() != null) {
				q.offer(front.getLeft());
			}
			if(front.getRight() != null) {
				q.offer(front.getRight());
			}
		}
		System.out.println();
	}
	
	private void printInorderTraversal(TreeNode root) {
		if(root != null) {
			printInorderTraversal(root.getLeft());
			System.out.print(root.getData() + " ");
			printInorderTraversal(root.getRight());
		}
	}
	
	public static void main(String[] args) {
		new ArrayToBalancedBST();
	}
	
	private class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;
		
		public TreeNode(int data) { 
			this.data = data;
			left = right = null;
		}
		public void setLeft(TreeNode left) { this.left = left; }
		public void setRight(TreeNode right) { this.right = right; }
		public int getData() { return data; } 
		public TreeNode getLeft() { return left; }
		public TreeNode getRight() { return right; }
	}
}
