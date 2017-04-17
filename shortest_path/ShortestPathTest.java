package shortest_path;

import java.io.IOException;
import java.util.Scanner;

public class ShortestPathTest {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		
		MTGraph g=new MTGraph();
		g.createMTGraph();
		/*System.out.print("please enter the start point:");
		Scanner in=new Scanner(System.in);
		String str=in.nextLine();
		g.dijkstra(str);
		g.dijPrintTest();
		System.out.println();
		*/
		AdjGraph g1=new AdjGraph();
		g1.createAdjGraph();
		/*System.out.print("please enter the start point:");
		str=in.nextLine();
		g1.dijkstra(str);
		g1.printTest();
		System.out.println();
		
		g.floyd();
		g.floydPrintTest();
		*/
		//test
		int n=100;
		System.out.println("数据规模："+n);
		long starttime,endtime;
		g=new MTGraph();
		g.createSparseGraph(n);
		starttime=System.nanoTime();
		g.dijkstra(null);
		endtime=System.nanoTime();
		System.out.println("邻接矩阵运行时间：" + (endtime - starttime) + "ns");
		
		g1=new AdjGraph();
		g1.createSparceGraph(n);
		starttime=System.nanoTime();
		g1.dijkstra(null);
		endtime=System.nanoTime();
		System.out.println("邻接表运行时间：" + (endtime - starttime) + "ns");
		
		
		

	}

}
