package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;


public class ConstDeclPart extends PascalSyntax {
    public ConstDeclPart(int n) {
        super(n);
        constants = new ArrayList<>();
    }

    List<ConstDecl> constants;

    static ConstDeclPart parse(Scanner s) {
        ConstDeclPart c = new ConstDeclPart(s.curLineNum());
        enterParser("const decl part");

        s.skip(TokenKind.constToken);

        while(s.curToken.kind == TokenKind.nameToken && s.nextToken.kind == TokenKind.equalToken){
            c.constants.add(ConstDecl.parse(s));
        }

        leaveParser("const decl part");
        return c;
    }

    @Override
    public String identify() {
        return "<const decl part> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrintLn("const");

        Main.log.prettyIndent();
        constants.forEach(ConstDecl::prettyPrint);
        Main.log.prettyOutdent();
    }
}
