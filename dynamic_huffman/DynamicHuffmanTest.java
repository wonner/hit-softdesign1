package dynamic_huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import forestandbinarytree.Node;

public class DynamicHuffmanTest {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		HuffmanTree tree=new HuffmanTree();
		tree.coding();
		System.out.println("success");
		HuffmanTree nativetree=new HuffmanTree();
		nativetree.decoding();

	}

}
