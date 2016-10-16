package parser;

import main.CodeFile;
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
    Expression expr;

    PascalDecl decl;

    public static Variable parse(Scanner s) {
        enterParser("variable");
        Variable v = new Variable(s.curLineNum());

        s.test(TokenKind.nameToken);
        v.name = s.curToken.id;

        s.skip(TokenKind.nameToken);

        if (s.curToken.kind == TokenKind.leftBracketToken) {
            s.skip(TokenKind.leftBracketToken);
            v.expr = Expression.parse(s);
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

    }

    public void check(Block curScope, Library lib) {

    }

    @Override
    public String identify() {
        return null;
    }
}
