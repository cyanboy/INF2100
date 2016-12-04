package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 21/10/15.
 */
public class TermOpr extends Operator {
    public TermOpr(int n) {
        super(n);
    }

    @Override
    void check(Block curScope, Library lib) {

    }

    @Override
    public void genCode(CodeFile f) {
        f.genInstr("", "movl", "%eax, %ecx", "");
        f.genInstr("", "popl", "%eax", "");

        if (op == TokenKind.addToken) {
            f.genInstr("", "addl", "%ecx, %eax", "");
        } else if (op == TokenKind.subtractToken) {
            f.genInstr("", "subl", "%ecx, %eax", "");
        } else { // OR
            f.genInstr("", "orl", "%ecx, %eax", "");
        }
    }

    static TermOpr parse(Scanner s) {
        enterParser("term opr");
        TermOpr t = new TermOpr(s.curLineNum());

        if (s.curToken.kind.isTermOpr()) {
            t.op = s.curToken.kind;
            s.readNextToken();
        }

        leaveParser("term opr");
        return t;
    }

    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(" " + op.toString() + " ");
    }
}
