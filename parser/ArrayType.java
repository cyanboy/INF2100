package parser;

import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class ArrayType extends Type {
    public ArrayType(int n) {
        super(n);
    }

    Type type;
    Type ofType;

    static ArrayType parse(Scanner s) {
        enterParser("array-type");
        ArrayType a = new ArrayType(s.curLineNum());
        s.skip(TokenKind.arrayToken);
        s.skip(TokenKind.leftBracketToken);
        a.type = Type.parse(s);
        s.skip(TokenKind.rightBracketToken);
        s.skip(TokenKind.ofToken);
        a.ofType = Type.parse(s);

        leaveParser("array-type");
        return a;
    }

    @Override
    void check(Block curScope, Library library) {
        type.check(curScope, library);
        ofType.check(curScope, library);
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
