package dynamic_huffman;

public class HuffmanNode {
	
	private Character ch;
	private Integer weight;
	private Integer parent;
	private Integer lchild;
	private Integer rchild;
	
	public HuffmanNode(Character ch,Integer weight,Integer parent,Integer lchild,Integer rchild)
	{
		this.ch=ch;
		this.weight=weight;
		this.parent=parent;
		this.lchild=lchild;
		this.rchild=rchild;
	}
	
	public Character getCh()
	{
		return this.ch;
	}
	
	public void setWLR(Integer weight,Integer lchild,Integer rchlid)
	{
		this.weight=weight;
		this.lchild=lchild;
		this.rchild=rchlid;
	}
	
	public void addWeight()
	{
		this.weight++;
	}
	
	public Integer getParent()
	{
		return this.parent;
	}
	
	public Integer getLchild()
	{
		return this.lchild;
	}
	
	public void setLchild(int lchild)
	{
		this.lchild=lchild;
	}
	
	public int getRchild()
	{
		return this.rchild;
	}
	
	public void setRchild(int rchild)
	{
		this.rchild=rchild;
	}
	
	public void setParent(int parent)
	{
		this.parent=parent;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
	
	public void setWeight(int weight)
	{
		this.weight=weight;
	}
}
