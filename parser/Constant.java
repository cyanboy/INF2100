package parser;

import scanner.Scanner;
import scanner.TokenKind;


/**
 * Created by cyanboy on 16/10/2016.
 */
public class Constant extends PascalSyntax {

    PrefixOpr prefixOpr;
    UnsignedConstant uc;

    Constant(int lNum) {
        super(lNum);
    }

    @Override
    void check(Block curScope, Library lib) {
    }

    @Override
    public String identify() {
        return "<constant> at line  " + lineNum;
    }

    public static Constant parse(Scanner s) {
        Constant c = new Constant(s.curLineNum());
        enterParser("constant");

        if (s.curToken.kind.isPrefixOpr()) {
            c.prefixOpr = PrefixOpr.parse(s);
        }

        c.uc = UnsignedConstant.parse(s);

        leaveParser("constant");
        return c;
    }

    @Override
    void prettyPrint() {
        if (prefixOpr != null) {
            prefixOpr.prettyPrint();
        }
        uc.prettyPrint();
    }
}
