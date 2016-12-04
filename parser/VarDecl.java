package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class VarDecl extends PascalDecl {

    public VarDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override
    void checkWhetherAssignable(PascalSyntax where) {
        // ALL GOOD
    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {
        where.error("A variable is not a function");
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name + " : ");
        pType.prettyPrint();
        Main.log.prettyPrintLn(";");
    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {
        where.error("A variable is not a procedure");
    }

    @Override
    void checkWhetherValue(PascalSyntax where) {
        //OK?
    }

    parser.Type pType;
    String name;

    static VarDecl parse(Scanner s) {
        enterParser("var decl");

        s.test(TokenKind.nameToken);
        VarDecl v = new VarDecl(s.curToken.id, s.curLineNum());
        v.name = s.curToken.id;

        s.skip(TokenKind.nameToken);

        s.skip(TokenKind.colonToken);
        v.pType = Type.parse(s);
        s.skip(TokenKind.semicolonToken);

        leaveParser("var decl");
        return v;
    }

    @Override
    public void genCode(CodeFile f) {

    }

    public void check(Block curScope, Library library) {
        pType.check(curScope, library);
        type = pType.type;
    }
    @Override
    public String identify() {
        return "<var decl> on line " + this.lineNum;
    }

}
