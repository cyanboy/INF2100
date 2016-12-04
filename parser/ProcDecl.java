package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 19/10/15.
 */
public class ProcDecl extends PascalDecl {
    public ProcDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override
    void checkWhetherAssignable(PascalSyntax where) {
        Main.error(lineNum, name + " is a procedure, not a variable");
    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {
        Main.error(lineNum, name + " is a procedure, not a function");
    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {
        // AIGHT
    }

    @Override
    void checkWhetherValue(PascalSyntax where) {
        Main.error(lineNum, name + " is a procedure, not a value");
    }

    @Override
    public void genCode(CodeFile f) {
        String procLabel = f.getLabel(name);

        for (FuncDecl fd : block.funcDeclList)
            fd.genCode(f);

        for (ProcDecl pd : block.procDeclList)
            pd.genCode(f);

        f.genInstr(String.format("proc$%s", procLabel), "", "", "");
        int decls = 0;

        if (block.varDeclPart != null)
            decls = 4 * block.varDeclPart.variables.size();

        f.genInstr("", "enter", String.format("$%d, $%d", 32 + decls, block.declLevel), "");

        block.genCode(f);

        f.genInstr("", "leave", "", "");
        f.genInstr("", "ret", "", "");
    }

    //String name;
    ParamDeclList declList;
    Block block;

    static ProcDecl parse(Scanner s) {
        enterParser("proc decl");
        ProcDecl p = new ProcDecl(s.curToken.id, s.curLineNum());
        s.skip(TokenKind.procedureToken);
        s.test(TokenKind.nameToken);
        p.name = s.curToken.id;
        s.readNextToken();

        if(s.curToken.kind == TokenKind.leftParToken){
            p.declList = ParamDeclList.parse(s);
        }

        s.skip(TokenKind.semicolonToken);
        p.block = Block.parse(s);
        s.skip(TokenKind.semicolonToken);

        leaveParser("proc decl");
        return p;
    }

    @Override
    void check(Block curScope, Library lib) {
        if (declList != null) declList.check(block, lib);
        block.check(curScope, lib);
    }

    @Override
    public String identify() {
        return "<proc decl> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("procedure ");
        Main.log.prettyPrint(name);

        if(declList != null) {
            declList.prettyPrint();
        }

        Main.log.prettyPrintLn(";");
        block.prettyPrint();
        Main.log.prettyPrint(";");
    }
}
