package shortest_path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class AdjGraph {
	
	private VertexNode[] verlist;
	private int n,e;
	double[] D;
	int[] P;
	boolean[] S;
	
	public void createAdjGraph() throws IOException
	{
		FileReader fr=new FileReader("F://666/shortest_path.txt"); //文件位置
		BufferedReader br=new BufferedReader(fr);
		String str;
		str=br.readLine();//读取n，e
		String[] stringlist=str.split(",");
		this.n=Integer.parseInt(stringlist[0]);
		this.verlist=new VertexNode[n];
		D=new double[n];
		P=new int[n];
		S=new boolean[n];
		for(int i=0;i<this.n;i++)
		{
			this.verlist[i]=new VertexNode();
		}
		this.e=Integer.parseInt(stringlist[1]);
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
			EdgeNode temp=new EdgeNode(index2,Double.parseDouble(stringlist[2]),this.verlist[index1].getFirstedge()); //有向图
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
	
	public void dijkstra(String str)
	{
		int index;
		if(str==null)
			index=0;
		else
			index=this.findIndex(str);
		for(int i=0;i<n;i++)
			D[i]=Double.POSITIVE_INFINITY;
		EdgeNode temp=verlist[index].getFirstedge();
		while(temp!=null)
		{
			D[temp.getIndex()]=temp.getCost();
			temp=temp.getNext();
		}
		S[index]=true;
		for(int i=1;i<n;i++)
		{
			int w=minCost();
			S[w]=true;
			temp=verlist[w].getFirstedge();
			while(temp!=null)
			{
				int j=temp.getIndex();
				if(!S[j])
				{
					double sum=D[w]+temp.getCost();
					if(sum<D[j])
					{
						D[j]=sum;
						P[j]=w;
					}
				}
				temp=temp.getNext();
			}
		}
	}
	
	public int minCost()
	{
		double min=Double.POSITIVE_INFINITY;
		int index=Integer.MAX_VALUE;
		for(int i=0;i<n;i++)
		{
			if(!S[i])
			{
				if(D[i]<min)
				{
					min=D[i];
					index=i;
				}
			}
		}
		return index;
	}
	
	public void printTest()
	{
		for(int i=0;i<n;i++)
			System.out.print(D[i]+" ");
	}
	
	public void createSparceGraph(int n)
	{
		Random random=new Random();
		this.n=n;
		this.verlist=new VertexNode[n];
		D=new double[n];
		P=new int[n];
		S=new boolean[n];
		for(int i=0;i<this.n;i++)
		{
			this.verlist[i]=new VertexNode();
		}
		this.e=n-1;
		for(int i=0;i<n-1;i++)
		{
			EdgeNode temp=new EdgeNode(i+1,random.nextDouble()*100,verlist[i].getFirstedge());
			verlist[i].setFirstedge(temp);
		}
	}

}
