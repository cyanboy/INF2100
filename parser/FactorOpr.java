package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

public class FactorOpr extends Operator {
    public FactorOpr(int n) {
        super(n);
    }

    @Override
    void check(Block curScope, Library lib) {

    }

    @Override
    public void genCode(CodeFile f) {
        f.genInstr("", "movl", "%eax, %ecx", "");
        f.genInstr("", "popl ", "%eax", "");

        if (op == TokenKind.multiplyToken) {
            f.genInstr("", "imull", "%ecx, %eax", "");

        } else if (op == TokenKind.divToken || op == TokenKind.modToken) {
            f.genInstr("", "cdq", "", "");
            f.genInstr("", "idivl", "%ecx", "");

            if (op == TokenKind.modToken)
                f.genInstr("", "movl", "%edx, %eax", "");
        } else if (op == TokenKind.andToken){
            f.genInstr("", "andl", "%ecx, %eax", "");
        }
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
        Main.log.prettyPrint(" " + this.op.toString() + " ");
    }
}