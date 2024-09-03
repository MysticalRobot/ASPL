import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplyNodeTest {

    @Test
    void eval() {
        assertEquals(new NumberNode(120.0),
                new ProgramNode(
                new DefNode("!",
                        new FuncNode(
                                List.of("n"),
                                new IfNode(
                                        new PrimNode("eq?",
                                                new VarNode("n"),
                                                new NumberNode(0.0)),
                                        new NumberNode(1.0),
                                        new PrimNode("*",
                                                new VarNode("n"),
                                                new ApplyNode(
                                                        new VarNode("!"),
                                                        new PrimNode("-",
                                                                new VarNode("n"),
                                                                new NumberNode(1))))))),
                new ApplyNode(new VarNode("!"), new NumberNode(5.0))).eval(new Environment()));
        assertEquals(new NumberNode(42.0),
                new ProgramNode(
                new DefNode("add1",
                        new FuncNode(
                                List.of("n"),
                                new PrimNode("+",
                                        new VarNode("n"),
                                        new NumberNode(1.0)))),
                new ApplyNode(new VarNode("add1"), new NumberNode(41.0))).eval(new Environment()));

    }

    @Test
    void testToString() {
        assertEquals("(add1 41.0)",
                new ApplyNode(new VarNode("add1"), new NumberNode(41.0)).toString());
    }
}