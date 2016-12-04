package parser;

import main.CodeFile;
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
        // OK
    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {
        where.error("A parameter is not a function");

    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {
        where.error("A parameter is not a procedure");

    }

    @Override
    void checkWhetherValue(PascalSyntax where) {
        //OK?
    }

    //String name;

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
    void check(Block curScope, Library lib) {
        typeName.check(curScope, lib);
        type = typeName.type;
    }

    @Override
    public void genCode(CodeFile f) {

    }

    @Override
    public String identify() {
        return "<parm decl> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name + " : ");
        typeName.prettyPrint();
    }
}
