
public class BST_LCA {

	private TreeNode root = null;
	
	public BST_LCA() {
		/*
		 * 		  12
		 * 		7    17
		 * 	  5   9    22
		 *  3
		 */
		
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(7);
		TreeNode d = new TreeNode(12);
		TreeNode e = new TreeNode(9);
		TreeNode f = new TreeNode(17);
		TreeNode g = new TreeNode(22);
		d.setLeft(c);
		c.setRight(e);
		c.setLeft(a);
		a.setLeft(b);
		d.setRight(f);
		f.setRight(g);
		
		root = d;
		
		LCA(3, 5);
		LCA(3, 7);
		LCA(3, 9);
		LCA(5, 9);
		LCA(9, 5);
		LCA(7, 9);
		LCA(12, 9);
		LCA(17, 22);
		LCA(22, 12);
		LCA(22, 3);
		LCA(22, 9);
		LCA(17, 5);
	}
	
	private void LCA(int a, int b) {
		if(root == null) {
			System.out.println("Empty tree.");
		} else {
			System.out.println("LCA of " + a + " and " + b + " is " + 
					LCAUtil(root, a, b));
		}
	}
	
	private int LCAUtil(TreeNode root, int a, int b) {
		if(root == null) {
			return 0;
		} else {
			if(root.getData() < a && root.getData() < b) {
				return LCAUtil(root.getRight(), a, b);
			}
			if(root.getData() > a && root.getData() > b) {
				return LCAUtil(root.getLeft(), a, b);
			}
			return root.getData();
		}
	}
	
	public static void main(String[] args) {
		new BST_LCA();
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
