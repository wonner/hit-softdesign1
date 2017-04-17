package biconnected;

public class VertexNode {
	
	private String vertex;
	private EdgeNode firstedge;
	private boolean isartis;
	
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
	
	public void setIsArtisTrue()
	{
		this.isartis=true;
	}
	
	public boolean getIsArtis()
	{
		return this.isartis;
	}
	

}
