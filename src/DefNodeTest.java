import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefNodeTest {

    @Test
    void eval() {
        assertEquals(new DefNode("n", new PrimNode("+", new NumberNode(10.0), new NumberNode(11.0))),
                new DefNode("n", new PrimNode("+", new NumberNode(10.0), new NumberNode(11.0))).eval(new Environment()));
        assertEquals(new NumberNode(42.0),
                new ProgramNode(new DefNode("n", new NumberNode(10.0)),
                        new PrimNode("+", new NumberNode(32.0), new VarNode("n"))).eval(new Environment()));
    }

    @Test
    void testToString() {
        assertEquals("(def n (+ 10.0 11.0))",
                new DefNode("n", new PrimNode("+", new NumberNode(10.0), new NumberNode(11.0))).toString());
        assertEquals("(def (add1 n) (+ 1.0 n))",
                new DefNode("add1", new FuncNode(List.of("n"), new PrimNode("+", new NumberNode(1.0), new VarNode("n")))).toString());
        assertEquals("(def (f) 3.0)",
                new DefNode("f", new FuncNode(List.of(), new NumberNode(3.0))).toString());
    }
}