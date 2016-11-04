package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 16/10/15.
 */
public class AssignStatement extends Statement {
    public AssignStatement(int lNum) {
        super(lNum);
    }

    Variable var;
    Expression exp;


    public static AssignStatement parse(Scanner s) {
        enterParser("assign statm");
        AssignStatement a = new AssignStatement(s.curLineNum());

        a.var = Variable.parse(s);
        s.skip(TokenKind.assignToken);
        a.exp = Expression.parse(s);

        leaveParser("assign statm");
        return a;
    }

    @Override
    public void check(Block curScope, Library library) {
        var.check(curScope, library);
        var.decl.checkWhetherAssignable(this);
        exp.check(curScope, library);

        var.type.checkType(exp.type, ":=", this, "assignment type mismatch");
    }

    @Override
    public void genCode(CodeFile codeFile) {

    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        var.prettyPrint();
        Main.log.prettyPrint(" := ");
        exp.prettyPrint();
    }
}
