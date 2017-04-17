package min_spanning_tree;

public class KruskalNode {
	
	private double cost;
	private int begin;
	private int end;
	
	public KruskalNode(double cost,int begin,int end)
	{
		this.cost=cost;
		this.begin=begin;
		this.end=end;
	}
	
	public int getBegin()
	{
		return this.begin;
	}
	
	public int getEnd()
	{
		return this.end;
	}
	
	public double getCost()
	{
		return this.cost;
	}

}
