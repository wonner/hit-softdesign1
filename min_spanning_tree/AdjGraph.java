package min_spanning_tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class AdjGraph {
	
	private VertexNode[] verlist;
	private int n,e;
	
	public void createAdjGraph() throws IOException
	{
		FileReader fr=new FileReader("F://666/min_spanning_tree.txt"); //文件位置
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
			EdgeNode temp=new EdgeNode(index2,Double.parseDouble(stringlist[2]),this.verlist[index1].getFirstedge()); //无向图
			this.verlist[index1].setFirstedge(temp);
			temp=new EdgeNode(index1,Double.parseDouble(stringlist[2]),this.verlist[index2].getFirstedge());
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
	
	public double prim()
	{
		double[] lowcost=new double[this.n];
		int[] closset=new int[this.n];
		double sumweight=0;
		lowcost[0]=Double.POSITIVE_INFINITY;
		EdgeNode temp=this.verlist[0].getFirstedge();
		while(temp!=null)
		{
			int index=temp.getIndex();
			double weight=temp.getWeight();
			lowcost[index]=weight;
			temp=temp.getNext();
		}
		for(int i=1;i<n;i++)
		{
			double min=lowcost[0];
			int k=0;
			for(int j=1;j<n;j++)
			{
				if(lowcost[j]<min&&lowcost[j]!=0)
				{
					min=lowcost[j];
					k=j;
				}
			}
			sumweight+=min;
			temp=verlist[k].getFirstedge();
			while(true) //树边只修改一侧
			{
				if(temp.getIndex()==closset[k])
				{
					temp.setistreeedgetrue();
					break;
				}
				else temp=temp.getNext();
			}
			lowcost[k]=Double.POSITIVE_INFINITY;
			temp=this.verlist[k].getFirstedge();
			while(temp!=null)
			{
				int index=temp.getIndex();
				double weight=temp.getWeight();
				if(lowcost[index]==0||weight<lowcost[index]&&lowcost[index]!=Double.POSITIVE_INFINITY)
				{
					lowcost[index]=weight;
					closset[index]=k;
				}
				temp=temp.getNext();
			}
		}
		return sumweight;
	}
	
	public void initTreeEdge()
	{
		for(int i=0;i<n;i++)
		{
			EdgeNode temp=this.verlist[i].getFirstedge();
			while(temp!=null)
			{
				temp.setistreeedgefalse();
				temp=temp.getNext();
			}
		}
	}
	
	public double kruskal()
	{
		int bnf,edf;
		double sumweight=0;
		int[] parents=new int[n];
		for(int i=0;i<n;i++)
			parents[i]=-1;
		KruskalNode[] edges=this.createEdge(); 
		MyComparator mComparator = new MyComparator(); 
		Arrays.sort(edges,mComparator);  //实现接口，对树边权值进行排序
		for(int i=0;i<e;i++)
		{
			bnf=find(edges[i].getBegin(),parents);
			edf=find(edges[i].getEnd(),parents);
			if(bnf!=edf) //是否属于同一连通分量
			{
				parents[bnf]=edf;
				sumweight+=edges[i].getCost();
				EdgeNode temp=verlist[edges[i].getBegin()].getFirstedge();
				while(true) //树边只修改一侧
				{
					if(temp.getIndex()==edges[i].getEnd())
					{
						temp.setistreeedgetrue();
						break;
					}
					else temp=temp.getNext();
				}
			}
		}
		return sumweight;
	}
	
	public int find(int p,int[] parents)
	{
		int temp=p;
		while(parents[temp]!=-1)
			temp=parents[temp];
		return temp;
	}
	
	public KruskalNode[] createEdge()
	{
		KruskalNode[] edges=new KruskalNode[e];
		int j=0; boolean tag;
		for(int i=0;i<n;i++)
		{
			EdgeNode temp=this.verlist[i].getFirstedge();
			while(temp!=null)
			{
				int begin=i;
				int end=temp.getIndex();
				tag=true;
				for(int k=0;k<j;k++)
				{
					if(edges[k].getBegin()==end&&edges[k].getEnd()==begin)
					{
						tag=false;
						break;
					}
				}
				if(tag)
				{
					edges[j]=new KruskalNode(temp.getWeight(),begin,end);
					j++;
				}
				temp=temp.getNext();
			}
		}
		return edges;
	}
	
	//comparator接口
	private class MyComparator implements Comparator<KruskalNode>
	{
		public int compare(KruskalNode o1,KruskalNode o2)
		{
			if(o1.getCost()>o2.getCost())
				return 1;
			else if(o1.getCost()<o2.getCost())
				return -1;
			else
				return 0;
		}
	}
	
	public void printMinTree()
	{
		for(int i=0;i<n;i++)
		{
			EdgeNode temp=this.verlist[i].getFirstedge();
			while(temp!=null)
			{
				if(temp.getistreeedge())
					System.out.print("("+verlist[i].getVertex()+","+verlist[temp.getIndex()].getVertex()+")");
				temp=temp.getNext();
			}
			
		}
	}
	
	public void createSparseGraph(int n)
	{
		this.n=n;
		this.e=n;
		this.verlist=new VertexNode[n];
		for(int i=0;i<this.n;i++)
		{
			this.verlist[i]=new VertexNode();
		}
		Random random=new Random();
		for(int i=0;i<n-1;i++)
		{
			EdgeNode temp=new EdgeNode(i+1,random.nextDouble()*100,verlist[i].getFirstedge());
			verlist[i].setFirstedge(temp);
			EdgeNode temp1=new EdgeNode(i,temp.getWeight(),verlist[i+1].getFirstedge());
			verlist[i+1].setFirstedge(temp1);
		}
		EdgeNode temp=new EdgeNode(n-1,random.nextDouble()*100,verlist[0].getFirstedge());
		verlist[0].setFirstedge(temp);
		EdgeNode temp1=new EdgeNode(0,temp.getWeight(),verlist[n-1].getFirstedge());
		verlist[n-1].setFirstedge(temp1);
	}
	
	public void createDenseGraph(int n)
	{
		this.n=n;
		this.e=n*(n-1)/2;
		this.verlist=new VertexNode[n];
		for(int i=0;i<this.n;i++)
		{
			this.verlist[i]=new VertexNode();
		}
		Random random=new Random();
		for(int i=0;i<this.n;i++)
		{
			for(int j=n-i-1;j>=1;j--)
			{
				EdgeNode temp=new EdgeNode(n-j,random.nextDouble()*100,verlist[i].getFirstedge());
				verlist[i].setFirstedge(temp);
				EdgeNode temp1=new EdgeNode(i,temp.getWeight(),verlist[n-j].getFirstedge());
				verlist[n-j].setFirstedge(temp1);
			}
		}
	}
}
