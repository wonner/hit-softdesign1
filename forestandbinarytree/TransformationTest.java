package forestandbinarytree;

import java.io.IOException;
import java.util.ArrayList;

public class TransformationTest {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		
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
