import java.util.Map;

class DefNode extends AstNode {

  final private String ID;

  /**
   * Constructor that takes in the definition name
   * and its body
   * @param id String that represents the definition name
   * @param arg AstNode def. body
   */
  DefNode(String id, AstNode arg) {
    super(arg);
    this.ID = id;
  }

  /**
   * Evaluates the body of the definition and places
   * it in the root environment
   * @param env to evaluate definition in
   * @return this DefNode
   */
  @Override
  AstNode eval(Environment env) {
    String id = this.ID;
    AstNode arg = this.getChildren().get(0);

    AstNode newArg = arg.eval(env);
    Map<String, AstNode> currentBindings = env.getENV();
    currentBindings.put(id, newArg);
    env.setENV(currentBindings);
    return this;
  }

  /**
   * Creates a string representation of a DefNode
   * @return String representation of the definition in the program
   */
  @Override
  public String toString() {
    AstNode args = this.getChildren().get(0);

    if (args instanceof FuncNode) {
      return "(def (" + this.ID + args.toString();
    }
    else return "(def " + this.ID + " " + args.toString() + ")";
  }

  /**
   * Allows for the comparison of two DefNodes
   * @param o Object to compared to
   * @return true if definition name and body of both DefNodes are equal
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof DefNode)) {
      return false;
    }
    else {
      DefNode othDN = (DefNode) o;
      return this.ID.equals(othDN.ID)
              && this.getChildren().get(0).equals(othDN.getChildren().get(0));
    }
  }
}