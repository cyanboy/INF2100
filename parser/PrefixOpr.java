package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

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

    @Override
    public void genCode(CodeFile f) {
        if (op == TokenKind.subtractToken)
            f.genInstr("", "negl", "%eax", "");
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
