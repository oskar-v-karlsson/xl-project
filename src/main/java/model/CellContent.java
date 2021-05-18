package model;

import expr.Environment;
import expr.Expr;
import expr.ExprResult;

public interface CellContent {

    public String getContent(Environment env);

    public ExprResult value(Environment env);

    public String getEditorText();
}