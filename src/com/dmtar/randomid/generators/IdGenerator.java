package com.dmtar.randomid.generators;

import com.dmtar.randomid.handlers.ElementIdGeneratorHandler;
import org.jsoup.nodes.Node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class IdGenerator implements HTMLGenerator {

    private static final Logger LOGGER = Logger.getLogger(IdGenerator.class.getName());

    private List<Method> methodsToApply;

    public IdGenerator() {
        this.methodsToApply = new ArrayList<>();
        this.init();
    }

    private void init() {
        try {
            Method uniqueIdGenerator = IdGenerator.class.getDeclaredMethod("generateUniqueRandomString", Node.class);
            this.methodsToApply.add(uniqueIdGenerator);
        } catch (NoSuchMethodException e) {
            LOGGER.log(Level.SEVERE, "ERROR OCCURRED", e);
        }
    }

    /**
     *
     */
    @Override
    public void generate(Node node) {
        Queue queue = new LinkedList<Node>();
        queue.add(node);
        this.bfs(queue);
    }

    private void bfs(Queue<Node> queue) {
        try {
            if (queue.isEmpty()) {
                return;
            }
            Node node = queue.poll();

            for (Method method : this.methodsToApply) {
                method.invoke(this, node);
            }

            if (node.childNodeSize() > 0) {
                for (Node child : node.childNodes()) {
                    queue.add(child);
                }
            }
            this.bfs(queue);
        } catch(IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "ERROR OCCURRED", e);
        }
    }

    /**
     * @return
     */
    private void generateUniqueRandomString(Node node) {
        if (!node.hasAttr("id")) {
            String uniqueId = UUID.randomUUID().toString();
            node.attr("id", uniqueId);
        }
    }
}
