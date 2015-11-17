import sun.tools.java.SyntaxError;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jonas Isacson, Jisacson@kth.se 
 * & Matteus Hast, Mhast@kth.se on 2015-11-16.
 */
public class LexAnalyzer {
    private String input;
    private List<Token> tokens;
    private int current;

    public LexAnalyzer(InputStream is) throws java.io.IOException, SyntaxError {
        input = readInputToString(is);
        //removes comments from the inout
        input.replace("%.+", "");
        //the valid token patterns
        Pattern tokenPattern = Pattern.compile("DOWN|UP|\\.|\"|FORW|BACK|LEFT|RIGHT|REP|COLOR|#[A-Z0-9]{6}|[0-9]+");
        Matcher m = tokenPattern.matcher(input);
        int inputPos = 0;
        int current = 0;
        tokens = new ArrayList<Token>();
        while(m.find()) {
            //if there are tokens that do not match in the beginning, throw error
            if(m.start() != inputPos) {
                throw new SyntaxError();
            }
            switch (m.group()){
                case "DOWN":
                    tokens.add(new Token(Type.DOWN));
                case "UP":
                    tokens.add(new Token(Type.UP));
                case ".":
                    tokens.add(new Token(Type.Dot));
                case "\"":
                    tokens.add(new Token(Type.CITE));
                case "FORW":
                    tokens.add(new Token(Type.FORW));
                case "BACK":
                    tokens.add(new Token(Type.BACK));
                case "LEFT":
                    tokens.add(new Token(Type.LEFT));
                case "RIGHT":
                    tokens.add(new Token(Type.RIGHT));
                case "REP":
                    tokens.add(new Token(Type.REP));
                case "COLOR":
                    tokens.add(new Token(Type.Color));
                default:
                    //Numbers
                    if(Character.isDigit(m.group().charAt(0))){
                        tokens.add(new Token(Type.Number));
                        //the actual color
                    }else tokens.add(new Token(Type.ColNUm));
                    break;
            }
        }
        //checks ig the string is empty. else something is wrong. throws error
        if (inputPos != input.length()) {
            throw new SyntaxError();
        }

    }

    private String readInputToString(InputStream is){
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
