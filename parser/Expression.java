package parser;


import main.CodeFile;
import scanner.Scanner;
import types.*;

public class Expression extends PascalSyntax {
    Expression(int lNum) {
        super(lNum);
    }

    SimpleExpression expr0;
    RelOpr relOpr;
    SimpleExpression expr1;

    types.Type type;

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
        type = expr0.type;

        if (relOpr != null) {
            relOpr.check(curScope, library);
            expr1.check(curScope, library);
            String op = relOpr.kind.toString();

            type.checkType(expr1.type, op, this, "Operands to " + op + " are of different type!");

            type = library.booleanType;
        }
    }

    public void genCode(CodeFile f) {
        expr0.genCode(f);

        if (relOpr != null) {
            f.genInstr("", "pushl", "%eax", "");
            expr1.genCode(f);
            relOpr.genCode(f);

        }

    }

    @Override
    public String identify() {
        return null;
    }
}
