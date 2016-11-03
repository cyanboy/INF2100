package parser;

import main.Main;
import scanner.TokenKind;
import scanner.Scanner;

/**
 * Created by cyanboy on 12/10/15.
 */

public class Program extends PascalDecl {
    public Block progBlock;

    public Program(String id, int lNum) {
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

    public static Program parse(Scanner s) {
        enterParser("program");

        s.skip(TokenKind.programToken);
        Program p = new Program(s.curToken.id, s.curLineNum());
        p.name = s.curToken.id;

        s.skip(TokenKind.nameToken);
        s.skip(TokenKind.semicolonToken);

        p.progBlock = Block.parse(s);

        s.skip(TokenKind.dotToken);
        leaveParser("program");
        return p;
    }


    public void prettyPrint() {
        Main.log.prettyPrint("program");
        Main.log.prettyPrintLn(" " + this.name + ";");
        progBlock.prettyPrint();
        Main.log.prettyPrint(".");
    }

    @Override
    public void check(Block curScope, Library lib) {
        progBlock.check(curScope, lib);
    }

    @Override
    public String identify() {
        return "<program> on line " + lineNum;
    }
}
