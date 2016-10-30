package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cyanboy on 16/10/15.
 */
public class ProcCall extends Statement {
    public ProcCall(int lNum) {
        super(lNum);
    }

    String name;
    List<Expression> expressions = new ArrayList<>();
    PascalDecl decl;

    public static ProcCall parse(Scanner s) {
        enterParser("proc call");

        ProcCall p = new ProcCall(s.curLineNum());
        p.name = s.curToken.id;
        s.skip(TokenKind.nameToken);

        if (s.curToken.kind == TokenKind.leftParToken) {

            s.skip(TokenKind.leftParToken);
            p.expressions.add(Expression.parse(s));

            while (s.curToken.kind == TokenKind.commaToken) {
                s.skip(TokenKind.commaToken);
                p.expressions.add(Expression.parse(s));
            }

            s.skip(TokenKind.rightParToken);
        }

        leaveParser("proc call");
        return p;
    }

    @Override
    public void genCode(CodeFile codeFile) {

    }

    @Override
    public void check(Block curScope, Library library) {

    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);
        Main.log.prettyPrint("(");

        Iterator<Expression> it = expressions.iterator();

        while (it.hasNext()) {
            Expression exp = it.next();
            exp.prettyPrint();

            if (it.hasNext()) {
                Main.log.prettyPrint(", ");
            }

        }

        Main.log.prettyPrint(")");
    }
}
