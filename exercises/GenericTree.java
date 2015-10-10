import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GenericTree {

	private	Node root;

	public GenericTree() {
		root = null;
	}
	
	public GenericTree(Node root) {
		this.root = root;
	}
	
	public void addRoot(int value) {
		if(root == null) {
			root = new Node(value);
		} else {
			System.out.println("Error. This tree already has a root!");
		}
	}
	
	public void addRoot(Node root) {
		if(this.root == null)
			this.root = root;
		else
			System.out.println("Error. This tree already has a root!");
	}
	
	public void addNode(Node newNode, int ancestorValue) {
		if(root == null)
			System.out.println("You can only add nodes if you have a root!");
		else {
			Node ancestor = findNodeDFSStack(root, ancestorValue);
			if(ancestor == null) {
				System.out.println("No ancestor node with value " + ancestorValue + 
						" was found in the tree.");
			} else {
				ancestor.addChild(newNode);
				// .addChild takes care of setParent too
			}
		}
	}
	
	public void setNodeValue(int curValue, int newValue) {
		Node node = findNodeDFSRecursion(root, curValue);
		if(node == null) {
			System.out.println("No node with value " + curValue + " was found in the tree.");
		} else {
			node.setData(newValue);
		}
	}
	
	public void deleteLeaf(int value) {
		Node node = findNodeDFSRecursion(root, value);
		if(node == null) {
			System.out.println("No such node.");
		} else if(node.getChildren().size() > 0) {
			System.out.println("You can only delete leafs!");
		} else {
			node.getParent().getChildren().remove(node);
		}
		// If storing the parent is not allowed
		// find the node that is the parent of the one we're looking for
	}
	
	public boolean contains(int key) {
		Node answer = findNodeDFSRecursion(root, key);
		return answer != null;
	}
	
	private	Node findNodeDFSStack(Node root, int key) {
		Stack<Node> s = new Stack<Node>();
		s.add(root);
		while(!s.isEmpty()) {
			Node cur = s.peek();
			if(cur.getData() == key)
				return cur;
			s.pop();
			for(int i = 0; i < cur.getChildren().size(); ++i)
			{
				s.push(cur.getChildren().get(i));
			}
		}
		return null;
	}

	private Node findNodeDFSRecursion(Node root, int key) {
		if(root.getData() == key)
			return root;
		else {
			for(int i = 0; i < root.getChildren().size(); ++i) {
				Node result = findNodeDFSRecursion(root.getChildren().get(i), key);
				if(result != null)
					return result;
			}
		}
		return null;
	}
	
	Node getRoot() { return root; }
		
	public void BFS_traversal()	{
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty()) {
			Node front = q.element();
			System.out.print(front.getData() + " ");
			for(int i = 0; i < front.getChildren().size(); ++i) {
				q.add(front.getChildren().get(i));
			}
			q.remove();
		}
	}

	public void DFS_traversal() {
		DFS_traversal_util(root);
	}
	
	public void DFS_traversal_util(Node root) {
		System.out.print(root.getData() + " ");
		for(int i = 0; i < root.getChildren().size(); ++i) {
			DFS_traversal_util(root.getChildren().get(i));
		}
	}
	
	public int getNodesCount() {
		return DFS_NodeCount(root);
	}
	
	private int DFS_NodeCount(Node root) {
		if(root == null) {
			return 0;
		} else {
			int nodesInChildrenSubtrees = 0;
			for(int i = 0; i < root.getChildren().size(); ++i) {
				nodesInChildrenSubtrees += DFS_NodeCount(root.getChildren().get(i));
			}
			return 1 + nodesInChildrenSubtrees; // 1 for the root
		}
	}
	
	public int getNumberOfDescendants(int nodeValue) {
		Node node = findNodeDFSStack(root, nodeValue);
		if(node != null) {
			return getNumberOfDescendantsUtil(node) - 1;
		}
		System.out.print("No such node exists. ");
		return -1;
	}
	
	private int getNumberOfDescendantsUtil(Node node) {
		int num = 0;
		for(int i = 0; i < node.getChildren().size(); ++i) {
			num += getNumberOfDescendantsUtil(node.getChildren().get(i));
		}
		return 1 + num;
	}
	
	public int getMaximumDepth() {
		return getMaximumDepthUtil(root);
	}
	
	private int getMaximumDepthUtil(Node root) {
		if(root == null) {
			return 0;
		} else {
			int maxDepth = 0;
			for(int i = 0; i < root.getChildren().size(); ++i) {
				int depthOfCurSubtree = getMaximumDepthUtil(root.getChildren().get(i));
				if(depthOfCurSubtree > maxDepth) {
					maxDepth = depthOfCurSubtree;
				}
			}
			return 1 + maxDepth;
		}
	}
	
	public void clear() {
		root = null;
	}
	
	public ArrayList<ArrayList<Node>> getPathsFromRootToLeaves() {
		ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();
		paths(root, new ArrayList<Node>(), paths);
		return paths;
	}
	
	private void paths(Node root, ArrayList<Node> path, ArrayList<ArrayList<Node>> paths) {
		if(root == null) {
			return;
		} else {
			path.add(root);
			if(root.getChildren().size() == 0) {
				paths.add(new ArrayList<Node>(path));
			} 
			
			for(int i = 0; i < root.getChildren().size(); ++i) {
				paths(root.getChildren().get(i), path, paths);
			}
			path.remove(root);
		}
	}
	
	public void levelOrderPrint() {
		LinkedList<Node> BFSIterator = new LinkedList<Node>();
		// basically doing BFS, but the queue does not have a getLast method
		BFSIterator.add(root);
		Node endOfLevel = root;
		while(!BFSIterator.isEmpty()) {
			Node front = BFSIterator.poll();
			System.out.print(front.getData() + " ");
			if(front == endOfLevel) {
				// Whenever we reach the end of the level
				System.out.println();
				if(endOfLevel.getChildren().size() > 0) {
					// If the node at the end of the level has children
					// mark its last child as the end of the next level
					endOfLevel = endOfLevel.getChildren().get(endOfLevel.getChildren().size()-1);
				} else {
					// Else 
					if(BFSIterator.size() > 0)
						endOfLevel = BFSIterator.getLast();
				}
			}
			for(Node n : front.getChildren()) {
				BFSIterator.add(n);
			}
		}
	}
	
	public boolean areSiblings(int a, int b) {
		Node A = findNodeDFSRecursion(root, a);
		Node B = findNodeDFSRecursion(root, b);
		if(A == null) {
			System.out.println("No node with value " + a + " exists in the tree.");
			return false;
		}
		if(B == null) {
			System.out.println("No node with value " + b + " exists in the tree.");
			return false;
		}
		return A.getParent() == B.getParent();
	}
	
	public boolean areCousins(int a, int b) {
		// Two nodes are cousins if they are on the same level
		// and they have different parents
		
		Node A = findNodeDFSRecursion(root, a);
		Node B = findNodeDFSRecursion(root, b);
		if(A == null) {
			System.out.println("No node with value " + a + " exists in the tree.");
			return false;
		}
		if(B == null) {
			System.out.println("No node with value " + b + " exists in the tree.");
			return false;
		}
		return areOnTheSameLevel(A, B) && A.getParent() != B.getParent();
	}
	
	public boolean areOnTheSameLevel(Node A, Node B) {
		LinkedList<Node> BFSIterator = new LinkedList<Node>();
		BFSIterator.add(root);
		Node endOfLevel = root;
		while(!BFSIterator.isEmpty()) {
			Node front = BFSIterator.poll();
			for(Node n : front.getChildren()) {
				BFSIterator.add(n);
			}
			if(front == endOfLevel) {
				if(endOfLevel.getChildren().size() > 0) {
					endOfLevel = endOfLevel.getChildren().get(endOfLevel.getChildren().size()-1);
				} else {
					if(BFSIterator.size() > 0)
						endOfLevel = BFSIterator.getLast();
				}
				if(BFSIterator.contains(A) && !BFSIterator.contains(B)) {
					return false;
				}
				if(BFSIterator.contains(B) && !BFSIterator.contains(A)) {
					return false;
				}
				if(BFSIterator.contains(A) && BFSIterator.contains(B))
					return true;
			}
		}
		return false;
	}
	
	/*
		5
	3	2	7
		12
	*/
	
	/*
	 	5
	 3		2     255
	 	  14       512
	 	  15
	*/
	
	public static void main(String[] args) {
		GenericTree tree = new GenericTree();
		tree.addRoot(new Node(5));

		tree.addNode(new Node(3), 5);
		tree.addNode(new Node(2), 5);
		tree.addNode(new Node(7), 5);
		tree.addNode(new Node(12), 2);
		
		System.out.println("--- DFS TRAVERSAL ---");
		tree.DFS_traversal();
		System.out.println();
		
		System.out.println("--- TESTING CONTAINS METHOD ---");
		System.out.println("contains 5? " + tree.contains(5));
		System.out.println("contains 14? " + tree.contains(14));
		tree.setNodeValue(12, 14);
		System.out.println("(changed the Node with value 12 to have a value of 14)");
		System.out.println("contains 14? " + tree.contains(14));
	
		System.out.println("--- BFS TRAVERSAL ---");
		tree.BFS_traversal();
		System.out.println();
		
		System.out.println("--- DELETE 12 ---");
		tree.deleteLeaf(12);
		System.out.println("--- DELETE 5 ---");
		tree.deleteLeaf(5);
		System.out.println("--- DELETE 7 ---");
		tree.deleteLeaf(7);
		
		System.out.println("--- BFS TRAVERSAL ---");
		tree.BFS_traversal();
		System.out.println();
		
		System.out.println("--- NUM OF NODES ---");
		System.out.println(tree.getNodesCount());
		System.out.println("(adding node 15 to 14)");
		tree.addNode(new Node(15), 14);
		System.out.println(tree.getNodesCount());
		
		System.out.println("--- BFS TRAVERSAL ---");
		tree.BFS_traversal();
		System.out.println();
		
		System.out.println("--- NUMBER OF DESCENDANTS ---");
		System.out.println("5 -> " + tree.getNumberOfDescendants(5));
		System.out.println("2 -> " + tree.getNumberOfDescendants(2));
		System.out.println("15 -> " + tree.getNumberOfDescendants(15));
		System.out.println("25 -> " + tree.getNumberOfDescendants(25));
		
		System.out.println("--- MAXIMUM DEPTH ---");
		System.out.println(tree.getMaximumDepth());
		
		/*
		System.out.println("--- CLEARING ---");
		tree.clear();
		System.out.println(tree.getMaximumDepth());
		tree.addRoot(3);
		System.out.println(tree.getMaximumDepth());
		tree.addNode(new Node(5), 3);
		System.out.println(tree.getMaximumDepth());
		tree.addNode(new Node(7), 3);
		System.out.println(tree.getMaximumDepth());
		tree.addNode(new Node(10), 7);
		System.out.println(tree.getMaximumDepth());
		*/
		
		System.out.println("--- ALL PATHS FROM ROOT TO LEAVES ---");
		tree.addNode(new Node(255), 5);
		tree.addNode(new Node(512), 255);
		ArrayList<ArrayList<Node>> paths = tree.getPathsFromRootToLeaves();
		for(ArrayList<Node> path : paths) {
			for(Node n : path) {
				System.out.print(n.getData() + " ");
			}
			System.out.println();
		}
		
		System.out.println("--- LEVEL ORDER PRINT ---");
		tree.levelOrderPrint();
		
		System.out.println("--- TESTING FOR SIBLINGS ---");
		System.out.println("Are 3 and 255 siblings? " + tree.areSiblings(3, 255));
		System.out.println("Are 3 and 14 siblings? " + tree.areSiblings(3, 14));
		System.out.println("Are 5 and 15 siblings? " + tree.areSiblings(5, 15));
		System.out.println("Are 15 and 16 siblings? " + tree.areSiblings(15, 16));
		System.out.println("Are 128 and 129 siblings? " + tree.areSiblings(128, 129));
	
		System.out.println("--- TESTING FOR COUSINS ---");
		System.out.println("Are 3 and 255 cousins? " + tree.areCousins(3, 255));
		System.out.println("Are 3 and 14 cousins? " + tree.areCousins(3, 14));
		System.out.println("Are 5 and 15 cousins? " + tree.areCousins(5, 15));
		System.out.println("Are 15 and 16 cousins? " + tree.areCousins(15, 16));
		System.out.println("Are 128 and 129 cousins? " + tree.areCousins(128, 129));
		System.out.println("Are 14 and 512 cousins? " + tree.areCousins(14, 512));
		System.out.println("Are 2 and 3 cousins? " + tree.areCousins(2, 3));

	}
	
}

class Node {
	
	private int data;
	private ArrayList<Node> children;
	private Node parent;
	
	public Node() {
		children = new ArrayList<Node>();
	}
	
	public Node(int data) {
		this();
		this.data = data;
	}
	
	public int getData() { return data; }
	public void setData(int data) { this.data = data; }
	public ArrayList<Node> getChildren() { return children; }
	public void addChild(Node child) {
		children.add(child);
		child.setParent(this);
	}
	public void setParent(Node parent)	{
		this.parent = parent;
	}
	public Node getParent() { return parent; }
}