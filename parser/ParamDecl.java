package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class ParamDecl extends PascalDecl {
    public ParamDecl(String id, int lNum) {
        super(id, lNum);
    }

    TypeName typeName;

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

    static ParamDecl parse(Scanner s) {
        enterParser("param decl");
        ParamDecl p = new ParamDecl(s.curToken.id, s.curLineNum());

        s.test(TokenKind.nameToken);
        p.name = s.curToken.id;
        s.readNextToken();

        s.skip(TokenKind.colonToken);

        p.typeName = TypeName.parse(s);


        leaveParser("param decl");

        return p;
    }

    @Override
    public String identify() {
        return "<parm-decl> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name + " : ");
        typeName.prettyPrint();
    }
}
