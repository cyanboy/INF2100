package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 20/10/15.
 */
public class SimpleExpression extends Expression {
    public SimpleExpression(int lNum) {
        super(lNum);
    }

    PrefixOpr prefixOpr;

    List<Term> term = new ArrayList<>();
    List<TermOpr> termOpr = new ArrayList<>();

    static SimpleExpression parse(Scanner s) {
        enterParser("simple-expression");
        SimpleExpression se = new SimpleExpression(s.curLineNum());

        if (s.curToken.kind.isPrefixOpr()) {
            se.prefixOpr = PrefixOpr.parse(s);
        }

        se.term.add(Term.parse(s));

        while (s.curToken.kind.isTermOpr()) {
            se.termOpr.add(TermOpr.parse(s));
            se.term.add(Term.parse(s));
        }

        leaveParser("simple-expression");
        return se;
    }

    @Override
    void check(Block curScope, Library library) {
        for (Term t : term) {
            t.check(curScope, library);
        }
    }

    public void genCode(CodeFile codeFile) {
        if (prefixOpr != null && prefixOpr.op == TokenKind.subtractToken) {
            codeFile.genInstr("", "negl", "%eax", "");
        }


        for (int i = 1; i < term.size() - 1; i++) {
            term.get(i).genCode(codeFile);

            if (!termOpr.isEmpty()) {
                TermOpr op = termOpr.get(i + 1);
                codeFile.genInstr("", "pushl", "%eax", "");
                term.get(i + 1).genCode(codeFile);
                codeFile.genInstr("", "movl", "%eax,%ecx", "");
                codeFile.genInstr("", "popl", "%eax", "");

                if (op.op == TokenKind.addToken) {
                    codeFile.genInstr("", "addl", "%ecx,%eax", "");
                } else if (op.op == TokenKind.subtractToken) {
                    codeFile.genInstr("", "subl", "%ecx,%eax", "");
                } else if (op.op == TokenKind.orToken) {
                    codeFile.genInstr("", "orl", "%ecx,%eax", "");
                }
            }
        }


    }

    @Override
    public String identify() {
        return super.identify();
    }
}
