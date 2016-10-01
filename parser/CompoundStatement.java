package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 16/10/15.
 */
public class CompoundStatement extends Statement {
    CompoundStatement(int lNum) {
        super(lNum);
    }
    StatementList statements;

    public static CompoundStatement parse(Scanner s) {
        enterParser("compound-statement");
        CompoundStatement c = new CompoundStatement(s.curLineNum());

        s.skip(TokenKind.beginToken);
        c.statements = StatementList.parse(s);
        s.skip(TokenKind.endToken);

        leaveParser("compound-statement");
        return c;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        for (Statement statement : statements.statements) {
            statement.genCode(codeFile);
        }
    }

    public void check(Block curScope, Library library) {
        for (Statement st : statements.statements) {
            st.check(curScope, library);
        }
    }

    @Override
    public String identify() {
        return null;
    }
}
