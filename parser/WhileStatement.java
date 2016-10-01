package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 16/10/15.
 */
public class WhileStatement extends Statement {
    Expression expr;
    Statement body;


    WhileStatement(int lNum) {
        super(lNum);
    }

    @Override
    public String identify() {
        return null;
    }

    public static WhileStatement parse(Scanner s) {
        enterParser("while-statement");

        s.skip(TokenKind.whileToken);

        WhileStatement w = new WhileStatement(s.curLineNum());

        w.expr = Expression.parse(s);
        s.skip(TokenKind.doToken);
        w.body = Statement.parse(s);

        leaveParser("while-statement");
        return w;
    }

    public void genCode(CodeFile codeFile) {
        String endLabel = codeFile.getLocalLabel();
        String testLabel = codeFile.getLocalLabel();

        codeFile.genInstr(testLabel, "", "", "Start while-statement");

        expr.genCode(codeFile);

        codeFile.genInstr("", "cmpl", "$0, %eax", "");
        codeFile.genInstr("", "je", endLabel, "");

        body.genCode(codeFile);

        codeFile.genInstr("", "jmp", testLabel, "");
        codeFile.genInstr(endLabel, "", "", "End while-statement");

    }

    public void check(Block curScope, Library library) {
        expr.check(curScope, library);
        body.check(curScope, library);
    }
}
