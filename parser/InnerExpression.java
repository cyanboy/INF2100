package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 21/10/15.
 */
public class InnerExpression extends Factor {
    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("(");
        exp.prettyPrint();
        Main.log.prettyPrint(")");
    }

    public InnerExpression(int n) {
        super(n);
    }

    Expression exp;

    static InnerExpression parse(Scanner s) {
        enterParser("inner expr");
        InnerExpression i = new InnerExpression(s.curLineNum());

        s.skip(TokenKind.leftParToken);
        i.exp = Expression.parse(s);
        s.skip(TokenKind.rightParToken);

        leaveParser("inner expr");
        return i;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        exp.genCode(codeFile);
    }


    @Override
    public void check(Block curScope, Library library) {
        exp.check(curScope, library);
        this.type = exp.type;
    }
}
