import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;



class Vertex{

    private Set<Edge> edges;
    private String name;

    public Vertex(String name)
    {
        this.name = name;
        edges = new HashSet<Edge>();    
    }

    void addEdge(Edge edge)
    {
        edges.add(edge);
    }
    String getName()
    {
        return new String(this.name);
    }
    Set<Vertex> getNeighbors()
    {
        Set<Vertex> neighbor = new HashSet<Vertex>();
        for(Edge edge : edges)
        {
            neighbor.add(edge.getDestination());
        }
        return neighbor;
    }

    Set<Edge> getEdges()
    {
        return new HashSet<Edge>(this.edges);
    }

}
class Edge 
{

    private Vertex From, To;
    private int weight;

    public Edge(Vertex From, Vertex To, int weight) {
        this.From = From;
        this.To= To;
        this.weight= weight;
    }

    Vertex getSource()
    {
        return this.From;
    }
    Vertex getDestination()
    {
        return this.To;
    }
    int getWeight()
    {
        return this.weight;
    }
    void setWeight(int K) {
    	this.weight=K;
    }


}
public class Graph {
	boolean isDirec=true;
	boolean isWeighted=true;
	private int numofEdges;
    private int numVertices;
    private boolean isitcomplete;
    private Map<Vertex, HashSet<Edge>> vertices;

   public Graph(){
    	this.isDirec=true;
    	this.isWeighted=true;
        numVertices = 0;
        numofEdges = 0;
        vertices = new HashMap<>();
    }
   public Graph(int n){
	   this.isDirec=true;
    	this.isWeighted=true;
	   this.numVertices=n;
	   for(int i = 0; i < n; i++) {
		   vertices = new HashMap<Vertex, HashSet<Edge>>();
		   
	   }
	   
    	
    }
   
   public Graph(int s , boolean isdirected) {
	   this.isDirec=isdirected;
    	this.isWeighted=true;
	   this.numVertices= s;
	   for(int i = 0; i < s; i++) {
		   vertices = new HashMap<Vertex, HashSet<Edge>>();
		   
	   }
	   
	   
   }

    void addEdge(Vertex source, Vertex destination, int distance)
    {
        if(vertices.containsKey(source))
        {
            Edge edge = new Edge(source, destination, distance);
            vertices.get(source).add(edge);
            source.addEdge(edge);
            numofEdges++;
        }
        else
        {
            System.out.println("Source vertex not found..");
        }

    }
    void addVertex(Vertex V)
    {
        if(!vertices.containsKey(V))
        {
            vertices.put(V, new HashSet<Edge>());
            ++numVertices;
        }
        else
            System.out.println("Vertex already added");
    }

    ArrayList<Vertex> getNeighbor(Vertex vertex)
    {
        return new ArrayList<Vertex>(vertex.getNeighbors());
    }
    
    int Degree(Vertex c) {
    	int degree=0;
    	for (HashSet<Edge> edge : vertices.values()) {
    		if(vertices.containsKey(c))
    			degree++;
    		
			
		}
    	
    	return degree;
    }

    // to get Weight between verices directly connected to each other
   int getDistance(Vertex source, Vertex destination)
    {
        int distance = 0;
        for(Edge edge : vertices.get(source))
        {

            if(edge.getDestination() == destination)
            {
                distance = edge.getWeight();
                break;
            }
        }
        return distance;
    }
   public Vertex get(String k) {
  	 Vertex f = null;
  	 for (Map.Entry<Vertex, HashSet<Edge>> vertices : vertices.entrySet()) {
  		 f = vertices.getKey();
  		  if(f.getName().contains(k)) {
  			     return f;
  			      
  		  }
  		}
  	 return null;
		
  	 
   }
   public boolean isitComlete() {
	   int k = numofEdges();
	   int l=numVertices();
	   int res = (l*(l-1))/2;
	   if(k!= res)
		   return false;
	   else 
		   return true;
   }
   public int numofEdges() {
	   return numofEdges;
   }

    // No of vertices in graph
    int numVertices()
    {
        return numVertices;
    }
    
