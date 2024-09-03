import java.util.List;

final class PrimNode extends AstNode {

  private final String OP;

  PrimNode(String op, AstNode... children) {
    super(children);
    this.OP = op;
  }

  /**
   * Interpret a primitive operation.
   * @param env - the environment in which to interpret the operation.
   * @return The result of the primitive operation.
   */
  @Override
  AstNode eval(Environment env) {
    String op = this.OP;
    List<AstNode> operands = this.getChildren().stream()
            .map(n -> n.eval(env))
            .toList();
    switch (op) {
      case "+":
        return this.primPlus(operands, env);
      case "-":
        return this.primMinus(operands, env);
      case "*":
        return this.primProduct(operands, env);
      case "eq?":
        return this.primEq(operands, env);
      case "first":
        return operands.get(0).getChildren().get(0);
      case "rest":
        return operands.get(0).getChildren().get(1);
      case "empty?":
        return new BoolNode(operands.get(0).getChildren().isEmpty());
      default:
        return null;
    }
  }

  @Override
  public String toString() {
    String args = "";

    for (AstNode c : this.getChildren()) {
      args += " " + c.toString();
    }

    return String.format("(%s%s)", this.OP, args);
  }

  private AstNode primPlus(List<AstNode> args, Environment env) {
    return new NumberNode(args.stream()
            .map(t -> ((NumberNode) t).getValue())
            .reduce(0.0, Double::sum));
  }

  private AstNode primMinus(List<AstNode> args, Environment env) {
    double res = ((NumberNode) args.get(0)).getValue();
    for (int i = 1; i < args.size(); i++) {
      res -= ((NumberNode) args.get(i)).getValue();
    }
    return new NumberNode(res);
  }

  private AstNode primProduct(List<AstNode> args, Environment env) {
    return new NumberNode(args.stream()
            .map(t -> ((NumberNode) t).getValue())
            .reduce(1.0, (a, c) -> c * a));
  }

  private AstNode primEq(List<AstNode> args, Environment env) {
    return new BoolNode(args.get(0).equals(args.get(1)));
  }

  @Override
  public boolean equals(Object o){
    if (!(o instanceof PrimNode)){
      return false;
    }
    else {
      PrimNode othPN = (PrimNode) o;
      return this.eval(new Environment()).equals(othPN.eval(new Environment()));
    }
  }
}