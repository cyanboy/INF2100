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

    }

    @Override
    public void check(Block curScope, Library library) {

    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {

    }
}
