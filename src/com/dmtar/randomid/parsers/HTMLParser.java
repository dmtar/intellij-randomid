package com.dmtar.randomid.parsers;

import com.dmtar.randomid.generators.HTMLGenerator;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.List;

/**
 *
 */
public class HTMLParser {

    /**
     *
     */
    private List<HTMLGenerator> generators;

    /**
     * @param generators
     */
    public HTMLParser(List<HTMLGenerator> generators) {
        this.generators = generators;
    }

    /**
     * @param bodyHtml
     * @return
     */
    public String parse(String bodyHtml) {
        String result = bodyHtml;
        //Currently not check for valid HTML. The idea is to be used with different template engines
        Document doc = Jsoup.parse(bodyHtml);
        doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
        Elements bodyElements = doc.getElementsByTag("body");
        Element bodyElement = bodyElements.first();
        this.applyGenerators(bodyElement);
        String resultHtml = StringEscapeUtils.unescapeHtml(bodyElement.html());
        return resultHtml;
    }

    /**
     * @param node
     */
    private void applyGenerators(Node node) {
        for (HTMLGenerator generator : generators) {
            generator.generate(node);
        }
    }
}
