import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramNodeTest {

    @Test
    void eval() {
        assertEquals(new NumberNode(42.0),
                new ProgramNode(new NumberNode(42.0)).eval(new Environment()));
        assertEquals(new NumberNode(42.0),
                new ProgramNode(new PrimNode("-", new NumberNode(30.0), new NumberNode(-12.0))).eval(new Environment()));
    }

    @Test
    void testToString() {
        assertEquals(new NumberNode(42.0).toString(),
                new ProgramNode(new NumberNode(42.0)).toString());
        assertEquals("(def (sub1 n) (- n 1.0))", new ProgramNode(
                new DefNode("sub1",
                        new FuncNode(
                                List.of("n"),
                                new PrimNode("-",
                                        new VarNode("n"),
                                        new NumberNode(1.0))))).toString());
    }
}