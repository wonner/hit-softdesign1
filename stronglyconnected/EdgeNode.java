package stronglyconnected;

public class EdgeNode {
	
	private int adjver;
	private EdgeNode next;
	
	public EdgeNode(int adjver,EdgeNode next)
	{
		this.adjver=adjver;
		this.next=next;
	}
	
	public EdgeNode getNext()
	{
		return this.next;
	}
	
	public int getIndex()
	{
		return this.adjver;
	}

}
