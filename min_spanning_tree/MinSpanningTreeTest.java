package min_spanning_tree;

import java.io.IOException;

public class MinSpanningTreeTest {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		
		AdjGraph g=new AdjGraph();
		g.createAdjGraph();
		long startTime = System.nanoTime(); 
		double sumweight=g.prim();
		long endTime = System.nanoTime();
		System.out.println("Prim����ʱ�䣺" + (endTime - startTime) + "ns");    //�����������ʱ��
		g.printMinTree();
		System.out.println();
		System.out.println("��С������ȨֵΪ��"+sumweight);
		
		g.initTreeEdge();
		
		startTime = System.nanoTime(); 
		sumweight=g.kruskal();
		endTime = System.nanoTime(); 
		System.out.println("Kruskal����ʱ�䣺" + (endTime - startTime) + "ns");
		g.printMinTree();
		System.out.println();
		System.out.println("��С������ȨֵΪ��"+sumweight);
		
		int n=100;
		System.out.println("���ݹ�ģ��"+n);
		AdjGraph testg=new AdjGraph();
		testg.createSparseGraph(n);
		startTime = System.nanoTime(); 
		sumweight=testg.prim();
		endTime = System.nanoTime();
		System.out.println("Prim����ʱ�䣺" + (endTime - startTime) + "ns");
		System.out.println("��С������ȨֵΪ��"+sumweight);
		testg.initTreeEdge();
		startTime = System.nanoTime(); 
		sumweight=testg.kruskal();
		endTime = System.nanoTime(); 
		System.out.println("Kruskal����ʱ�䣺" + (endTime - startTime) + "ns");
		System.out.println("��С������ȨֵΪ��"+sumweight);
		
		System.out.println("���ݹ�ģ��"+n);
		testg=new AdjGraph();
		testg.createDenseGraph(n);
		startTime = System.nanoTime(); 
		sumweight=testg.prim();
		endTime = System.nanoTime();
		System.out.println("Prim����ʱ�䣺" + (endTime - startTime) + "ns");
		System.out.println("��С������ȨֵΪ��"+sumweight);
		testg.initTreeEdge();
		startTime = System.nanoTime(); 
		sumweight=testg.kruskal();
		endTime = System.nanoTime(); 
		System.out.println("Kruskal����ʱ�䣺" + (endTime - startTime) + "ns");
		System.out.println("��С������ȨֵΪ��"+sumweight);

	}

}
