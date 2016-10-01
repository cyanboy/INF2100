package parser;

import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;


public class ConstDeclPart extends PascalSyntax {
    public ConstDeclPart(int n) {
        super(n);
        constants = new ArrayList<ConstDecl>();
    }

    List<ConstDecl> constants;

    static ConstDeclPart parse(Scanner s) {
        ConstDeclPart c = new ConstDeclPart(s.curLineNum());
        enterParser("const-decl-part");

        s.skip(TokenKind.constToken);

        while(s.curToken.kind == TokenKind.nameToken && s.nextToken.kind == TokenKind.equalToken){
            c.constants.add(ConstDecl.parse(s));
        }

        leaveParser("const-decl-part");
        return c;
    }

    public void check(Block curScope, Library library) {

    }

    @Override
    public String identify() {
        return null;
    }
}
