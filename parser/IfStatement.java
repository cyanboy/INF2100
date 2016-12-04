package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 16/10/15.
 */
public class IfStatement extends Statement {
    Expression exp;
    Statement body0;
    Statement body1;

    IfStatement(int lNum) {
        super(lNum);
    }

    static IfStatement parse(Scanner s) {
        enterParser("if-statm");
        s.skip(TokenKind.ifToken);

        IfStatement i = new IfStatement(s.curLineNum());

        i.exp = Expression.parse(s);
        s.skip(TokenKind.thenToken);

        i.body0 = Statement.parse(s);
        if (s.curToken.kind == TokenKind.elseToken){
            s.skip(TokenKind.elseToken);
            i.body1 = Statement.parse(s);
        }
        leaveParser("if-statm");
        return i;
    }

    @Override
    public void genCode(CodeFile f) {
        String lab1 = f.getLocalLabel();
        String lab2 = body1 != null ? f.getLocalLabel() : "";

        exp.genCode(f);
        f.genInstr("", "cmpl", "$0, %eax", "");
        f.genInstr("", "je", lab1, "");

        body0.genCode(f);

        if (!lab2.isEmpty())
            f.genInstr("", "jmp", lab2, "");

        f.genInstr(lab1, "", "", "");

        if (!lab2.isEmpty()) {
            body1.genCode(f);
            f.genInstr(lab2, "", "", "");
        }


    }

    @Override
    public void check(Block curScope, Library library) {
        exp.check(curScope, library);
        exp.type.checkType(library.booleanType, "if-test", this, "If-Test is not Boolean");
        body0.check(curScope, library);
        if (body1 != null) body1.check(curScope, library);
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("if ");
        exp.prettyPrint();

        Main.log.prettyPrintLn(" then ");

        Main.log.prettyIndent();
        body0.prettyPrint();

        if(body1 != null) {
            Main.log.prettyPrintLn();
            Main.log.prettyOutdent();
            Main.log.prettyPrintLn("else");

            Main.log.prettyIndent();
            body1.prettyPrint();
            //Main.log.prettyPrintLn();
        }
        Main.log.prettyOutdent();
    }
}
