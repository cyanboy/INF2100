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
public class StatementList extends PascalSyntax {
    public StatementList(int n) {
        super(n);
    }


    List<Statement> statements = new ArrayList<>();

    @Override
    void check(Block curScope, Library lib) {
        for (Statement s : statements) {
            s.check(curScope, lib);
        }
    }

    @Override
    public void genCode(CodeFile f) {
        for (Statement s : statements) {
            s.genCode(f);
        }
    }

    public static StatementList parse(Scanner s) {
        enterParser("statm list");
        StatementList sl = new StatementList(s.curLineNum());

        sl.statements.add(Statement.parse(s));

        while (s.curToken.kind == TokenKind.semicolonToken) {
            s.skip(TokenKind.semicolonToken);
            sl.statements.add(Statement.parse(s));
        }

        leaveParser("statm list");
        return sl;
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Iterator<Statement> it = statements.iterator();

        while (it.hasNext()) {
            Statement s = it.next();
            s.prettyPrint();

            if (it.hasNext())
                Main.log.prettyPrint(";");

            Main.log.prettyPrintLn();
            
        }
    }
}
