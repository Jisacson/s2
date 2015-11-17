/**
 * Created by obamatteus on 2015-11-16.
 */

enum Type {
    Command, Dot, Number, Color
}

public class Token {
    private Type type;
    private String parameter;

    public Token(Type type){
        this.type = type;
    }

    public Token(Type type, String parameter){
        this.type = type;
        this.parameter = parameter;
    }

    public Type getType(){return type;}

    public String getParam(){return parameter;}
}
