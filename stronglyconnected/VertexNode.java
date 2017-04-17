package stronglyconnected;

public class VertexNode {
	
	private String vertex;
	private EdgeNode firstedge;
	private int component;
	
	public void setVertex(String vertex)
	{
		this.vertex=vertex;
	}
	
	public String getVertex()
	{
		return this.vertex;
	}
	
	public EdgeNode getFirstedge()
	{
		return this.firstedge;
	}
	
	public void setFirstedge(EdgeNode firstedge)
	{
		this.firstedge=firstedge;
	}
	
	public void setComponent(int i)
	{
		this.component=i;
	}
	
	public int getComponent()
	{
		return this.component;
	}

}
