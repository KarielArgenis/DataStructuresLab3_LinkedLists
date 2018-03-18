package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		if (length==0) {
			first = last = (SNode<E>) nuevo;
			length++;
		}
		else {
			((SNode<E>) nuevo).setNext(first);
			first = (SNode<E>) nuevo;
			length++;
		}
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		if(target==last) {
			last.setNext((SNode<E>) nuevo);
			last = (SNode<E>) nuevo;
			length++;
		}
		else {
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		length++;
		}
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if(target==first) {
			((SNode<E>) nuevo).setNext(first);
			first = (SNode<E>) nuevo;
			length++;
		}
		SNode<E> prev = (SNode<E>) this.getNodeBefore(target);
		prev.setNext((SNode<E>) nuevo);
		((SNode<E>) nuevo).setNext((SNode<E>) target);
		length++;
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first==null)
			throw new NoSuchElementException("getFirstNode(): Linked list is empty.");
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (last==null)
			throw new NoSuchElementException("getFirstNode(): Linked list is empty.");
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		if (target==last)
			throw new NoSuchElementException("getNodeAfter(): Target node is the last node");
		return ((SNode<E>) target).getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if (target==first)
			throw new NoSuchElementException("getNodeBefore(): Target node is the first node.");
		SNode<E> prev = first;
		while(prev != null && prev.getNext() != target)
			prev = prev.getNext();
		return prev;
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		if (target==first) 
			first = first.getNext();
		else if (target==last) {
			SNode<E> prev = (SNode<E>) this.getNodeBefore(target);
			last = prev;
		}
		else {
			SNode<E> prev = (SNode<E>) this.getNodeBefore(target);
			prev.setNext(((SNode<E>) target).getNext());
		}
		((SNode<E>) target).clean();
		length--;
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
