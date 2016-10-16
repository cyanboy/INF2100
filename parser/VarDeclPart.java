package parser;

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

    static VarDeclPart parse(Scanner s) {
        enterParser("var-decl-part");

        VarDeclPart v = new VarDeclPart(s.curLineNum());
        s.skip(TokenKind.varToken);

        while(s.curToken.kind == TokenKind.nameToken && s.nextToken.kind == TokenKind.colonToken){
            v.variables.add(VarDecl.parse(s));
        }

        leaveParser("var-decl-part");

        return v;
    }

    @Override
    public String identify() {
        return "<var-decl-part> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("var ");
        variables.forEach(VarDecl::prettyPrint);
    }
}
