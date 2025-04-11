package tp2;

import java.util.ArrayList;

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
	
	public int getHeight() {
		return getHeightRec(root);
	}
	
	private int getHeightRec(TreeNode nodo) {
		if(nodo == null) return -1;
		
		int alturaLeft = getHeightRec(nodo.getLeft());
		int alturaRight = getHeightRec(nodo.getRight());
		
		return Math.max(alturaLeft, alturaRight) + 1;
	}
	
	public void imprimirPreOrder() {
		imprimirPreOrderRec(root);
		System.out.println();
	}
	
	private void imprimirPreOrderRec(TreeNode nodo) {
		if(nodo == null) return;
		System.out.print(nodo.getValue() +  " ");
		imprimirPreOrderRec(nodo.getLeft());
		imprimirPreOrderRec(nodo.getRight());
		//son iguales cambia el momento de impresion y cambia el lugar en el callstack
	}
	
	public ArrayList<Integer> getRamaLarga(){
		return getRamaLargaRec(root);
	}

	private ArrayList<Integer> getRamaLargaRec(TreeNode nodo) {
		if(nodo == null) return new ArrayList<Integer>();
		
		ArrayList<Integer> izq = getRamaLargaRec(nodo.getLeft());
		ArrayList<Integer> der = getRamaLargaRec(nodo.getRight());
		
		ArrayList<Integer> masLarga = (izq.size() > der.size()) ? izq : der;
		ArrayList<Integer> resultado = new ArrayList<>();
		resultado.add(nodo.getValue());
		resultado.addAll(masLarga);
		return resultado;
		
		
	}
	
	public ArrayList<Integer> getFrontera(){
		ArrayList<Integer> resultados = new ArrayList<>();
		if(root != null) {
			getFronteraRec(root, resultados);
		}
		return resultados;
	}

	private void getFronteraRec(TreeNode nodo, ArrayList<Integer> resultados) {
		if(nodo.getLeft() == null && nodo.getRight() == null) {
			resultados.add(nodo.getValue());
		}
		getFronteraRec(nodo.getLeft(), resultados);
		getFronteraRec(nodo.getRight(), resultados);
		
	}
	
	public Integer getMaxElem() {
		if(!this.isEmpty()) {
			return getMaxElemRec(root);
		}
		return null;
	}

	private Integer getMaxElemRec(TreeNode n) {
		if(n.getRight() == null) return n.getValue();
		
		return getMaxElemRec(n.getRight());
		
	}
	
	public ArrayList<Integer> getElemAtLevel(int i){
		ArrayList<Integer> resultados = new ArrayList<>();
		if(root != null) {
			getElemAtLevelRec(root, resultados, i, 0);
		}
		return resultados;
	}

	private void getElemAtLevelRec(TreeNode nodo, ArrayList<Integer> resultados, int i, int contador) {
		if(nodo == null) return;
		if(i == contador) {
			resultados.add(nodo.getValue());
			return;
		}
		
		getElemAtLevelRec(nodo.getLeft(), resultados, i, contador +1);
		getElemAtLevelRec(nodo.getRight(), resultados, i, contador +1);
		
	}
	
	
	
}
