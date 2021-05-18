package model;

import expr.Environment;
import expr.ExprResult;

public interface CellContent {

    public ExprResult value(Environment env);

    public String getContent(Environment env);

    public String getEditorText();
}