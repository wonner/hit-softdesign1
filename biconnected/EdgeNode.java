package biconnected;

public class EdgeNode {
	
	private int adjver;
	private EdgeNode next;
	private boolean istreeedge;
	
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
	
	public void setTreeEdgeture()
	{
		this.istreeedge=true;
	}
	
	public boolean getIsTreeEdge()
	{
		return this.istreeedge;
	}

}
