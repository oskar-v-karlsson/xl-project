package model;

import expr.Environment;
import expr.Expr;
import expr.ExprResult;

public interface CellContent {
    
    public void update(String s);

    public String getContent();

    public ExprResult value(Environment env);
}