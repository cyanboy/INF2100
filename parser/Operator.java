package parser;

import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 21/10/15.
 */
public abstract class Operator extends PascalSyntax {
    public Operator(int n) {
        super(n);
    }

    TokenKind op;

    @Override
    public String identify() {
        return null;
    }
}
