import java.util.Iterator;

public class LinkStrand implements IDnaStrand {
	String myInfo; 
	int myAppends;
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
		if(index>=mySize || index<0){
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if(index<myIndex){
			myIndex = 0;
			current = myFirst;
			myLocalIndex = 0;
		}
		int counter = myIndex;
		Node temp = current;
		while(counter != index){
			counter++;
			myLocalIndex++;
			if(myLocalIndex == current.info.length()){
				myLocalIndex= 0;
				current = current.next;
			}
		}
		current = temp;
		myIndex = counter;
		System.out.println(temp.info.charAt(myLocalIndex));
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
