package hw3;
import java.util.*;

public class IDLList<E> {
	
	private class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		public Node (E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		public Node (E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	public IDLList (){
		head = new Node<E>(null, null, null);
		tail=head;
		size=0;
		indices=new ArrayList<Node<E>>();
	}
	public boolean add (Integer index, E elem){
		Node<E> item ;
		Node<E> temp = head;
		if(index<0){
			System.out.println("Index is less than 0");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}
		if(!(index instanceof Integer)) {
			System.out.println("Please enter a valid integer");
			throw new IllegalArgumentException("Please enter a valid Integer");
		}
		if(index > size){
			System.out.println("Index is greater than size");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}else {
			if(index == 0){
				return add(elem);
			}
			if(index == size){
				return append(elem);
			}
			Node<E> update = indices.get(index);
			temp = update.prev;
			item = new Node<>(elem, temp, update);
			temp.next = item;
			update.prev = item;
			indices.add(index, item);
			size++;
		}
		
		return true;
	}
	
	public boolean add (E elem){
		Node<E> input=new Node<E>(elem, null, head);
		if(head == null){
			head = input;
			tail = input;
			indices.add(0, input);
			size++;
			return true;
		}
		head.prev = input;
		head = input;
		indices.add(0, input);
		size++;
		return true;
	}
	
	public boolean append (E elem){
		Node<E> input = new Node<E>(elem, tail, null);
		if(head == null){
			head = input;
			tail = input;
			indices.add(0, input);
			size++;
			return true;
		}
		tail.next = input;
		tail = input;
		indices.add(size, input);
		size++;
		return true;
	}
	public E get (Integer index){
		if (index < 0) {
			System.out.println("Index is less than zero");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}
		if(index>size){
			System.out.println("Index is greater than size");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}
		if(!(index instanceof Integer)) {
			System.out.println("Please enter a valid integer");
			throw new IllegalArgumentException("Please enter a valid Integer");
		}
		return indices.get(index).data;
	}
	public E getHead (){
		if(head == null){
			System.out.println("Head is Null, Can't fetch the head element");
			throw new NullPointerException("Null pointer");
		}
		return head.data;
	}
	public E getLast (){
		if(tail == null){
			System.out.println("Tail is Null, Can't fetch the tail element");
			throw new NullPointerException("Null pointer");
		}
		return tail.data;
	}
	public int size(){
		return size;
	}
	
	public E remove (){
		if (size == 0){
			System.out.println("No elements are present to be deleted");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}
		E result = head.data;
		if(size == 1){
			tail = null;
			head = null;
			indices.remove(0);
			size--;
			return result;
		}
		Node<E> item = head.next;
		item.prev = null;
		head = item;
		indices.remove(0);
		size--;
		return result;
	}
	public E removeLast (){
		if (size == 0){
			System.out.println("No elements are present to be deleted");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}
		E result = tail.data;
		if(size == 1){
			tail = null;
			head = null;
			indices.remove(0);
			size--;
			return result;
		}
		Node<E> item=tail.prev;
		item.next=null;	
		tail=null;
		tail=item;
		indices.remove(size-1);
		size--;
		return result;
	}
	public E removeAt (Integer index){
		
		if(index < 0 ) {
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		if(index >= size) {
			System.out.println("Index is greater than size");
			throw new ArrayIndexOutOfBoundsException("Array Index out of bounds");
		}
		if(!(index instanceof Integer)) {
			System.out.println("Please enter a valid integer");
			throw new IllegalArgumentException("Please enter a valid Integer");
		}
		if(index == 0){
			E result=head.data;
			remove();
			return result;
		}
		if(index == size-1){
			E result = tail.data;
			removeLast();
			return result;
		}
		Node<E> item = indices.get(index);
		Node<E> previous = item.prev;
		Node<E> next1 = item.next;
		previous.next = next1;
		next1.prev = previous;
		E rst = item.data;
		item = null;
		indices.remove(index);
		size--;
		return rst;
	}
	public boolean remove (E elem){
		Node<E> temp = head;
		int index = -1;
		for(int i = 0;i < size;i++){
			if(temp.data == elem){
				index = i;
				break;
			}
			temp = temp.next;
		}

		if(index==-1){
			return false;
		}else{
			removeAt(index);
			return true;
		}
	}
	public String toString(){
		String result="";
		if(size == 0){
			result = "The list is empty\n";
			return result;
		}
		for(int i = 0;i < size;i++){
			if (get(i) == null){
				result = result +"null   ";
			}else{
				result = result + get(i)+"   ";
			}
		}
		result = result +"\n";
		return result;
	}
	
}
