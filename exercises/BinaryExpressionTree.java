import java.util.Stack;

public class BinaryExpressionTree {
	
	ExpressionNode root = null;
	
	public BinaryExpressionTree(String postfix) {
		Stack<ExpressionNode> s = new Stack<ExpressionNode>();
		
		int curNum = 0;
		int tens = 1;
		
		for(int i = 0; i < postfix.length(); ++i) {
			if(postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9') {
				curNum *= tens;
				curNum += (postfix.charAt(i) - '0');
				tens *= 10;
				if(i < postfix.length() - 1) {
					if(postfix.charAt(i+1) < '0' || postfix.charAt(i+1) > '9') {
						s.add(new ExpressionNode(NodeTypes.DATA_NODE, curNum));
						curNum = 0;
						tens = 1;
					} 
				} else {
					s.add(new ExpressionNode(NodeTypes.DATA_NODE, curNum));
				}
			} else if(postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || 
					postfix.charAt(i) == '*' || postfix.charAt(i) == '/') {
				ExpressionNode a = s.pop();
				ExpressionNode b = s.pop();
				s.add(new ExpressionNode(NodeTypes.OPERATOR_NODE, postfix.charAt(i), b, a));
			}
		}
		
		root = s.pop();
	}
	
	public void inorderTraversal() {
		inorderPrivate(root);
	}
	
	private void inorderPrivate(ExpressionNode root) {
		if(root != null) {
			if(root.getLeft() != null && root.getRight() != null)
				System.out.print("(");
			inorderPrivate(root.getLeft());
			if(root.getType() == NodeTypes.DATA_NODE)
				System.out.print(root.getData());
			else if(root.getType() == NodeTypes.OPERATOR_NODE)
				System.out.print((char)root.getData());
			inorderPrivate(root.getRight());
			if(root.getLeft() != null && root.getRight() != null)
				System.out.print(")");
		}
	}
	
	public void preorderTraversal() {
		preorderPrivate(root);
	}
	
	private void preorderPrivate(ExpressionNode root) {
		if(root != null) {				
			if(root.getType() == NodeTypes.DATA_NODE)
				System.out.print(root.getData());
			else if(root.getType() == NodeTypes.OPERATOR_NODE)
				System.out.print((char)root.getData());	
			System.out.print(" ");	
				
			preorderPrivate(root.getLeft());
			preorderPrivate(root.getRight());				
		}
	}
	
	public static void main(String[] args) {
		BinaryExpressionTree tree = new BinaryExpressionTree("5 3 + 12 * 3 /");
		tree.inorderTraversal();
		System.out.println();
		tree.preorderTraversal();
		System.out.println();
	}
	
}

enum NodeTypes {
	DATA_NODE, 
	OPERATOR_NODE;
	
	NodeTypes() { }
}

class ExpressionNode {
	private NodeTypes type;
	// OOP flew out of the window with the above line 
	// but dealing with all the various types was very cumbersome 
	private int data;
	private ExpressionNode left;
	private ExpressionNode right;
	
	public ExpressionNode(NodeTypes type, int data) {
		if(type == NodeTypes.DATA_NODE) {
			this.type = type;
			this.data = data;
			left = right = null;
		}
	}
	
	public ExpressionNode(NodeTypes type, char operator, 
			ExpressionNode left, ExpressionNode right) {
		if(type == NodeTypes.OPERATOR_NODE) {
			this.type = type;
			this.left = left;
			this.right = right;
			data = (int) operator;
		}
	}
	
	public NodeTypes getType() { return type; }
	public int getData() { return data; }
	public ExpressionNode getLeft() { return left; }
	public ExpressionNode getRight() { return right; }
	
}