package tech.tresearchgroup.html2j;

import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;

import java.util.List;

public class HTMLGenerator {
  public String generate(String html) {
    StringBuilder buffer = new StringBuilder();
    parse(html).forEach(node -> NodeTraversor.filter(new HTMLNodeFilter(buffer), node));
    return buffer.toString();
  }

  private List<Node> parse(String html) {
    return Parser.parseFragment(html, null, "");
  }
}
