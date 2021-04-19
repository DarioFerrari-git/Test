import org.tweetyproject.graphs.DefaultGraph;
import org.tweetyproject.graphs.DirectedEdge;
import org.tweetyproject.graphs.Graph;
import org.tweetyproject.graphs.SimpleNode;
import org.tweetyproject.graphs.util.GraphUtil;
import org.tweetyproject.math.ComplexNumber;

public class bho {
	public static void main(String[] args){
		Graph<SimpleNode> graph = new DefaultGraph<SimpleNode>();
		
		SimpleNode a = new SimpleNode("A");
		SimpleNode b = new SimpleNode("B");
		SimpleNode c = new SimpleNode("C");
		SimpleNode d = new SimpleNode("D");
		SimpleNode e = new SimpleNode("E");
		SimpleNode f = new SimpleNode("F");
		SimpleNode g = new SimpleNode("G");
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.add(e);
		graph.add(f);
		graph.add(g);
		
		graph.add(new DirectedEdge<SimpleNode>(a,b));
		graph.add(new DirectedEdge<SimpleNode>(b,c));
		graph.add(new DirectedEdge<SimpleNode>(a,d));
		graph.add(new DirectedEdge<SimpleNode>(d,a));
		graph.add(new DirectedEdge<SimpleNode>(c,d));
		graph.add(new DirectedEdge<SimpleNode>(a,c));
		graph.add(new DirectedEdge<SimpleNode>(c,e));
		graph.add(new DirectedEdge<SimpleNode>(e,f));
		graph.add(new DirectedEdge<SimpleNode>(f,g));
		graph.add(new DirectedEdge<SimpleNode>(g,f));
		graph.add(new DirectedEdge<SimpleNode>(c,g));
		
		System.out.println(graph);
		
		System.out.println(graph.getStronglyConnectedComponents());
		System.out.println();
		System.out.println(graph.getAdjacencyMatrix());
		
		for(ComplexNumber ev: GraphUtil.eigenvalues(graph))
			System.out.print(ev + " ");
	}
}