    void setWeight(Vertex S, Vertex D , int value) {
        for(Edge edge : vertices.get(S))
        {

            if(edge.getDestination() == D)
            	edge.setWeight(value);
            	
    	
    }
    }
    public boolean adjecent(Vertex v,Vertex u) {
    	
        for(Edge edge : vertices.get(v))
        {

            if(edge.getDestination() == u)
            	return true;
    	
    }
       
        	return false;
  
    }
    void bfs(Vertex source)
    {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        HashSet<Vertex> visited = new HashSet<Vertex>();
        ArrayList<String> path = new ArrayList();
        queue.add(source);

        while(!queue.isEmpty())
        {
            Vertex node = queue.poll();
            visited.add(node);
            Set<Vertex> neighbor = node.getNeighbors();
            System.out.print("Node " +node.getName()+ "\t ");
            for(Vertex V : neighbor){
               System.out.print(V.getName()+" \t");
                if(!visited.contains(V))
                {
                    queue.add(V);
                    visited.add(V);
                    path.add(V.getName());
                }
            }
            System.out.println("");
        }
        System.out.println("BFS \t"+path.toString());
    }
    public boolean isEmpty() {
    	if(numVertices == 0) {
    		return true;
    	}
    	else
    		return false;
    }
    public void print() {
    	System.out.println("----------------------------------------------------");
	    System.out.println(numVertices);
	    for(Vertex vertex : vertices.keySet()) {
	    	System.out.print(vertex.getName()+" ");
	    }
	    System.out.print("\n");
	    
	    for(Vertex ver : vertices.keySet()) {
	    	for(Edge edge : vertices.get(ver)) {
	    		String C = edge.getSource().getName();
	    		String D = edge.getDestination().getName();
	    		int W = edge.getWeight();
	    		System.out.println(C + " "+D+" "+W);
	    	}
	    }
    }

    public static void main(String[] args) throws FileNotFoundException {

       /* Graph graphf = new Graph();
        System.out.println("enter the number of verticies:");
        int n = Integer.parseInt(new Scanner(System.in).nextLine());
        Graph G = new Graph(n);
        System.out.print(graphf.isEmpty());
        for (int i = 0 ; i<5;i++) {
        System.out.println("enter  verticies:");
        
        String S = new Scanner(System.in).nextLine();
        Vertex V = new Vertex(S);
        graphf.addVertex(V);
        }*/

    	
    	
    	Scanner scanner = new Scanner(new FileReader("/Users/ziyayilmaz/Desktop/sampleGraph.txt.txt"));
    	  try
    	  {
    		int n = scanner.nextInt();
    		Graph graph = new Graph();
    		for(int i = 0 ; i < n; i++) {
    		    String a=scanner.next();
    		    Vertex V = new Vertex(a);
    		    graph.addVertex(V);
    		}
    		
    	    while( scanner.hasNext() )
    	    {
    	          String from = scanner.next();
    	  	      Vertex V1 = graph.get(from);
    	  	      String to = scanner.next();
    	  	      Vertex V2 = graph.get(to);
    	  	      int weight = scanner.nextInt();
    	  	      graph.addEdge(V1, V2, weight);
    	    }
    	    System.out.println("is it Directed Graph = "+graph.isDirec);
    	    System.out.println("Number of Edges in Graph is = " +graph.numofEdges);
    	    System.out.println("Is it Copmlete Graph = "+graph.isitcomplete);
    	    Vertex a=graph.get("a");
    	    Vertex h = graph.get("h");
    	    ArrayList<Vertex> K= new ArrayList<Vertex>();
    	    K = graph.getNeighbor(a) ;
    	    if(K.indexOf(h)==-1)
    	    	 System.out.println("there is no link between a --> h");
    	    else 
    	    	System.out.print("there is link bewtween those 2 vertices");
    	    
    	    Vertex f=graph.get("a");
    	    Vertex p=graph.get("b");
    	    int x = graph.getDistance(f, p);
    	    System.out.println("the weight between a --> b is : "+x);
    	    Vertex c = graph.get("c");
    	    int degree= graph.Degree(c);
    	    System.out.println("Degree of Vertex c is =" +degree);
    	    int k = graph.numVertices;
    	    System.out.println("number of Vertices in graph is :"+k);
    	    
    	    Vertex n1 = new Vertex("k");
    	    graph.addVertex(n1);
    	    graph.addEdge(n1, f, 5);
    	    Vertex g = new Vertex("g");
    	    graph.addVertex(g);
    	    graph.addEdge(g, n1, 2);
    	    graph.setWeight(f, c, 7);
    	    
    	    graph.print();
    	    
    	    
    	  }
    	  finally
    	  {
    	    scanner.close();
    	  }
    }
}
