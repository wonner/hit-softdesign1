package biconnected;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class AdjGraph {
	
	private VertexNode[] verlist;
	private int n,e;
	private boolean[] visited;
	private int[] dfn;
	private int count;
	
	public void createAdjGraph() throws IOException
	{
		FileReader fr=new FileReader("F://666/biconnected.txt"); //文件位置
		BufferedReader br=new BufferedReader(fr);
		String str;
		str=br.readLine();//读取n，e
		String[] stringlist=str.split(",");
		this.n=Integer.parseInt(stringlist[0]);
		this.verlist=new VertexNode[n];
		for(int i=0;i<this.n;i++)
		{
			this.verlist[i]=new VertexNode();
		}
		this.e=Integer.parseInt(stringlist[1]);
		this.visited=new boolean[n];
		this.dfn=new int[n];
		str=br.readLine();//读取顶点
		stringlist=str.split(",");
		for(int i=0;i<n;i++)
		{
			this.verlist[i].setVertex(stringlist[i]);
		}
		for(int i=0;i<e;i++)
		{
			str=br.readLine();//读取边
			stringlist=str.split(",");
			int index1=this.findIndex(stringlist[0]);
			int index2=this.findIndex(stringlist[1]);
			EdgeNode temp=new EdgeNode(index2,this.verlist[index1].getFirstedge()); //无向图
			this.verlist[index1].setFirstedge(temp);
			temp=new EdgeNode(index1,this.verlist[index2].getFirstedge());
			this.verlist[index2].setFirstedge(temp);
		}
		
	}
	
	public int findIndex(String str)
	{
		int i;
		for(i=0;i<n;i++)
		{
			if(this.verlist[i].getVertex().equals(str))
				break;
		}
		return i;
	}
	
	public void findArtis()
	{
		this.dfsTraverse();
		int[] sort=this.postOrder();
		int[] low=new int[this.n];
		//求low数组
		for(int i=0;i<this.n;i++)
		{
			int index=sort[i];
			low[index]=this.dfn[index];
			EdgeNode child=this.verlist[index].getFirstedge();
			while(child!=null)
			{
				if(child.getIsTreeEdge())
				{
					int temp=child.getIndex();
					if(low[temp]!=0&&low[temp]<low[index])
						low[index]=low[temp];
				}
				else
				{
					if(this.isBackEdge(index,child.getIndex()))
							if(this.dfn[child.getIndex()]<low[index])
								low[index]=this.dfn[child.getIndex()];
				}
				child=child.getNext();
			}
		}
		//树根是否为关节点判断
		EdgeNode temp=this.verlist[0].getFirstedge();
		int cnt=0;
		boolean tag=false;
		while(temp!=null)
		{
			if(temp.getIsTreeEdge())
				cnt++;
			if(cnt>=2)
			{
				tag=true;
				break;
			}
			temp=temp.getNext();
		}
		if(tag)
			this.verlist[0].setIsArtisTrue();
		//非树根关节点判断
		for(int i=1;i<this.n;i++)
		{
			EdgeNode tmp=this.verlist[i].getFirstedge();
			while(tmp!=null)
			{
				if(tmp.getIsTreeEdge())
					if(low[tmp.getIndex()]>=this.dfn[i])
					{
						this.verlist[i].setIsArtisTrue();
						break;
					}
				tmp=tmp.getNext();
			}
		}
	}
	
	public void dfsTraverse()
	{
		this.count=1;
		int i;
		for(i=0;i<this.n;i++)
			this.visited[i]=false;
		for(i=0;i<this.n;i++)
			if(!this.visited[i])
				this.dfs(i);
	}
	
	public void dfs(int index)
	{
		this.visited[index]=true;
		this.dfn[index]=count++;
		EdgeNode next=this.verlist[index].getFirstedge();
		while(next!=null)
		{
			int nextindex=next.getIndex();
			if(!this.visited[nextindex])
			{
				next.setTreeEdgeture();//标记树边
				this.dfs(nextindex);
			}
			next=next.getNext();
		}
	}
	
	public int[] postOrder()
	{
		int[] sort=new int[this.n];
		Stack<Integer> stack=new Stack<>();
		stack.push(0);
		EdgeNode next=this.verlist[0].getFirstedge();
		int nextindex;
		int i=0;
		while(!stack.isEmpty())
		{
			while(next!=null)
			{
				boolean tag=false;
				if(next.getIsTreeEdge())
				{
					for(int j=0;j<i;j++)
					{
						if(next.getIndex()==sort[j])
						{
							tag=true;
							break;
						}
					}
					if(!tag)
					{
						nextindex=next.getIndex();
						stack.push(nextindex);
						next=this.verlist[nextindex].getFirstedge();
					}
					else
						next=next.getNext();
					
				}
				else
					next=next.getNext();
			}
			sort[i]=stack.pop();
			if(!stack.isEmpty())
			next=this.verlist[stack.peek()].getFirstedge();
			i++;
		}
		return sort;
	}
	
	public boolean isBackEdge(int p,int q)
	{
		EdgeNode temp=this.verlist[q].getFirstedge();
		while(temp!=null)
		{
			if(temp.getIndex()==p)
				if(temp.getIsTreeEdge())
					return false;
				else
					break;
			temp=temp.getNext();
		}
		return true;
	}
	
	public void printArtis()
	{
		for(int i=0;i<this.n;i++)
		{
			if(this.verlist[i].getIsArtis())
				System.out.print("关节点为："+this.verlist[i].getVertex()+" ");
		}
	}
	
	public int getN()
	{
		return this.n;
	}
	
	public VertexNode getVertexNode(int i)
	{
		return this.verlist[i];
	}

}
