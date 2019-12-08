package hw3;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> idlList = new IDLList();
		
		System.out.println(idlList.toString()); // To test empty string throwing error
		
		idlList.add(15); //add 15 as first element at head
        System.out.println("The first element is " + idlList.toString() + "\n"); //Prints The first element is 15

        idlList.add(0,10); // Add value 10 at the head
        System.out.println("After add 10 at the head updated list " + idlList.toString() + "\n"); //Prints After add 10 at the head updated list 10   15  

        idlList.add(1,12); // Add value 12 at index 1
        System.out.println("After add 12 at position 1 updated list " + idlList.toString() + "\n"); //Prints After add 12 at position 1 updated list 10   12   15   

        idlList.append(20); // Append 20
        System.out.println("After append updated list " + idlList.toString() + "\n"); //Prints After append updated list 10   12   15   20

        System.out.println("Value at first index " + idlList.get(1) + "\n"); //Prints data at position 1 "Value at first index 12

        System.out.println("Object at Head " + idlList.getHead() + "\n"); //Use getHead function and print the object at head "Object at Head 10

        System.out.println("Object at Tail " + idlList.getLast() + "\n"); //Use getLast function and print the last object "Object at Tail 20

        System.out.println("List size " + idlList.size() + "\n"); //Use size function to print size of list "List size 4"

        
        System.out.println("List before remove at Head " + idlList.toString()); //Prints "List before remove at Head 10   12   15   20" 
        System.out.println("Removed element " + idlList.remove()); // remove and return element at the head prints "Removed element 10"
        System.out.println("List after remove at head " + idlList.toString() + "\n"); //Prints "List after remove at head 12   15   20"

        
        System.out.println("List before remove " + idlList.toString()); //Prints "List before remove 12   15   20"  
        System.out.println("Removed element " + idlList.removeLast());// remove and return the element at the tail prints "Removed element 20"
        System.out.println("List after remove at tail " + idlList.toString() + "\n"); //Prints "List after remove at tail 12   15"

        idlList.add(2, 17); //Add value 17 at position 2
        idlList.add(3, 17); //Add value 17 at position 3
        System.out.println("Updated list " + idlList.toString());// Prints updated list "Updated list 12 15 17 17"

        idlList.remove(17); //Removes first occurrence of 17
        System.out.println("After removing first occurrence of 17 list " + idlList.toString());// Prints "After removing first occurrence of 17 list 12   15   17"
        
        System.out.println(idlList.removeAt(20)); //Index out of bounds

	}

}
