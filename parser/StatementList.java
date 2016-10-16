package parser;

import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 16/10/15.
 */
public class StatementList extends PascalSyntax {
    public StatementList(int n) {
        super(n);
    }

    List<Statement> statements = new ArrayList<>();

    public static StatementList parse(Scanner s) {
        enterParser("statement-list");
        StatementList sl = new StatementList(s.curLineNum());


        sl.statements.add(Statement.parse(s));

        while (s.curToken.kind == TokenKind.semicolonToken) {
            s.skip(TokenKind.semicolonToken);
            sl.statements.add(Statement.parse(s));
        }

        leaveParser("statement-list");
        return sl;
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {

    }
}
