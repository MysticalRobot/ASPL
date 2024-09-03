class ClosureNode extends AstNode{

    final private Environment ENV;

    /**
     * Constructor that receives a function and the
     * environment to capture
     * @param fn
     * @param env
     */
    ClosureNode(FuncNode fn, Environment env) {
        super(fn);
        this.ENV = env;
    }

    public Environment getENV() {
        return this.ENV;
    }

    /**
     * Evaluates the closure to itself
     * @param env ignored Environment
     * @return this ClosureNode
     */
    @Override
    AstNode eval(Environment env) {
        return this;
    }

    /**
     * Creates a string representation of the function inside the closure
     * @return String representation of Closure
     */
    @Override
    public String toString() {
        return this.getChildren().get(0).toString();
    }

    /**
     * Allows for the comparison of two ClosureNodes by comparing the
     * function parameters and passed environments
     * @param o Object to be compared
     * @return true if functions have the same parameters and the environments
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClosureNode)) {
            return false;
        }
        else {
            ClosureNode othCN = (ClosureNode) o;
            return this.getChildren().get(0).equals(othCN.getChildren().get(0)) && this.ENV.equals(othCN.ENV);
        }
    }
}