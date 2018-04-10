package lesson10.labs.prob6;

//Show the methods are not threadsafe.
//Then modify so that they are threadsafe.
public class Queue {
	class Node {
		Object value;
		Node next;
	}
	public int count = 0;
	private Node head;
	private Node tail;
	public void add(Object newValue) {
		Node n = new Node();
		if(head == null) head = n;
		else tail.next = n;
		tail = n;
		tail.value = newValue;
		count++;
	}
	public Object remove() {
		if(head == null) return null;
		Node n = head;
		head = n.next;
		count--;
		return n.value;
	}

	public synchronized void sync_add(Object newValue) {
		Node n = new Node();
		if(head == null) head = n;
		else tail.next = n;
		tail = n;
		tail.value = newValue;
		count++;
	}
	public synchronized Object sync_remove() {
		if(head == null) return null;
		Node n = head;
		head = n.next;
		count--;
		return n.value;
	}
}
