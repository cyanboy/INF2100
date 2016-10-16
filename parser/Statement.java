package parser;

import main.CodeFile;
import scanner.*;

/**
 * Created by cyanboy on 14/10/15.
 */
public abstract class Statement extends PascalSyntax {

    public Statement(int lNum) {
        super(lNum);
    }

    static Statement parse(Scanner s) {
        enterParser("statement");
        Statement st = null;

        switch (s.curToken.kind) {
            case beginToken:
                st = CompoundStatement.parse(s);
                break;
            case ifToken:
                st = IfStatement.parse(s);
                break;
            case nameToken:
                switch (s.nextToken.kind) {
                    case leftBracketToken:
                    case assignToken:
                        st = AssignStatement.parse(s);
                        break;
                    default:
                        st = ProcCall.parse(s);
                        break;
                }
                break;
            case whileToken:
                st = WhileStatement.parse(s);
                break;
            default:
                st = EmptyStatement.parse(s);
                break;
        }

        leaveParser("statement");
        return st;
    }

    abstract public void genCode(CodeFile codeFile);

    abstract public void check(Block curScope, Library library);
}
