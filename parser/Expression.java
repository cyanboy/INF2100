package parser;


import main.CodeFile;
import scanner.Scanner;

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

    @Override
    void prettyPrint() {
        expr0.prettyPrint();
        if (relOpr != null) {
            relOpr.prettyPrint();
            expr1.prettyPrint();
        }
    }

    void check(Block curScope, Library library) {
        expr0.check(curScope, library);

        if (relOpr != null) {
            relOpr.check(curScope, library);
            expr1.check(curScope, library);
        }
    }

    public void genCode(CodeFile codeFile) {

    }

    @Override
    public String identify() {
        return null;
    }
}
