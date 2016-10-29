package parser;

import main.Main;
import scanner.Scanner;

public class FactorOpr extends Operator {
    public FactorOpr(int n) {
        super(n);
    }

    static FactorOpr parse(Scanner s) {
        enterParser("factor opr");
        FactorOpr f = new FactorOpr(s.curLineNum());

        if (s.curToken.kind.isFactorOpr()) {
            f.op = s.curToken.kind;
            s.readNextToken();
        } else {
            Main.error(s.curLineNum() + ": expected a FactorOpr, got " + s.curToken.toString());
        }

        leaveParser("factor opr");
        return f;
    }

    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(this.op.toString());
    }
}