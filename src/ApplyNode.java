import java.util.List;
import java.util.ArrayList;

final class ApplyNode extends AstNode {

  /**
   * Constructor takes a function identifier and the arguments to that function
   * @param fn AstNode (VarNode) function identifier
   * @param args AstNode[] function arguments
   */
  ApplyNode(AstNode fn, AstNode... args) {
    super(initializeAN(fn, args));
  }

  /**
   * Helper method for super constructor: adds function identifier to the end
   * of the list containing the function arguments
   * @param fn AstNode (VarNode) function identifier
   * @param args AstNode[] function arguments
   * @return List<AstNode> of function arguments and identifier
   */
  private static List<AstNode> initializeAN(AstNode fn, AstNode... args) {
    List<AstNode> AN_Children = new ArrayList<>();

    for (int i = 0; i < args.length; i++) {
      AN_Children.add(args[i]);
    }
    AN_Children.add(fn);

    return AN_Children;
  }

  /**
   * Evaluates each function argument, extends the environment to contain
   * the function definition, and applies the function to the evaluated arguments
   * @param env (current) Environment
   * @return AstNode result of applying the function in the given Environment
   * to the given arguments
   */
  @Override
  AstNode eval(Environment env) {
    List<AstNode> evaluatedArgs = new ArrayList<>();
    for (int i = 0; i < this.getChildren().size()-1; i++) {
      evaluatedArgs.add(this.getChildren().get(i).eval(env));
    }

    VarNode fnName = (VarNode) this.getChildren().get(this.getChildren().size()-1);
    ClosureNode cn = (ClosureNode) fnName.eval(env);
    FuncNode fn = (FuncNode) cn.getChildren().get(0);

    env = cn.getENV().extend(fn.getIdentifiers(), evaluatedArgs);

    return fn.getChildren().get(0).eval(env);
  }

  /**
   * Creates string representation of ApplyNode
   * @return String representation of function call
   */
  @Override
  public String toString() {
    String applyString = "(" + this.getChildren().get(this.getChildren().size()-1).toString();

    for (int i = 0; i < this.getChildren().size()-1; i++) {
      applyString += " " + this.getChildren().get(i).toString();
    }
    return applyString + ")";
  }

  /**
   * Allows for the comparison of two ApplyNodes
   * @param o Object to be compared to
   * @return true if the result of two function calls are the same
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AstNode)) {
      return false;
    }
    else {
      return this.eval(new Environment()).equals(o);
    }
  }
}