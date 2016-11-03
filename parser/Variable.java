package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

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
    public void genCode(CodeFile codeFile) {

    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);

        if(exp != null) {
            Main.log.prettyPrint("[");
            exp.prettyPrint();
            Main.log.prettyPrint("]");
        }
    }

    public void check(Block curScope, Library lib) {
        curScope.findDecl(name, this);
        if (exp != null) exp.check(curScope, lib);
    }

    @Override
    public String identify() {
        return null;
    }
}
