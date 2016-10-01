package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class StringLiteral extends Constant {
    public StringLiteral(int lNum) {
        super(lNum);
    }

    String val;

    static StringLiteral parse(Scanner s) {
        enterParser("string-literal");
        StringLiteral sl = new StringLiteral(s.curLineNum());
        s.test(TokenKind.stringValToken);
        sl.val = s.curToken.strVal;
        s.readNextToken();
        leaveParser("string-literal");
        return sl;
    }

    @Override
    public void genCode(CodeFile codeFile) {
    }

    @Override
    public void check(Block curScope, Library library) {
        return;
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
