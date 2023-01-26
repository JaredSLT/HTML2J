package tech.tresearchgroup.html2j;

import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;

import java.util.List;

public class J2HTMLGenerator {
  public String generate(String data) {
    StringBuilder buffer = new StringBuilder();
    parse(data).forEach(node -> NodeTraversor.filter(new HTMLNodeFilter(buffer), node));
    return buffer.toString();
  }

  private List<Node> parse(String html) {
    return Parser.parseFragment(html, null, "");
  }
}
