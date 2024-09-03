import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsNodeTest {

    @Test
    void eval() {
        assertEquals(new ConsNode(
                new NumberNode(1.0),
                new ConsNode(
                        new NumberNode(2.0),
                        new ConsNode(
                                new NumberNode(3.0),
                                new ConsNode()))),
                new ConsNode(
                new PrimNode("-", new NumberNode(42.0), new NumberNode(41.0)),
                new ConsNode(
                        new PrimNode("+", new NumberNode(-40.0), new NumberNode(42.0)),
                        new ConsNode(
                                new PrimNode("*", new NumberNode(21.0), new NumberNode(1.0/7.0)),
                                new ConsNode()))).eval(new Environment()));
        assertEquals(new BoolNode(true),
                new PrimNode("empty?", new ConsNode()).eval(new Environment()));
        assertEquals(new BoolNode(false),
                new PrimNode("empty?",
                        new ConsNode(new NumberNode(1.0), new ConsNode())).eval(new Environment()));
        assertEquals(new NumberNode(1.0),
                new PrimNode("first",
                        new ConsNode(new NumberNode(1.0), new ConsNode())).eval(new Environment()));
        assertEquals(new ConsNode(),
                new PrimNode("rest",
                        new ConsNode(new NumberNode(1.0), new ConsNode())).eval(new Environment()));
    }

    @Test
    void testToString() {
        assertEquals("[1.0, 2.0, 3.0]",
                new ConsNode(
                new NumberNode(1.0),
                new ConsNode(
                        new NumberNode(2.0),
                        new ConsNode(
                                new NumberNode(3.0),
                                new ConsNode()))).toString());
        assertEquals("[1.0]",
                new ConsNode(new NumberNode(1.0), new ConsNode()).toString());
        assertEquals("[]",
                new ConsNode().toString());
    }
}