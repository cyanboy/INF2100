package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cyanboy on 20/10/15.
 */
public class ParamDeclList extends PascalSyntax {
    public ParamDeclList(int n) {
        super(n);
    }

    List<ParamDecl> parameters = new ArrayList<>();

    static ParamDeclList parse(Scanner s) {
        enterParser("ParamDeclList");
        ParamDeclList p = new ParamDeclList(s.curLineNum());

        s.skip(TokenKind.leftParToken);
        p.parameters.add(ParamDecl.parse(s));

        while(s.curToken.kind == TokenKind.semicolonToken){
            s.skip(TokenKind.semicolonToken);
            p.parameters.add(ParamDecl.parse(s));
        }
        s.skip(TokenKind.rightParToken);

        leaveParser("param-decl-list");
        return p;
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("(");

        parameters.forEach(p -> {
            p.prettyPrint();
        });
        Main.log.prettyPrint("; ");

        Main.log.prettyPrint(")");
    }
}
