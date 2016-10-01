package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 03/11/15.
 */
public class Name extends Constant {
    String name;

    public Name(int lNum) {
        super(lNum);
    }

    static Name parse(Scanner s) {
        enterParser("NamedConst");
        Name n = new Name(s.curLineNum());

        s.test(TokenKind.nameToken);
        n.name = s.curToken.id;
        s.readNextToken();

        leaveParser("NamedConst");
        return n;
    }

    @Override
    public void genCode(CodeFile codeFile) {

    }

    @Override
    public void check(Block curScope, Library library) {

    }
}
