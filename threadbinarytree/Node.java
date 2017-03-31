package threadbinarytree;

public class Node {
	
	private Node lchild;
	private boolean ltag;
	private char data;
	private Node rchild;
	private boolean rtag;
	
	public Node(char data)
	{
		this.data=data;
	}
	
	public void setLChild(Node lchild,boolean ltag)
	{
		this.lchild=lchild;
		this.ltag=ltag;
	}
	
	public void setRChild(Node rchild,boolean rtag)
	{
		this.rchild=rchild;
		this.rtag=rtag;
	}
	
	public Node getLChild()
	{
		return this.lchild;
	}
	
	public boolean getLTag()
	{
		return this.ltag;
	}
	
	public boolean getRTag()
	{
		return this.rtag;
	}
	
	public char getData()
	{
		return this.data;
	}
	
	public Node getRChild()
	{
		return this.rchild;
	}


}
