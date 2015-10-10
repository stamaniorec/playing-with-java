
public class IsBST {
	
	/*
	 * Expected output: false, true, true, true
	 */
	
	public IsBST() {
		/*		3
		 *    /   \
		 *   2     5 
		 *  / \    
		 * 1   4
		 * 
		 */
		
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		a.setLeft(b);
		b.setLeft(c);
		b.setRight(d);
		a.setRight(e);
		
		System.out.println(isBST(a));
		
		/*		3
		 *    /   \
		 *   2     5 
		 *  /     /
		 * 1     4
		 * 
		 */
		
		b.setRight(null);
		e.setLeft(d);
		
		System.out.println(isBST(a));
		
		/*
		 *    10
		 *   /  \
		 *  5    15
		 *      /
		 *     13
		 */
		
		TreeNode q = new TreeNode(10);
		TreeNode w = new TreeNode(5);
		TreeNode t = new TreeNode(15);
		TreeNode r = new TreeNode(13);
		
		q.setLeft(w);
		q.setRight(t);
		e.setLeft(r);
		
		System.out.println(isBST(q));
		
		/*
		 *      4
		 *     / \
		 *    2   5
		 *   / \   
		 *  1   3  
		 */
		
		TreeNode j = new TreeNode(4);
		TreeNode k = new TreeNode(2);
		TreeNode l = new TreeNode(1);
		TreeNode m = new TreeNode(3);
		TreeNode n = new TreeNode(5);
		
		j.setLeft(k);
		k.setLeft(l);
		k.setRight(m);
		j.setRight(n);
		
		System.out.println(isBST(j));
	}
	
	private boolean isBST(TreeNode root) {
		if(root == null) {
			System.out.println("Empty tree.");
			return false;
		} else {
			return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
	}
	
	private boolean isBSTHelper(TreeNode root, int lower, int upper) {
		if(root == null)
			return true;
		else 
			return (root.getData() < upper && root.getData() > lower) && 
				isBSTHelper(root.getLeft(), lower, root.getData()) && 
				isBSTHelper(root.getRight(), root.getData(), upper);
	}
	
	public static void main(String[] args) {
		new IsBST();
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
