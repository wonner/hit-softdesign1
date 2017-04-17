package min_spanning_tree;

public class EdgeNode {
	
	private int adjver;
	private double weight;
	private boolean istreeedge;
	private EdgeNode next;
	
	public EdgeNode(int adjver,double weight,EdgeNode next)
	{
		this.adjver=adjver;
		this.weight=weight;
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
	
	public double getWeight()
	{
		return this.weight;
	}
	
	public void setistreeedgetrue()
	{
		this.istreeedge=true;
	}
	
	public void setistreeedgefalse()
	{
		this.istreeedge=false;
	}
	
	public boolean getistreeedge()
	{
		return this.istreeedge;
	}

}
