package threadbinarytree;

import java.util.Scanner;

public class ThreadTreeTest {
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.print("请输入层序遍历序列，空以#表示：");
		Scanner in = new Scanner(System.in);
		String str=in.next();
		
		ThreadTree prethtree=new ThreadTree();
		prethtree.createBinaryTree(str.toCharArray());
		//ThreadTree.PrintBtree(prethtree.getHead().getLChild(), 0); 
		prethtree.createPreOrderThreadTree(prethtree.getHead().getLChild());
		prethtree.createThread();
		System.out.println("前序线索二叉树遍历结果：");
		System.out.print("前序遍历：");
		prethtree.prePreOrder();
		System.out.println();
		System.out.print("中序遍历：");
		prethtree.preInOrder();
		System.out.println();
		System.out.print("后续遍历：");
		prethtree.prePostOrder();
		
		System.out.println();
		ThreadTree postthtree=new ThreadTree();
		postthtree.createBinaryTree(str.toCharArray());
		postthtree.createPostOrderThreadTree(postthtree.getHead().getLChild());
		postthtree.createThread();
		System.out.println("后序线索二叉树遍历结果：");
		System.out.print("前序遍历：");
		postthtree.postPreOrder();
		System.out.println();
		System.out.print("中序遍历：");
		postthtree.postInOrder();
		System.out.println();
		System.out.print("后续遍历：");
		postthtree.postPostOrder();

	}

}
