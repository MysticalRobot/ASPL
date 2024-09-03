import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Environment {

  private Map<String, AstNode> ENV;

  final private Environment PARENT;

  Environment() {
    this(null);
  }

  Environment(Environment parent) {
    this.ENV = new HashMap<>();
    this.PARENT = parent;
  }

  public Map<String, AstNode> getENV() {
    return this.ENV;
  }

  public Environment getPARENT() {
    return PARENT;
  }

  public void setENV(Map<String, AstNode> ENV) {
    this.ENV = ENV;
  }

  /**
   * Looks up a variable in the current environment.
   * @param id - the variable name.
   * @return the value bound to the variable, or null if it does not exist.
   */
  AstNode lookup(String id) {
    if (this.ENV.containsKey(id)) {
      return this.ENV.get(id);
    } else if (this.PARENT != null) {
      return this.PARENT.lookup(id);
    } else {
      return null;
    }
  }

  /**
   * Extends the current environment to contain a new variable binding.
   * @param id    - the variable name.
   * @param value - the value to bind to the variable.
   * @return a new environment with the new binding.
   */
  Environment extend(String id, AstNode value) {
    Environment env = new Environment(this);
    env.ENV.put(id, value);
    return env;
  }

  /**
   * Extends the current environment to contain a new variable binding for each of the function arguments.
   * @param identifiers - the list of variable names
   * @param evaluatedArgs - the values to bind to the variable
   * @returna new environment with the new bindings.
   */
  Environment extend(List<String> identifiers, List<AstNode> evaluatedArgs) {
    Environment env = new Environment(this);

    for (int i = 0; i < identifiers.size(); i++) {
      env.ENV.put(identifiers.get(i), evaluatedArgs.get(i));
    }
    return env;
  }

  /**
   * Allows for the comparison of two Environments
   * @param o Object to be compared to
   * @return true if environments contain the same bindings
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Environment)) {
      return false;
    }
    else {
      Environment othENV = (Environment) o;
      if (this.getPARENT() == null && othENV.getPARENT() == null)
        return this.ENV.equals(othENV.getENV());
      else if (this.getPARENT() == null || othENV.getPARENT() == null)
        return false;
      else
        return this.ENV.equals(othENV.getENV()) && this.getPARENT().equals(othENV.getPARENT());
    }
  }
}