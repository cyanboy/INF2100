package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 20/10/15.
 */
public class VarDeclPart extends PascalSyntax {

    List<VarDecl> variables = new ArrayList<>();

    public VarDeclPart(int lNum) {
        super(lNum);
    }

    @Override
    void check(Block curScope, Library lib) {
        variables.forEach(v -> v.check(curScope, lib));
    }

    @Override
    public void genCode(CodeFile f) {

    }

    static VarDeclPart parse(Scanner s) {
        enterParser("var decl part");

        VarDeclPart v = new VarDeclPart(s.curLineNum());
        s.skip(TokenKind.varToken);

        while(s.curToken.kind == TokenKind.nameToken && s.nextToken.kind == TokenKind.colonToken){
            v.variables.add(VarDecl.parse(s));
        }

        leaveParser("var decl part");

        return v;
    }

    @Override
    public String identify() {
        return "<var decl part> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrintLn("var");

        Main.log.prettyIndent();
        variables.forEach(VarDecl::prettyPrint);
        Main.log.prettyOutdent();
    }
}
