package parser;

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

        Type t;

        if (s.curToken.kind == TokenKind.arrayToken) {
            t = ArrayType.parse(s);
        } else {
            t = TypeName.parse(s);
        }

        leaveParser("type");
        return t;
    }

    void check(Block curScope, Library library) {

    }


}
