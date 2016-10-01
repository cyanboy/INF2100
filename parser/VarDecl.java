package parser;

import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class VarDecl extends PascalDecl {

    public VarDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override
    void checkWhetherAssignable(PascalSyntax where) {

    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {

    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {

    }

    @Override
    void checkWhetherValue(PascalSyntax where) {

    }

    Type type;
    String name;

    static VarDecl parse(Scanner s) {
        enterParser("var-decl");

        s.test(TokenKind.nameToken);
        VarDecl v = new VarDecl(s.curToken.id, s.curLineNum());
        v.name = s.curToken.id;

        s.skip(TokenKind.nameToken);

        s.skip(TokenKind.colonToken);
        v.type = Type.parse(s);
        s.skip(TokenKind.semicolonToken);

        leaveParser("var-decl");
        return v;
    }

    public void check(Block curScope, Library library) {
        type.check(curScope, library);
    }
    @Override
    public String identify() {
        return "<var-decl> on line " + this.lineNum;
    }
}