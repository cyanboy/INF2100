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

        s.test(TokenKind.nameToken);

        Program p = new Program(s.curToken.id, s.curLineNum());

        s.readNextToken();

        s.skip(TokenKind.semicolonToken);

        p.progBlock = Block.parse(s);

        p.progBlock.context = p;

        s.skip(TokenKind.eofToken);
        leaveParser("program");
        return p;
    }


    public void prettyPrint() {
        Main.log.prettyPrint("program");
        Main.log.prettyPrintLn(" " + this.name + ";");
        Main.log.prettyIndent();
        progBlock.prettyPrint();
        Main.log.prettyOutdent();
        Main.log.prettyPrint(".");
    }

    @Override
    public String identify() {
        return "<program> on line " + lineNum;
    }
}
