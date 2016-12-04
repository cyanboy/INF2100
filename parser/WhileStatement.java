package parser;

import main.CodeFile;
import main.Main;
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

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("while ");
        expr.prettyPrint();
        Main.log.prettyPrintLn(" do");
        body.prettyPrint();
    }

    public static WhileStatement parse(Scanner s) {
        enterParser("while-statm");

        s.skip(TokenKind.whileToken);

        WhileStatement w = new WhileStatement(s.curLineNum());

        w.expr = Expression.parse(s);
        s.skip(TokenKind.doToken);
        w.body = Statement.parse(s);

        leaveParser("while-statm");
        return w;
    }

    public void genCode(CodeFile f) {
        String lab1 = f.getLocalLabel(),
                lab2 = f.getLocalLabel();

        f.genInstr(lab1, "", "", "");
        expr.genCode(f);

        f.genInstr("", "cmpl", "$0, %eax", "");
        f.genInstr("", "je", lab2, "");

        body.genCode(f);

        f.genInstr("", "jmp", lab1, "");

        f.genInstr(lab2, "", "", "");
    }

    public void check(Block curScope, Library library) {
        expr.check(curScope, library);
        expr.type.checkType(library.booleanType, "while-test", this, "While-Test is not Boolean");
        body.check(curScope, library);
    }
}
