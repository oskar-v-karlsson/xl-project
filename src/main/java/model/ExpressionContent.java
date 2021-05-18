package model;


import expr.*;
import util.XLException;

import java.io.IOException;

public class ExpressionContent implements CellContent{
    private  String string;
    private  Expr expression;

    public ExpressionContent(String s){
        string = s;
        try{
            expression = new ExprParser().build(string);
        } catch(IOException e) {
            expression = new ErrorExpr(e.getMessage());
        }
    }

    @Override
    public ExprResult value(Environment env) {
        return expression.value(env);
    }

    @Override
    public String getContent(Environment env) {
        return value(env).toString();
    }

    @Override
    public String getEditorText() {
        return string;
    }
}
