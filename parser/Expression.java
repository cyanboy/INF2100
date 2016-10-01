package parser;


import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

public class Expression extends PascalSyntax {
    Expression(int lNum) {
        super(lNum);
    }

    SimpleExpression expr0;
    RelOpr relOpr;
    SimpleExpression expr1;

    static Expression parse(Scanner s) {
        enterParser("expression");
        Expression e = new Expression(s.curLineNum());
        e.expr0 = SimpleExpression.parse(s);

        if (s.curToken.kind.isRelOpr()) {
            e.relOpr = RelOpr.parse(s);
            e.expr1 = SimpleExpression.parse(s);
        }

        leaveParser("expression");
        return e;
    }

    void check(Block curScope, Library library) {
        if (expr0 != null) {
            expr0.check(curScope, library);
        }

        if (expr1 != null) {
            expr1.check(curScope, library);
        }
    }

    public void genCode(CodeFile codeFile) {
        expr0.genCode(codeFile);

        if (relOpr != null) {
            codeFile.genInstr("", "pushl", "%eax", "");

            expr1.genCode(codeFile);

            codeFile.genInstr("", "popl", "%ecx", "");
            codeFile.genInstr("", "cmpl", "%eax, %ecx", "");
            codeFile.genInstr("", "movl", "$0, %eax", "");

            if (relOpr.kind == TokenKind.equalToken) {
                codeFile.genInstr("", "sete", "%al", "");
            } else if (relOpr.kind == TokenKind.notEqualToken) {
                codeFile.genInstr("", "setne", "%al", "");
            } else if (relOpr.kind == TokenKind.lessToken) {
                codeFile.genInstr("", "setl", "%al", "");
            } else if (relOpr.kind == TokenKind.lessEqualToken) {
                codeFile.genInstr("", "setle", "%al", "");
            } else if (relOpr.kind == TokenKind.greaterToken) {
                codeFile.genInstr("", "setg", "%al", "");
            } else if (relOpr.kind == TokenKind.greaterEqualToken) {
                codeFile.genInstr("", "setge", "%al", "");
            }

        }
    }

    @Override
    public String identify() {
        return null;
    }
}
