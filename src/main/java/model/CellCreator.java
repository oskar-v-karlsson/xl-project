package model;

public class CellCreator {
    public final static CellContent createContentType(String s){
        if(s.startsWith("#")){
            return new DisplayedContent(s);
        }else {
            return new ExpressionContent(s);
        }
    }
}
