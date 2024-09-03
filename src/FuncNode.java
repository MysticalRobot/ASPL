import java.util.List;

final class FuncNode extends AstNode {

  final private List<String> identifiers;

  /**
   * Constructor that takes in a list of variable names and a primitive expression
   * @param identifiers - List of variable name(s)
   * @param body - AstNode (primitive) expression that does something to the list of variable(s)
   */
  FuncNode(List<String> identifiers, AstNode body) {
    super(body);
    this.identifiers = identifiers;
  }

  public List<String> getIdentifiers() {
    return this.identifiers;
  }

  /**
   * Evaluates the primitive expression using the list of variable(s)
   * @param env (current) environment
   * @return ClosureNode closing off the (primitive) expression that represents the function's purpose
   */
  @Override
  AstNode eval(Environment env) {
   return new ClosureNode(this, env);
  }

  /**
   * Creates a string representation of FuncNode
   * @return String representation of the list of variables and the (primitive) expression
   */
  @Override
  public String toString() {
    String funcString = "";

    for (String i : identifiers) {
      funcString += " " + i;
    }

    return funcString + ") " + this.getChildren().get(0).toString() + ")";
  }

  /**
   * Allows for the comparison of two FuncNodes
   * @param o Object to be compared to
   * @return true if list of variables and the (primitive) expression of both FuncNodes are the same
   */
  @Override
  public boolean equals(Object o){
    if (!(o instanceof FuncNode)) {
      return false;
    }
    else {
      FuncNode othFN = (FuncNode) o;
      return this.identifiers.equals(othFN.identifiers);
    }
  }
}