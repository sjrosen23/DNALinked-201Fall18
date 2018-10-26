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
		HashMap<Node, Integer> mappy = new HashMap<Node,Integer>();

		current = myFirst;
		Node prev = current;
		int count = 0;
		if(current.next== null){
			StringBuilder s = new StringBuilder(current.info);
			s.reverse();
			return new LinkStrand(s.toString());
		}
		while(current.next!= null){
			mappy.put(current, count);
			count++;
		}
		
		int counter = 0;
		current = myFirst;

		for(int i = 0; i< count; i++){
			current = myFirst;
			for(int x = count; x >0; x--){
				if(x == mappy.get(current)){
					if(x!= count){
						Node temp = current;
						current.next = null;
						myLast.next = current;
						System.out.println(current.info);
						StringBuilder s = new StringBuilder(current.info);
						s.reverse();
						current.info = s.toString();
						System.out.println(current.info);
						prev.next = temp.next;
					}
				}
				if(x != count){
					prev = prev.next;
				}
				current = current.next;
			}
		}
		return new LinkStrand(toString());
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
