package biconnected;

import java.io.IOException;

public class BiconnectedTest {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		
		AdjGraph g=new AdjGraph();
		g.createAdjGraph();
		g.findArtis();
		g.printArtis();

	}

}
