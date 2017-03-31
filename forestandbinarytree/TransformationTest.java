package forestandbinarytree;

import java.io.IOException;
import java.util.ArrayList;

public class TransformationTest {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		
		ArrayList<Tree> forest=Tree.createTree();
		BinaryTree binarytree=Tree.forestToBinary(forest);
		BinaryTree.PrintBtree(binarytree.getHead(), 0);
		ArrayList<Tree> generateforest=binarytree.binaryToForest();
		for(int i=0;i<generateforest.size();i++)
		{
			System.out.println("tree "+(i+1)+":");
			generateforest.get(i).printTree();
		}

	}

}
