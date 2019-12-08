import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private static class Node<E>{
		
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Data cannot be null.");
				
			}else {
				
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}
		
		public Node<E> rotateRight(){
			if (this.left == null) {
				return this;
				
			}else {
				Node<E> update = this.left;
				this.left = this.left.right;
				update.right = this;
					return update;
			}
		}
		
		public Node<E> rotateLeft(){
			if(this.right == null) {
				return this;
				
			}else {
				
				Node<E> newUpdate = this.right;
				this.right = this.right.left;
				newUpdate.left = this;
				return newUpdate;
			}
		}
		
		public String toString() {
			return "(key=" + data.toString() + ", priority=" + String.valueOf(priority) + ")";
		}
	}
		
	private Random priorityGenerator;
	private Node<E> root;
	
	public Treap() {
		priorityGenerator = new Random();
			this.root = null;
	}
	public Treap(long seed) {
		
		priorityGenerator = new Random(seed);
			this.root = null;
	}
	
	public boolean add(E key) {
		int priority = priorityGenerator.nextInt();
			return add(key, priority);
	}
	
	public boolean add(E key , int priority) {
		
		Node<E> temp = new Node<E>(key, priority);
		if(this.root == null){
			this.root = temp;
				return true;
		}
		Stack<Node<E>> stack = new Stack<Node<E>>();
		Node<E> route = this.root;
		int compare = temp.data.compareTo(route.data);
		stack.push(route);
		while(route != null){
			if(compare > 0){
				if(route.right == null){
					route.right = temp;
					reheap(stack, temp);
						return true;
					
				}
				route = route.right;
				stack.push(route);
			}
			
				if(compare < 0){
					if(route.left == null){
					route.left = temp;
					reheap(stack, temp);
						return true;
				}
				route = route.left;
				stack.push(route);
			}
		}
						return false;
	}
	
	private void reheap(Stack<Node<E>> stack, Node<E> temp){
		while(!stack.isEmpty()){
			Node<E> route = stack.pop();
			int compare = temp.data.compareTo(route.data);
			if(route.priority > temp.priority){
				break;
			} else {
				if(compare > 0){
					temp = route.rotateLeft();
				} else {
					temp = route.rotateRight();
				}
				if(!stack.isEmpty()){
					Node<E> comp = stack.peek();
					if(comp.left == route){
						comp.left = temp;
					} else {
						comp.right = temp;
						}
				} else {
					this.root = temp;
				}
			}
		}
	}
	
	public boolean delete(E key){
		if(this.root == null || this.find(root, key) == false || key == null){
			return false;
			
		} else {
			
			this.root = delete(this.root, key);
			return true;
		}
	}
	
	public Node<E> delete(Node<E> root, E key){
		int compare = key.compareTo(root.data);
		
		if(compare > 0){
			root.right = delete(root.right, key);
		}
		else if(compare < 0){
			root.left = delete(root.left, key);
		}
		else if(root.right == null){
			Node<E> newRoot = root.left;
			root = newRoot;
		}
		else if(root.left == null){
			Node<E> newRoot = root.right;
			root = newRoot;
		}
		else if(root.right.priority > root.left.priority){
			root = root.rotateLeft();
			root.left = delete(root.left, key);
		}
		else if(root.right.priority < root.left.priority){
			root = root.rotateRight();
			root.right = delete(root.right, key);
		}else {
			
			return null;
		}
		return root;
	}
	
	private boolean find(Node<E> root, E key) {
		int compare = key.compareTo(root.data);
		if (root == null || key == null) {
			return false;
		}else if (compare == 0) {
			return true;
		}else if (compare > 0) {
			return find(root.right, key);
		}else {
			return find(root.left, key);
		}
	}
	
	public boolean find(E key) {
		return find(root, key);
	}
	
	
	public String toString() {
        StringBuilder str = new StringBuilder();
        preOrderTraverse(root, 1, str);
        	return str.toString();
    }

    
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder string) {
        for (int i = 1; i < depth; i++) {
            string.append("  ");
        }
        
        if (node == null) {
            string.append("null\n");
            
        } else {
        	
            string.append(node.toString());
            string.append("\n");
            preOrderTraverse(node.left, depth + 1, string);
            preOrderTraverse(node.right, depth + 1, string);
        }
    }
    
	public static void main(String[] args) { 
		
    		Treap<Integer>testTree = new Treap <Integer>();
    		testTree.add(4,19); 
    		testTree.add(2,31);
    		testTree.add(6,70); 
    		testTree.add(1,84);
    		testTree.add(3,12); 
    		testTree.add(5,83);
    		testTree.add(7,26);
    		System.out.println("PRINTING THE TREE\n"+testTree);
    		System.out.println("FIND OPERATION: "+testTree.find(7));
    		System.out.println("TO STRING"+testTree.toString());
    		System.out.println("DELETE OPERATION "+testTree.delete(7));
    		System.out.println("PRINTING POST DELETION: "+testTree);
    }
}