package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import types.*;

/**
 * Created by cyanboy on 20/10/15.
 */
public abstract class Factor extends PascalSyntax {
    types.Type type;

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
            case notToken:
                f = Negation.parse(s);
                break;
            case intValToken:
            case charValToken:
                f = UnsignedConstant.parse(s);
                break;
            case leftParToken:
                f = InnerExpression.parse(s);
                break;
            default:
                Main.error(s.curLineNum() + ": Expected factor, got " + s.curToken.id);
        }

        leaveParser("factor");
        return f;
    }

    abstract void prettyPrint();

    public abstract void genCode(CodeFile codeFile);

    public abstract void check(Block curScope, Library library);

}
