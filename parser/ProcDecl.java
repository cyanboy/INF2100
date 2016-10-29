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
    public String identify() {
        return "<proc-decl> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("procedure ");
        Main.log.prettyPrint(name);

        if(declList != null) {
            declList.prettyPrint();
        }

        Main.log.prettyPrint(";");
        Main.log.prettyPrintLn(" ");
        block.prettyPrint();
        Main.log.prettyPrint(";");
    }
}
