package stronglyconnected;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

import biconnected.VertexNode;

public class AdjGraph {
	
	private VertexNode[] verlist;
	private int n,e;
	private boolean[] visited;
	private int[] dfn;
	private int[] order;
	private int count;
	private boolean isstronglyconnected;
	
	public void createAdjGraph() throws IOException
	{
		FileReader fr=new FileReader("F://666/stronglyconnected.txt"); //文件位置
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
		this.order=new int[n];
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
			EdgeNode temp=new EdgeNode(index2,this.verlist[index1].getFirstedge()); //有向图
			this.verlist[index1].setFirstedge(temp);
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
	
	public void findComponent()
	{
		this.dfsTraverse();
		AdjGraph transgraph=this.graphTranspose();
		transgraph.rev_dfsTraverse();
		transgraph.printComponent();
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
		this.order[count-1]=index;
		this.dfn[index]=count++;
		EdgeNode next=this.verlist[index].getFirstedge();
		while(next!=null)
		{
			int nextindex=next.getIndex();
			if(!this.visited[nextindex])
				this.dfs(nextindex);
			next=next.getNext();
		}
	}
	
	public void rev_dfsTraverse()
	{
		this.count=0;
		int i;
		for(i=0;i<this.n;i++)
			this.visited[i]=false;
		for(i=this.n-1;i>0;i--)
		{
			int index=this.order[i];
			if(!this.visited[index])
			{
				count++;
				this.rev_dfs(index);
			}
		}
		if(count==1)
			this.isstronglyconnected=true;
	}
	
	public void rev_dfs(int index)
	{
		this.visited[index]=true;
		this.verlist[index].setComponent(this.count);
		EdgeNode next=this.verlist[index].getFirstedge();
		while(next!=null)
		{
			int nextindex=next.getIndex();
			if(!this.visited[nextindex])
				this.rev_dfs(nextindex);
			next=next.getNext();
		}
	}
	
	public AdjGraph graphTranspose()
	{
		AdjGraph trans=new AdjGraph();
		trans.verlist=new VertexNode[n];
		for(int i=0;i<this.n;i++)
		{
			trans.verlist[i]=new VertexNode();
			trans.verlist[i].setVertex(this.verlist[i].getVertex());
		}
		trans.n=this.n;
		trans.e=this.e;
		trans.visited=new boolean[this.n];
		trans.order=this.order;
		for(int i=0;i<this.n;i++)
		{
			EdgeNode temp=this.verlist[i].getFirstedge();
			while(temp!=null)
			{
				EdgeNode p=new EdgeNode(i,trans.verlist[temp.getIndex()].getFirstedge());
				trans.verlist[temp.getIndex()].setFirstedge(p);
				temp=temp.getNext();
			}
		}
		return trans;
	}
	
	public void printComponent()
	{
		if(this.isstronglyconnected)
			System.out.print("该图为强连通图");
		else
		{
			for(int i=1;i<=count;i++)
			{
				System.out.print("强连通分量"+i+"为：");
				for(int j=0;j<this.n;j++)
				{
					if(this.verlist[j].getComponent()==i)
						System.out.print(this.verlist[j].getVertex()+" ");
				}
				System.out.println();
			}
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
