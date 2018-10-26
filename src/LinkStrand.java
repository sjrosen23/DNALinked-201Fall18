import java.util.HashMap;
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
		StringBuilder s = new StringBuilder("");
		Node current = myFirst;
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
		System.out.println("start " + toString());
		HashMap<Node, Integer> mappy = new HashMap<Node,Integer>();
		LinkStrand toReturn = new LinkStrand();
		current = myFirst;
		int count = 1;
		if(current.next== null){
			StringBuilder s = new StringBuilder(current.info);
			s.reverse();
			return new LinkStrand(s.toString());
		}
		while(current.next!= null){
			mappy.put(current, count);
			count++;
			current = current.next;
		}
		System.out.println(count);
		if(count == 2){
			StringBuilder s = new StringBuilder(current.next.info);
			s.reverse();
			StringBuilder b = new StringBuilder(current.info);
			b.reverse();
			return new LinkStrand(s.toString()+b.toString());
		}

		current = myFirst;
		for(int i = count; i> 1; i--){
			current = myFirst;
			for(int b = 1; b< i; b++){
				current = current.next;
			}
			StringBuilder s = new StringBuilder(current.info);
			System.out.println("Appending " + s.toString());
			s.reverse();
			toReturn.append(s.toString());
		}
		System.out.println("Returning " + toReturn.toString());

		return toReturn;
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
		while(myIndex != index){
			myIndex++;
			myLocalIndex++;
			if(myLocalIndex >= current.info.length()){
				myLocalIndex= 0;
				current = current.next;
			}
		}

		return current.info.charAt(myLocalIndex);
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
