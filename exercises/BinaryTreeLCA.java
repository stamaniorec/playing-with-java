

public class BinaryTreeLCA {

	private TreeNode root = null;
	
	public BinaryTreeLCA() {
		/*
		 * 			7
		 * 		12		3
		 *  22     9  		5
		 * 		17
		 */
		
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(7);
		TreeNode d = new TreeNode(12);
		TreeNode e = new TreeNode(9);
		TreeNode f = new TreeNode(17);
		TreeNode g = new TreeNode(22);
		c.setLeft(d);
		d.setRight(e);
		c.setRight(b);
		b.setRight(a);
		e.setLeft(f);
		d.setLeft(g);
		
		root = c;
		
		LCA(17, 9);
		LCA(12, 17);
		LCA(17, 22);
		LCA(3, 5);
		LCA(5, 17);
		LCA(5, 12);
	}
	
	private void LCA(int a, int b) {
		if(root == null) {
			System.out.println("Empty tree.");
		} else {
			System.out.println("LCA of " + a + " and " + b + 
					" is " + LCAUtil(root, a, b));
		}
	}
	
	/*
	We traverse from the bottom, and once we reach a node which 
	matches one of the two nodes, we pass it up to its parent. 
	The parent would then test its left and right subtree 
	if each contain one of the two nodes. 
	If yes, then the parent must be the LCA and we pass its parent up to the root. 
	If not, we pass the lower node which contains either one of the two nodes 
	(if the left or right subtree contains either a or b), 
	or NULL (if both the left and right subtree does not contain either p or q) up.
	*/
	
	private int LCAUtil(TreeNode root, int a, int b) {
		if(root == null) {
			return 0;
		}
		
		if(root.getData() == a || root.getData() == b) {
			return root.getData();
		}
		
		int inLeftSubtree = LCAUtil(root.getLeft(), a, b);
		int inRightSubtree = LCAUtil(root.getRight(), a, b);
		
		if(inLeftSubtree != 0 && inRightSubtree != 0) {
			return root.getData();
		}
		
		return inLeftSubtree != 0 ? inLeftSubtree : inRightSubtree;
	}
	
	public static void main(String[] args) {
		new BinaryTreeLCA();
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
