package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public abstract class Type extends PascalSyntax {
    public Type(int n) {
        super(n);
    }

    static Type parse(Scanner s) {
        enterParser("type");
        Type t = null;

        if((s.curToken.kind == TokenKind.nameToken ||
                s.curToken.kind == TokenKind.intValToken ||
                s.curToken.kind == TokenKind.charValToken) &&
                s.nextToken.kind == TokenKind.rangeToken){
            t = RangeType.parse(s);
        }else if(s.curToken.kind == TokenKind.nameToken){
            t = TypeName.parse(s);
        }else if(s.curToken.kind == TokenKind.leftParToken){
            t = EnumType.parse(s);
        }else if(s.curToken.kind == TokenKind.arrayToken){
            t = ArrayType.parse(s);
        }else{
            Main.error("Found a " + s.curToken.kind.toString());
        }

        leaveParser("type");
        return t;
    }

    abstract void check(Block curScope, Library library);

    @Override
    public String identify() {
        return null;
    }
}
