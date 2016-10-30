package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class RelOpr extends Expression {
    public RelOpr(int lNum) {
        super(lNum);
    }

    TokenKind kind;

    static RelOpr parse(Scanner s) {
        enterParser("rel opr");
        RelOpr r = new RelOpr(s.curLineNum());

        if (s.curToken.kind.isRelOpr()) {
            r.kind = s.curToken.kind;
            s.readNextToken();
        } else {
            Main.error(s.curLineNum() + ": Expected a RelOpr, found " + s.curToken.kind.toString());
        }

        leaveParser("rel opr");
        return r;
    }


    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(" " + kind.toString() + " ");
    }
}
