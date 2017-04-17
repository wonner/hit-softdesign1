package shortest_path;

public class EdgeNode {
	
	private int adjver;
	private double cost;
	private EdgeNode next;
	
	public EdgeNode(int adjver,double cost,EdgeNode next)
	{
		this.adjver=adjver;
		this.cost=cost;
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
	
	public double getCost()
	{
		return this.cost;
	}

}
