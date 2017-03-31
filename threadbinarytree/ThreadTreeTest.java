package threadbinarytree;

import java.util.Scanner;

public class ThreadTreeTest {
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.print("���������������У�����#��ʾ��");
		Scanner in = new Scanner(System.in);
		String str=in.next();
		
		ThreadTree prethtree=new ThreadTree();
		prethtree.createBinaryTree(str.toCharArray());
		//ThreadTree.PrintBtree(prethtree.getHead().getLChild(), 0); 
		prethtree.createPreOrderThreadTree(prethtree.getHead().getLChild());
		prethtree.createThread();
		System.out.println("ǰ���������������������");
		System.out.print("ǰ�������");
		prethtree.prePreOrder();
		System.out.println();
		System.out.print("���������");
		prethtree.preInOrder();
		System.out.println();
		System.out.print("����������");
		prethtree.prePostOrder();
		
		System.out.println();
		ThreadTree postthtree=new ThreadTree();
		postthtree.createBinaryTree(str.toCharArray());
		postthtree.createPostOrderThreadTree(postthtree.getHead().getLChild());
		postthtree.createThread();
		System.out.println("�����������������������");
		System.out.print("ǰ�������");
		postthtree.postPreOrder();
		System.out.println();
		System.out.print("���������");
		postthtree.postInOrder();
		System.out.println();
		System.out.print("����������");
		postthtree.postPostOrder();

	}

}
