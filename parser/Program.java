package parser;

import main.CodeFile;
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
        where.error("A program is not assignable");
    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {
        where.error("A program is not a function");
    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {
        where.error("A program is not a procedure");
    }

    @Override
    void checkWhetherValue(PascalSyntax where) {
        where.error("A program is not a value");
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
    public void genCode(CodeFile f) {
        String programLabel = String.format("prog$%s", f.getLabel(name));
        int decls = 0;

        if (progBlock.varDeclPart != null)
            decls = 4 * progBlock.varDeclPart.variables.size();

        f.genInstr("", ".globl _main", "", "");
        f.genInstr("", ".globl main", "", "");

        f.genInstr("_main", "", "", "");
        f.genInstr("main", "", "", "");

        f.genInstr("", "call", programLabel, "");
        f.genInstr("", "movl", "$0, %eax", "");
        f.genInstr("", "ret", "", "");

        for (FuncDecl fd : progBlock.funcDeclList)
            fd.genCode(f);

        for (ProcDecl pd : progBlock.procDeclList)
            pd.genCode(f);


        f.genInstr(programLabel, "", "", "");
        f.genInstr("", "enter", String.format("$%d, $1", 32 + decls), "");

        progBlock.genCode(f);

        f.genInstr("", "leave", "", "");
        f.genInstr("", "ret", "", "");

    }

    @Override
    public String identify() {
        return "<program> on line " + lineNum;
    }
}
