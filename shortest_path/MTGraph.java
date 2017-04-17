package shortest_path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class MTGraph {
	
	String[] vertex;
	double[][] edge;
	int n,e;
	double[] D;
	int[] P;
	boolean[] S;
	double[][] D1;
	int[][] P1;
	
	public void createMTGraph() throws IOException
	{
		FileReader fr=new FileReader("F://666/shortest_path.txt"); //文件位置
		BufferedReader br=new BufferedReader(fr);
		String str;
		str=br.readLine();//读取n，e
		String[] stringlist=str.split(",");
		this.n=Integer.parseInt(stringlist[0]);
		this.vertex=new String[n];
		this.edge=new double[n][n];
		D=new double[n];
		P=new int[n];
		S=new boolean[n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				edge[i][j]=Double.POSITIVE_INFINITY;
			}
		}
		this.e=Integer.parseInt(stringlist[1]);
		str=br.readLine();//读取顶点
		stringlist=str.split(",");
		for(int i=0;i<n;i++)
		{
			this.vertex[i]=stringlist[i];
		}
		for(int i=0;i<e;i++)
		{
			str=br.readLine();//读取边
			stringlist=str.split(",");
			int index1=this.findIndex(stringlist[0]);
			int index2=this.findIndex(stringlist[1]);
			edge[index1][index2]=Double.parseDouble(stringlist[2]);
		}
		
	}
	
	public int findIndex(String str)
	{
		int i;
		for(i=0;i<n;i++)
		{
			if(this.vertex[i].equals(str))
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
		{
			D[i]=edge[index][i];
			P[i]=index;
		}
		S[index]=true;
		for(int i=1;i<n;i++)
		{
			int w=minCost();
			S[w]=true;
			for(int j=0;j<n;j++)
			{
				if(!S[j])
				{
					double sum=D[w]+edge[w][j];
					if(sum<D[j])
					{
						D[j]=sum;
						P[j]=w;
					}
				}
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
	
	public void floyd()
	{
		D1=new double[n][n];
		P1=new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				D1[i][j]=edge[i][j];
				P1[i][j]=-1;
			}
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(D1[i][k]+D1[k][j]<D1[i][j])
					{
						D1[i][j]=D1[i][k]+D1[k][j];
						P1[i][j]=k;
					}
	}
	
	public void dijPrintTest()
	{
		for(int i=0;i<n;i++)
			System.out.print(D[i]+" ");
	}
	
	public void floydPrintTest()
	{
		for(int i=0;i<n;i++)
			System.out.print(D1[0][i]+" ");
	}
	
	public void createSparseGraph(int n)
	{
		Random random=new Random();
		this.n=n;
		this.vertex=new String[n];
		this.edge=new double[n][n];
		D=new double[n];
		P=new int[n];
		S=new boolean[n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				edge[i][j]=Double.POSITIVE_INFINITY;
			}
		}
		this.e=n-1;
		for(int i=0;i<n-1;i++)
		{
			edge[i][i+1]=random.nextDouble()*100;
		}
	}

}
