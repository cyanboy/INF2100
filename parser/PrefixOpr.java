package parser;

import main.Main;
import scanner.Scanner;

/**
 * Created by cyanboy on 20/10/15.
 */
public class PrefixOpr extends Operator {
    public PrefixOpr(int n) {
        super(n);
    }

    @Override
    void check(Block curScope, Library lib) {

    }

    static PrefixOpr parse(Scanner s) {
        enterParser("prefix opr");
        PrefixOpr p = new PrefixOpr(s.curLineNum());

        if (s.curToken.kind.isPrefixOpr()) {
            p.op = s.curToken.kind;
            s.readNextToken();
        } else {
            Main.error(s.curLineNum() + ": expected a PrefixOpr, got " + s.curToken.toString());
        }

        leaveParser("prefix opr");
        return p;
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(op.toString());
    }
}
