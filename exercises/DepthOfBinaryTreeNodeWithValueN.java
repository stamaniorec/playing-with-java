
public class DepthOfBinaryTreeNodeWithValueN {

	private TreeNode root;
	
	public DepthOfBinaryTreeNodeWithValueN() {
		/*
		 * 			7
		 * 		12		3
		 * 		  9  		5
		 * 		17
		 */
		
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(7);
		TreeNode d = new TreeNode(12);
		TreeNode e = new TreeNode(9);
		TreeNode f = new TreeNode(17);
		c.setLeft(d);
		d.setRight(e);
		c.setRight(b);
		b.setRight(a);
		e.setLeft(f);
		
		root = c;
		
		depth(7);
		depth(12);
		depth(5);
		depth(9);
		depth(17);
		depth(144);
	}
	
	private void depth(int value) {
		if(root == null) {
			System.out.println("Sorry, but your tree is empty.");
		} else {
			int depth = depthUtil(root, value);
			if(depth == -1) {
				System.out.println("Sorry, there is no node with value " + value +
						" in the tree.");
			} else {
				System.out.println("Depth of " + value + " : " + depth);
			}
		}
	}
	
	private int depthUtil(TreeNode root, int value) {
		if(root == null) {
			return -1;
		} else {
			if(root.getData() == value) {
				return 0;
			} else {
				int ret = depthUtil(root.getLeft(), value);
				if(ret != -1) {
					return 1 + ret;
				}
				
				ret = depthUtil(root.getRight(), value);
				if(ret != -1) {
					return 1 + ret;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		new DepthOfBinaryTreeNodeWithValueN();
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

