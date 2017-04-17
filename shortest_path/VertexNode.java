package shortest_path;

public class VertexNode {
	
	private String vertex;
	private EdgeNode firstedge;
	
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
	
	
}
