package model;

import expr.*;

public class DisplayedContent implements CellContent{
    private String content;

    public DisplayedContent(String s){
        this.content = s;
    }

    @Override
    public String getContent(Environment env) {
        return content;
    }

    @Override
    public ExprResult value(Environment env) {
        return new ErrorResult("References comment");
    }

    @Override
    public String getEditorText() {
        return content;
    }
}

