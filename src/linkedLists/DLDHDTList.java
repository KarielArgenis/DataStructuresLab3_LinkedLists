package linkedLists;

import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 

	public DLDHDTList() { 
		header = new DNode<E>();
		trailer = new DNode<E>();
		header.setNext(trailer);
		trailer.setPrev(header);
		length = 0;
		
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dNuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dNuevo); 
		trailer.setPrev(dNuevo); 
		dNuevo.setPrev(nBefore); 
		dNuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dNuevo = (DNode<E>) nuevo;
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dNuevo); 
		nAfter.setPrev(dNuevo); 
		dNuevo.setPrev(nBefore); 
		dNuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> dNuevo = (DNode<E>) nuevo;
		DNode<E> nAfter = (DNode<E>) target;
		DNode<E> nBefore = nAfter.getPrev();
		nBefore.setNext(dNuevo); 
		nAfter.setPrev(dNuevo); 
		(dNuevo).setPrev(nBefore); 
		(dNuevo).setNext(nAfter); 
		length++; 
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		if (target==trailer)
			throw new NoSuchElementException("getNodeAfter: Target node is trailer dummy node.");
		return ((DNode<E>) target).getNext(); 
	}

	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		if (header==trailer)
			throw new NoSuchElementException("getNodeBefore: Target node is header dummy node.");
		return ((DNode<E>) target).getPrev();
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> dTarget = (DNode<E>) target;
		DNode<E> nBefore = dTarget.getPrev();
		DNode<E> nAfter = dTarget.getNext();
		
		nBefore.setNext(nAfter);
		nAfter.setPrev(nBefore);
		dTarget.clean();
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		DNode<E> current = header.getNext();
		while(current != trailer) {
			DNode<E> nextNode = current.getNext();
			current.clean();
			current = nextNode;
		}
		header.setNext(trailer);
		trailer.setPrev(header);
		length=0;
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}

}
