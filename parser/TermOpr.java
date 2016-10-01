package parser;

import scanner.Scanner;

/**
 * Created by cyanboy on 21/10/15.
 */
public class TermOpr extends Operator {
    public TermOpr(int n) {
        super(n);
    }

    static TermOpr parse(Scanner s) {
        enterParser("term-opr");
        TermOpr t = new TermOpr(s.curLineNum());

        if (s.curToken.kind.isTermOpr()) {
            t.op = s.curToken.kind;
            s.readNextToken();
        }

        leaveParser("term-opr");
        return t;
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
