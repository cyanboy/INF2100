package parser;

import main.CodeFile;
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

    String name;
    ParamDeclList declList;
    Block block;

    static ProcDecl parse(Scanner s) {
        enterParser("proc-decl");
        ProcDecl p = null;
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

        leaveParser("proc-decl");
        return p;
    }

    public void codeGen(CodeFile codeFile) {
        codeFile.genInstr("proc$" + name + "_" + declLevel, "enter",
                "$" + (32+(declList.parameters.size()*4)) + "$" + declLevel, "");

        block.declLevel = this.declLevel;
        block.genCode(codeFile);

        codeFile.genInstr("", "ret", "", "");
        codeFile.genInstr("", "leave", "", "");
    }

    void check(Block curScope, Library library) {
        block.outerScope = curScope;
        for (ParamDecl pd : declList.parameters) {
            block.addDecl(pd.name, pd);
        }
        block.check(block, library);
    }

    @Override
    public String identify() {
        return "<proc-decl> at line " + lineNum;
    }
}