import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClosureNodeTest {

    @Test
    void eval() {
        assertEquals(new ClosureNode(
                new FuncNode(
                        List.of("n"),
                                new PrimNode("+",
                                new VarNode("n"),
                                new NumberNode(1.0))), new Environment()),
                        new ClosureNode(
                                new FuncNode(
                                        List.of("n"),
                                                new PrimNode("+",
                                                        new VarNode("n"),
                                                        new NumberNode(1.0))), new Environment()).eval(new Environment()));
        assertEquals(new NumberNode(10.0),
                new ProgramNode(
                        new DefNode("f",
                                new LetNode("x",
                                        new NumberNode(10.0),
                                        new FuncNode(Collections.emptyList(), new VarNode("x")))),
                        new LetNode("x",
                                new NumberNode(3.0), new ApplyNode(new VarNode("f")))).eval(new Environment()));
    }

    @Test
    void testToString() {
        assertEquals(" n) (+ n 1.0))",
                new ClosureNode(
                new FuncNode(
                        List.of("n"),
                        new PrimNode("+",
                                new VarNode("n"),
                                new NumberNode(1.0))), new Environment()).toString());
        assertEquals("(def f (let ([x 10.0]) (λ () x)))" +
                        "\n(let ([x 3.0]) (f))",
                new ProgramNode(
                        new DefNode("f" ,
                                new LetNode("x", new NumberNode(10.0),
                                new FuncNode(List.of(), new VarNode("x")))),
                new LetNode("x",
                        new NumberNode(3.0), new ApplyNode(new VarNode("f")))).toString());
    }
}