class ConsNode extends AstNode {

    /**
     * Empty ConsList constructor
     */
    ConsNode() {
        super();
    }

    /**
     * Non-empty ConsList constructor
     * @param first value of type X
     * @param rest of ConsList
     */
    ConsNode(AstNode first, ConsNode rest) {
        super(first, rest);
    }

    /**
     * Evaluates all the elements in the ConsList
     * @param env to evaluate all the elements in
     * @return evaluated ConsList
     */
    @Override
    AstNode eval(Environment env) {
        if (this.getChildren().size() == 0) {
            return this;
        }
        else {
            return new ConsNode(this.getChildren().get(0).eval(env),
                    (ConsNode) this.getChildren().get(1).eval(env));
        }
    }

    /**
     * Creates a string representation of the ConsList
     * @return String holding comma separated values enclosed in brackets
     */
    @Override
    public String toString() {
        return "[" + this.toStringHelper();
    }

    /**
     * Helper method for toString
     * @return String representation of ConsList without starting bracket
     */
    private String toStringHelper() {
        if (this.getChildren().size() == 0) {
            return "]";
        }
        else if (this.getChildren().get(1).getChildren().size() == 0) {
            return this.getChildren().get(0).toString() + "]";
        }
        else {
            return this.getChildren().get(0).toString() + ", " +
                    ((ConsNode) this.getChildren().get(1)).toStringHelper();
        }
    }

    /**
     * Allows for the comparison of two ConsLists
     * checks if each value in each ConsList (if o is a ConsNode) is equal
     * @param o Object to be compared to
     * @return true if values are the same or both are empty, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ConsNode)) {
            return false;
        }
        else {
            ConsNode othCN = (ConsNode) o;

            if (this.getChildren().size() == 0 && othCN.getChildren().size() == 0) {
                return true;
            }
            else if (this.getChildren().size() == 0 || othCN.getChildren().size() == 0) {
                return false;
            }
            else {
                return this.getChildren().get(0).equals(othCN.getChildren().get(0)) &&
                        ((ConsNode) this.getChildren().get(1)).equals(othCN.getChildren().get(1));
            }
        }
    }
}