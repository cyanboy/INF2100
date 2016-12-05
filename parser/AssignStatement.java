package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;
import types.ArrayType;

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
    public void genCode(CodeFile f) {
        exp.genCode(f);

        if (var.decl instanceof FuncDecl) {
            // movl −4(b + 1)(%ebp),%edx
            // movl %eax,-32(%edx)

            f.genInstr("", "movl", -4 * (var.decl.declLevel + 1) + "(%ebp), %edx", "");
            f.genInstr("", "movl", "%eax, -32(%edx)", "");

        } else if (var.decl.type instanceof ArrayType){
            f.genInstr("", "pushl", "%eax", "");

            var.exp.genCode(f);

            int low = ((ArrayType) var.decl.type).loLim;

            if (low != 0)
                f.genInstr("", "subl", "$" + low + ",%eax", "");

            f.genInstr("", "movl", -4 * var.decl.declLevel + "(%ebp),%edx", "");
            f.genInstr("", "leal", var.decl.declOffset + "(%edx),%edx", "");
            f.genInstr("", "popl", "%ecx", "");
            f.genInstr("", "movl", "%ecx,(%edx,%eax,4)", "");
        } else {
            f.genInstr("", "movl", -4 * var.decl.declLevel + "(%ebp),%edx", "");
            f.genInstr("", "movl", "%eax," + var.decl.declOffset + "(%edx)", "");
        }

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
