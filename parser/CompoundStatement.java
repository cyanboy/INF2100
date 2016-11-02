package parser;

import main.CodeFile;
import main.Main;
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
        enterParser("compound statm");
        CompoundStatement c = new CompoundStatement(s.curLineNum());

        s.skip(TokenKind.beginToken);
        c.statements = StatementList.parse(s);
        s.skip(TokenKind.endToken);

        leaveParser("compound statm");
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

    @Override

    void prettyPrint() {
        Main.log.prettyPrintLn("begin");
        Main.log.prettyIndent();

        statements.prettyPrint();
        Main.log.prettyOutdent();
        Main.log.prettyPrint("end");
    }
}
