package tp2;

public class Tree {

	private TreeNode root;
	
	public Tree() {
		this.root = null;
	}
	
	public void add(Integer value) {
		if (this.root == null)
			this.root = new TreeNode(value);
		else
			this.add(this.root,value);
	}
	
	private void add(TreeNode actual, Integer value) {
		if (actual.getValue() > value) {
			if (actual.getLeft() == null) { 
				TreeNode temp = new TreeNode(value);
				actual.setLeft(temp);
			} else {
				add(actual.getLeft(),value);
			}
		} else if (actual.getValue() < value) {
			if (actual.getRight() == null) { 
				TreeNode temp = new TreeNode(value);
				actual.setRight(temp);
			} else {
				add(actual.getRight(),value);
			}
		}
	}
	
	public int getRoot() {
		return this.root.getValue();
	}
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public boolean tieneValor(int val) {
		return tieneValor(this.root, val);
	}
	
	public boolean tieneValor(TreeNode n, int val) {
		if(n == null) {
			return false;
		}
		if(n.getValue() == val) {
			return true;
		} else if(val < n.getValue()) {
			return tieneValor(n.getLeft(), val);
		} else {
			return tieneValor(n.getRight(), val);
		}
	}
	
	private int size(TreeNode node) {
		if(node == null) {
			return 0;
		}
		return 1 + size(node.getLeft()) + size(node.getRight());
	}
	
	private TreeNode getMinNode(TreeNode start) {
		if(start.getLeft() == null) {
			return start;
		}
		return getMinNode(start.getLeft());
	}
	
	private TreeNode deleteRec(TreeNode actual, int val) {
		if(actual == null) return null;
		if(val < actual.getValue()) {
			actual.setLeft(deleteRec(actual.getLeft(), val));
		} else if(val > actual.getValue()) {
			actual.setRight(deleteRec(actual.getRight(), val));
		} else {
			//CASO 1
			if(actual.getLeft() == null && actual.getRight() == null) {
				return null;
			}
			
			//Caso 2
			if(actual.getLeft() == null) return actual.getRight();
			if(actual.getRight() == null) return actual.getLeft();
			
			//CASO 3
			
			TreeNode sucesor = getMinNode(actual.getRight());
			actual.setValue(sucesor.getValue());
			actual.setRight(deleteRec(actual.getRight(), actual.getValue()));
		}
		return actual;
	}
	
	public boolean delete(Integer val) {
		if(val == null) return false;
		int initialSize = size(root);
		root = deleteRec(root, val);
		return initialSize != size(root);
		
	}
	
	
	
}
