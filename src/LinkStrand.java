import java.util.Iterator;

public class LinkStrand implements IDnaStrand {
	private String myInfo; 
	private int myAppends;
	private int myIndex;
	private Node myFirst,myLast;
	private long mySize;
	private Node current;
	private int myLocalIndex;


	public LinkStrand(){
		this("");
	}
	public LinkStrand(String strand){
		initialize(strand);
	}

	public String toString(){
		Node current = myFirst;
		StringBuilder s = new StringBuilder("");
		while(current!= null){
			s.append(current.info);
			current = current.next;
		}
		String toReturn = s.toString();
		return toReturn;
	}

	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myInfo = source;
		myAppends = 0;
		myIndex = 0;
		myLocalIndex = 0;
		myFirst = new Node(source);
		myLast = myFirst;
		current = myFirst;
		mySize = source.length();
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder copy = new StringBuilder(myInfo);
		copy.reverse();
		LinkStrand lC = new LinkStrand(copy.toString());
		return lC;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) throws IndexOutOfBoundsException{
		Node temp = current;
		int counter = myIndex;
		if(index>=mySize || index<0){
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if(index<myIndex){
			myIndex = 0;
			current = myFirst;
			myLocalIndex = 0;
		}
		while(counter != index){
			counter++;
			myLocalIndex++;
			if(myLocalIndex >= temp.info.length()){
				myLocalIndex= 0;
				temp = temp.next;
			}
		}
		myIndex = counter;
		current = temp;
		return temp.info.charAt(myLocalIndex);
	}


	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}


}
