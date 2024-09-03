import java.util.ArrayList;
import java.util.List;

final class ProgramNode extends AstNode {

  /**
   * Constructor takes in each argument from the program
   * @param args AstNode[] function arguments
   */
  ProgramNode(AstNode... args) {
    super(args);
  }

  /**
   * Evaluates each argument from the program
   * @param env (current) environment
   * @return NumberNode/BoolNode that represents a number/boolean value
   * of the last expression in the program
   */
  @Override
  AstNode eval(Environment env) {
    List<AstNode> evaluatedStatements = new ArrayList<>();
    for (int i = 0; i < this.getChildren().size(); i++) {
      evaluatedStatements.add(this.getChildren().get(i).eval(env));
    }
    AstNode result = evaluatedStatements.get(evaluatedStatements.size()-1);
    return result;
  }

  /**
   * Creates a string representation of ProgramNode
   * @return String representation of the expressions in the program
   */
  @Override
  public String toString() {
    String result = this.getChildren().stream().
            map(ast -> ast.toString()).
            map(astString -> astString + "\n").
            reduce("", String::concat);
    if (result.length() == 0)
      return result;
    else
      return result.substring(0,result.length()-1);
  }

  /**
   * Allows for the comparison of two ProgramNodes
   * @param o Object to be compared to
   * @return true if the results of both programs are equal
   */
  @Override
  public boolean equals(Object o){
    if (!(o instanceof ProgramNode)){
      return false;
    }
    else {
      ProgramNode othPN = (ProgramNode) o;
      return this.eval(new Environment()).equals(othPN.eval(new Environment()));
    }
  }
}