package parser;

import main.CodeFile;
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
        enterParser("if-statement");
        s.skip(TokenKind.ifToken);

        IfStatement i = new IfStatement(s.curLineNum());

        i.exp = Expression.parse(s);
        s.skip(TokenKind.thenToken);

        i.body0 = Statement.parse(s);
        if (s.curToken.kind == TokenKind.elseToken){
            s.skip(TokenKind.elseToken);
            i.body1 = Statement.parse(s);
        }
        leaveParser("if-statement");
        return i;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        String lab1 = codeFile.getLocalLabel();
        String lab2 = null;

        exp.genCode(codeFile);

        codeFile.genInstr("", "cmpl", "$0, %eax","");
        codeFile.genInstr("", "je", lab1, "");
        body0.genCode(codeFile);

        if (body1 != null) {
            lab2 = codeFile.getLocalLabel();
            codeFile.genInstr("", "jmp", lab2, "");
        }

        codeFile.genInstr(lab1, "", "", "");
        body1.genCode(codeFile);

        if (lab2 != null) {
            codeFile.genInstr(lab2, "", "", "");
        }

    }

    @Override
    public void check(Block curScope, Library library) {
        exp.check(curScope, library);


        if (body0 != null) {
            body0.check(curScope, library);
        }

        if (body1 != null) {
            body1.check(curScope, library);
        }
    }

    @Override
    public String identify() {
        return null;
    }
}
