package parser;

import main.CodeFile;
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


    public void check(Block curScope, Library library) {
        progBlock.check(curScope, library);
    }


    public void genCode(CodeFile codeFile) {
        this.declLevel = 1;

        codeFile.genInstr("", ".globl", "_main", "");
        codeFile.genInstr("", ".globl", "main", "");
        codeFile.genInstr("_main", "", "", "");
        codeFile.genInstr("main", "call", "prog$" + this.name + "_"  + this.declLevel, "");
        codeFile.genInstr("", "movl", "$0,%eax", "");
        codeFile.genInstr("", "ret", "", "");


        progBlock.genCode(codeFile);

        codeFile.genInstr("prog$" + name + "_"  + declLevel,
                "enter", "$" + (32 + (progBlock.varDeclPart.variables.size() * 4)) + ",$1",
                "");

        progBlock.statementList.genCode(codeFile);

        codeFile.genInstr("", "leave", "", "");
        codeFile.genInstr("", "ret", "", "");

    }

    public void prettyPrint() {

    }

    @Override
    public String identify() {
        return "";
    }
}
