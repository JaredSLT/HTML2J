package tech.tresearchgroup.html2j;

import org.jsoup.nodes.*;

import static java.util.stream.Collectors.joining;
import static org.jsoup.select.NodeFilter.FilterResult.CONTINUE;

class HTMLNodeFilter implements org.jsoup.select.NodeFilter {
    private final StringBuilder buffer;

    private int previousDepth = -1;

    HTMLNodeFilter(final StringBuilder buffer) {
        this.buffer = buffer;
    }

    @Override
    public FilterResult head(final Node node, final int depth) {
        if (!(node instanceof TextNode || node instanceof Element)) {
            return CONTINUE;
        }
        if (node instanceof TextNode && ((TextNode) node).text().trim().isEmpty()) {
            return CONTINUE;
        }
        if (depth <= previousDepth) {
            buffer.append(",");
        }
        previousDepth = depth;
        if (node instanceof Element) {
            return head((Element) node);
        } else {
            return head((TextNode) node);
        }
    }

    @Override
    public FilterResult tail(final Node node, final int depth) {
        System.out.println("Tail: " + node.nodeName());
        if (node instanceof Element) {
            return tail((Element) node);
        }
        return CONTINUE;
    }

    private FilterResult head(TextNode textNode) {
        System.out.println("Head text: " + textNode.text());
        buffer.append("text(\"")
            .append(textNode.text())
            .append("\")");
        return CONTINUE;
    }

    private FilterResult head(Element element) {
        System.out.println("Head name: " + element.tagName());
        buffer.append(element.tagName()).append("(");
        return CONTINUE;
    }

    private FilterResult tail(Element element) {
        System.out.println("Tail: " + element.attributes());
        buffer.append(")").append(handle(element.attributes()));
        return CONTINUE;
    }

    private String handle(final Attributes attributes) {
        return attributes.asList().stream().map(this::handle).collect(joining());
    }

    private String handle(final Attribute attribute) {
        return ".attr(\"" + attribute.getKey() + "\", \"" + attribute.getValue() + "\")";
    }
}
