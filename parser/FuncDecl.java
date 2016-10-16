package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 19/10/15.
 */
public class FuncDecl extends PascalDecl {
    Block body;
    ParamDeclList declList;
    TypeName typeName;

    public FuncDecl(String id, int lNum) {
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

    static FuncDecl parse(Scanner s) {
        s.skip(TokenKind.functionToken);
        FuncDecl f = new FuncDecl(s.curToken.id, s.curLineNum());
        s.skip(TokenKind.nameToken);


        if(s.curToken.kind == TokenKind.leftParToken){
            f.declList = ParamDeclList.parse(s);
        }

        s.skip(TokenKind.colonToken);
        f.typeName = TypeName.parse(s);
        s.skip(TokenKind.semicolonToken);
        f.body = Block.parse(s);
        s.skip(TokenKind.semicolonToken);

        return f;
    }


    void check(Block curScope, Library library) {
    }

    @Override
    public String identify() {
        return "<func-decl> at line" + lineNum;
    }

    @Override
    void prettyPrint() {

    }
}
