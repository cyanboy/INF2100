package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;

/**
 * Created by cyanboy on 20/10/15.
 */
public abstract class Factor extends PascalSyntax {
    @Override
    public String identify() {
        return null;
    }

    public Factor(int n) {
        super(n);
    }

    static Factor parse(Scanner s) {
        enterParser("factor");
        Factor f = null;

        switch (s.curToken.kind) {
            case notToken:
                f = Negation.parse(s);
                break;
            case intValToken:
                f = Constant.parse(s);
                break;
            case leftParToken:
                f = InnerExpression.parse(s);
                break;
            case nameToken:
                switch (s.nextToken.kind) {
                    case leftParToken:
                        f = FuncCall.parse(s);
                        break;
                    case leftBracketToken:
                        f = Variable.parse(s);
                        break;
                    default:
                        f = Variable.parse(s);
                        break;
                }
                break;
            default:
                Main.error(s.curLineNum() + ": Expected factor, got " + s.curToken.id);
        }

        leaveParser("factor");
        return f;
    }


    public abstract void genCode(CodeFile codeFile);

    public abstract void check(Block curScope, Library library);

}
