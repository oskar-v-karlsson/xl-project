package model;

import expr.Environment;
import expr.Expr;
import expr.ExprResult;

public class CellContent implements Environment {
    private String contents;
    private Expr expression;

    public ExprResult value(String name) {

    }
}
