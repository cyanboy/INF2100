package parser;

import main.CodeFile;
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

    public InnerExpression(int n) {
        super(n);
    }

    Expression exp;

    static InnerExpression parse(Scanner s) {
        enterParser("inner-expression");
        InnerExpression i = new InnerExpression(s.curLineNum());

        s.skip(TokenKind.leftParToken);
        i.exp = Expression.parse(s);
        s.skip(TokenKind.rightParToken);

        leaveParser("inner-expression");
        return i;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        exp.genCode(codeFile);
    }


    @Override
    public void check(Block curScope, Library library) {
        exp.check(curScope, library);
    }
}
