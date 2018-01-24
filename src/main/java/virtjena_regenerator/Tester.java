package virtjena_regenerator;

import static org.apache.jena.datatypes.xsd.XSDDatatype.XSDstring;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

public class Tester {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    VirtGraph graph = new VirtGraph("testGraph", "jdbc:virtuoso://localhost:1111", "dba", "dba");
    String nameStr = "test_sensor";
    String BASE = "http://base.org#";
    String uuid = "xxxxx";
    // Trying to add name literal in different ways.
    Node name1 = NodeFactory.createLiteralByValue(nameStr, XSDstring);
    Node name2 = NodeFactory.createLiteral(nameStr);
    Node point1 = NodeFactory.createURI(BASE + uuid + "1");
    Node point2 = NodeFactory.createURI(BASE + uuid + "2");
    Node nameProp = NodeFactory.createURI(BASE + "name");
    graph.add(new Triple(point1, nameProp, name1));
    graph.add(new Triple(point2, nameProp, name2));
    // Below query works in ISQL.
    Boolean successFlag = false;
    String qStr = "PREFIX base: <http://base.org#> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>  SELECT ?s {?s base:name \"test_sensor\"^^xsd:string}";
    Query sparql = QueryFactory.create(qStr);
    VirtuosoQueryExecution vqd = VirtuosoQueryExecutionFactory.create(sparql, graph);
    ResultSet results = vqd.execSelect();
    while (results.hasNext()) {
      successFlag = true;
      QuerySolution result = results.nextSolution();
      System.out.println(result.get("s").toString());
    }
    if (successFlag) {
      System.out.println("success!");
    } else {
      System.out.println("failed!");
    }
  }

}
