package parser;

import scanner.Scanner;
import scanner.TokenKind;
import types.*;


/**
 * Created by cyanboy on 16/10/2016.
 */
public class Constant extends PascalSyntax {

    PrefixOpr prefixOpr;
    UnsignedConstant uc;
    types.Type type;

    int constval;

    Constant(int lNum) {
        super(lNum);
    }

    @Override
    void check(Block curScope, Library lib) {
        if (prefixOpr != null) {
            prefixOpr.check(curScope, lib);
        }

        uc.check(curScope, lib);
        type = uc.type;

        if (uc instanceof NumericLiteral) {
            constval = ((NumericLiteral) uc).val;

            if (prefixOpr != null && prefixOpr.op == TokenKind.subtractToken) {
                constval = -constval;
            }

        } else if (uc instanceof CharLiteral) {
            constval = (int) ((CharLiteral) uc).constant;
        } else {
            ConstDecl n = (ConstDecl) ((Name) uc).decl;

            while(true) {

                if (n.constant.uc instanceof Name) {
                    n = (ConstDecl) ((Name) n.constant.uc).decl;
                } else if (n.constant.uc instanceof CharLiteral) {
                    constval = (int) ((CharLiteral) n.constant.uc).constant;
                    break;
                } else if (n.constant.uc instanceof NumericLiteral) {
                    constval = ((NumericLiteral) n.constant.uc).val;
                    break;
                }
            }
        }

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
