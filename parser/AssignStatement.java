package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 16/10/15.
 */
public class AssignStatement extends Statement {
    public AssignStatement(int lNum) {
        super(lNum);
    }

    Expression exp;
    Variable var;

    public static AssignStatement parse(Scanner s) {
        enterParser("assign-statement");
        AssignStatement a = new AssignStatement(s.curLineNum());

        a.var = Variable.parse(s);
        s.skip(TokenKind.assignToken);
        a.exp = Expression.parse(s);

        leaveParser("assign-statement");
        return a;
    }

    @Override
    public void check(Block curScope, Library library) {}

    @Override
    public void genCode(CodeFile codeFile) {
        exp.genCode(codeFile);

        int b = var.decl.declLevel; //block level
        int o = var.decl.declOffset; // offset

        codeFile.genInstr("", "movl", -4*b + "(%ebp),%edx", "");
        codeFile.genInstr("", "movl", "%eax," + -o + "(%edx)", var.decl.name + ":=");
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {

    }
}
