package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 19/10/15.
 */
public class ConstDecl extends PascalDecl {
    Constant constant;

    public ConstDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override
    void checkWhetherAssignable(PascalSyntax where) {
        where.error("A constant is not assignable");

    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {
        where.error("A constant is not a function");
    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {
        where.error("A constant is not a procedure");
    }

    @Override
    void checkWhetherValue(PascalSyntax where) {
        //OK
    }

    static ConstDecl parse(Scanner s) {
        enterParser("const decl");
        ConstDecl c = new ConstDecl(s.curToken.id, s.curLineNum());

        c.name = s.curToken.id;
        s.skip(TokenKind.nameToken);
        s.skip(TokenKind.equalToken);
        c.constant = Constant.parse(s);
        s.skip(TokenKind.semicolonToken);

        leaveParser("const decl");

        return c;
    }

    @Override
    void check(Block curScope, Library lib) {
        constant.check(curScope, lib);
    }

    @Override
    public String identify() {
        return "<const decl> on line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(this.name + " = ");
        constant.prettyPrint();
        Main.log.prettyPrintLn(";");
    }
}
