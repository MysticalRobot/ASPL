import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FuncNodeTest {

    @Test
    void eval() {
        Environment parent = new Environment();
        Map<String, AstNode> binding = new HashMap<>();
        binding.put("n", new NumberNode(1.0));
        parent.setENV(binding);

        assertNotEquals(new DefNode("joshua", new FuncNode(List.of("n"),
                        new PrimNode("+", new VarNode("n"), new NumberNode(41.0)))),
                new DefNode("joshua",
                        new FuncNode(List.of("n"),
                        new PrimNode("+", new VarNode("n"), new NumberNode(41.0))).eval(new Environment(parent))));
    }

    @Test
    void testToString() {
        assertEquals(" n) (+ 1.0 n))",
                new FuncNode(List.of("n"), new PrimNode("+", new NumberNode(1.0), new VarNode("n"))).toString());
        assertEquals(") (+ 1.0 n))",
                new FuncNode(List.of(), new PrimNode("+", new NumberNode(1.0), new VarNode("n"))).toString());
    }
}