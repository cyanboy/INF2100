package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 16/10/15.
 */
public class ProcCallStatement extends Statement {
    public ProcCallStatement(int lNum) {
        super(lNum);
    }

    String name;
    List<Expression> expressions = new ArrayList<>();
    PascalDecl decl;

    public static ProcCallStatement parse(Scanner s) {
        enterParser("proc-call-statement");
        ProcCallStatement p = new ProcCallStatement(s.curLineNum());
        p.name = s.curToken.id;
        s.skip(TokenKind.nameToken);

        if (s.curToken.kind == TokenKind.leftParToken) {

            s.skip(TokenKind.leftParToken);
            p.expressions.add(Expression.parse(s));

            while (s.curToken.kind == TokenKind.commaToken) {
                s.skip(TokenKind.commaToken);
                p.expressions.add(Expression.parse(s));
            }

            s.skip(TokenKind.rightParToken);
        }

        leaveParser("proc-call-statement");
        return p;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        if (name.equals("write")) {
            for (Expression exp : expressions) {
                exp.genCode(codeFile);
                Factor c = exp.expr0.term.get(0).factors.get(0);

                if (c instanceof Constant) {

                    if (c instanceof NumericLiteral) {
                        codeFile.genInstr("", "pushl", "%eax", "");
                        codeFile.genInstr("", "call", "write_int", "");
                        codeFile.genInstr("", "addl", "$4,%esp", "");
                    } else if (c instanceof CharLiteral) {
                        codeFile.genInstr("", "pushl", "%eax", "");
                        codeFile.genInstr("", "call", "write_char", "");
                        codeFile.genInstr("", "addl", "$4,%esp", "");
                    } else if (c instanceof StringLiteral) {
                        //
                    }
                }
            }
        } else {

            for (Expression exp : expressions) {
                exp.genCode(codeFile);
                codeFile.genInstr("", "pushl", "%eax", "");
            }

            codeFile.genInstr("", "call", "proc$" + name + "_" + decl.declLevel, "");
            codeFile.genInstr("", "addl", "$" + (expressions.size() * 4) + ",%esp", "");
        }

    }

    @Override
    public void check(Block curScope, Library library) {
        this.decl = curScope.findDecl(name, this);
        for (Expression exp : expressions) {
            exp.check(curScope, library);
        }
    }

    @Override
    public String identify() {
        return null;
    }
}
