package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.ArrayType;

/**
 * Created by cyanboy on 18/10/15.
 */
public class Variable extends Factor {
    public Variable(int n) {
        super(n);
    }

    String name;
    Expression exp;

    PascalDecl decl;

    public static Variable parse(Scanner s) {
        enterParser("variable");

        Variable v = new Variable(s.curLineNum());
        v.name = s.curToken.id;

        s.skip(TokenKind.nameToken);

        if (s.curToken.kind == TokenKind.leftBracketToken) {
            s.skip(TokenKind.leftBracketToken);
            v.exp = Expression.parse(s);
            s.skip(TokenKind.rightBracketToken);
        }

        leaveParser("variable");
        return v;
    }

    @Override
    public void genCode(CodeFile f) {
        if (decl instanceof ConstDecl) {
            int value = ((ConstDecl) decl).constant.constval;
            f.genInstr("", "movl", "$" + value + ",%eax", "");
        } else if (exp != null) {
            exp.genCode(f);
            int low = ((ArrayType) decl.type).loLim;

            if (low > 0)
                f.genInstr("", "subl", "$" + low + "%eax", "");

            f.genInstr("", "movl", -4 * decl.declLevel + "(%ebp),%edx", "");
            f.genInstr("", "leal", decl.declOffset + "(%edx), %edx", "");
            f.genInstr("", "movl", "(%edx,%eax,4),%eax", "");

        }else {
            f.genInstr("", "movl", -4 * decl.declLevel + "(%ebp),%edx", "");
            f.genInstr("", "movl", decl.declOffset + "(%edx),%eax", "");
        }
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);

        if (exp != null) {
            Main.log.prettyPrint("[");
            exp.prettyPrint();
            Main.log.prettyPrint("]");
        }
    }

    public void check(Block curScope, Library lib) {
        decl = curScope.findDecl(name, this);
        type = decl.type;
        if (exp != null) {
            exp.check(curScope, lib);
            type = ((ArrayType) decl.type).elemType;
        }
    }

    @Override
    public String identify() {
        return null;
    }
}
