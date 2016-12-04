package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class RelOpr extends Operator {
    public RelOpr(int lNum) {
        super(lNum);
    }

    @Override
    void check(Block curScope, Library lib) {

    }

    @Override
    public void genCode(CodeFile f) {
        // popl %ecx
        // cmpl %eax,%ecx
        // movl $0,%eax

        f.genInstr("", "popl", "%ecx", "");
        f.genInstr("", "cmpl", "%eax, %ecx", "");
        f.genInstr("", "movl", "$0, %eax", "");

        switch (kind) {
            case equalToken:
                f.genInstr("", "sete", "%al", "");
                break;
            case notEqualToken:
                f.genInstr("", "setne", "%al", "");
                break;
            case lessToken:
                f.genInstr("", "setl", "%al", "");
                break;
            case lessEqualToken:
                f.genInstr("", "setle", "%al", "");
                break;
            case greaterToken:
                f.genInstr("", "setg", "%al", "");
                break;
            case greaterEqualToken:
                f.genInstr("", "setge", "%al", "");
                break;
            default:
                this.error("OH NO, this should not happen :(");
                break;
        }
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
