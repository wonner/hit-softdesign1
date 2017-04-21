package min_spanning_tree;

import java.io.IOException;

public class MinSpanningTreeTest {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		
		AdjGraph g=new AdjGraph();
		g.createAdjGraph();
		long startTime = System.nanoTime(); 
		double sumweight=g.prim();
		long endTime = System.nanoTime();
		System.out.println("Prim运行时间：" + (endTime - startTime) + "ns");    //输出程序运行时间
		g.printMinTree();
		System.out.println();
		System.out.println("最小生成树权值为："+sumweight);
		
		g.initTreeEdge();
		
		startTime = System.nanoTime(); 
		sumweight=g.kruskal();
		endTime = System.nanoTime(); 
		System.out.println("Kruskal运行时间：" + (endTime - startTime) + "ns");
		g.printMinTree();
		System.out.println();
		System.out.println("最小生成树权值为："+sumweight);
		
		int n=100;
		System.out.println("数据规模："+n);
		AdjGraph testg=new AdjGraph();
		testg.createSparseGraph(n);
		startTime = System.nanoTime(); 
		sumweight=testg.prim();
		endTime = System.nanoTime();
		System.out.println("Prim运行时间：" + (endTime - startTime) + "ns");
		System.out.println("最小生成树权值为："+sumweight);
		testg.initTreeEdge();
		startTime = System.nanoTime(); 
		sumweight=testg.kruskal();
		endTime = System.nanoTime(); 
		System.out.println("Kruskal运行时间：" + (endTime - startTime) + "ns");
		System.out.println("最小生成树权值为："+sumweight);
		
		System.out.println("数据规模："+n);
		testg=new AdjGraph();
		testg.createDenseGraph(n);
		startTime = System.nanoTime(); 
		sumweight=testg.prim();
		endTime = System.nanoTime();
		System.out.println("Prim运行时间：" + (endTime - startTime) + "ns");
		System.out.println("最小生成树权值为："+sumweight);
		testg.initTreeEdge();
		startTime = System.nanoTime(); 
		sumweight=testg.kruskal();
		endTime = System.nanoTime(); 
		System.out.println("Kruskal运行时间：" + (endTime - startTime) + "ns");
		System.out.println("最小生成树权值为："+sumweight);

	}

}
