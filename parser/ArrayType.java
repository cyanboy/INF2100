package parser;

import main.Main;
import scanner.Scanner;
import scanner.Token;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class ArrayType extends Type {
    public ArrayType(int n) {
        super(n);
    }

    Type type;
    Constant a, b;

    static ArrayType parse(Scanner s) {
        enterParser("array-type");
        ArrayType at = new ArrayType(s.curLineNum());

        s.skip(TokenKind.arrayToken);
        s.skip(TokenKind.leftBracketToken);

        at.a = Constant.parse(s);

        s.skip(TokenKind.rangeToken);

        at.b = Constant.parse(s);


        s.skip(TokenKind.rightBracketToken);
        s.skip(TokenKind.ofToken);
        at.type = Type.parse(s);

        leaveParser("array-type");
        return at;
    }

    @Override
    void check(Block curScope, Library library) {

    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("array[");
        a.prettyPrint();
        Main.log.prettyPrint("..");
        b.prettyPrint();
        Main.log.prettyPrint("] of");
        type.prettyPrint();
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
