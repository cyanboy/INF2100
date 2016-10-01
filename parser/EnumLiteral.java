package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class EnumLiteral extends Constant {
    public EnumLiteral(int lNum) {
        super(lNum);
    }

    String name;

    static EnumLiteral parse(Scanner s) {
        enterParser("enum-literal");
        EnumLiteral e = new EnumLiteral(s.curLineNum());

        s.test(TokenKind.nameToken);
        e.name= s.curToken.id;
        s.readNextToken();

        leaveParser("enum-literal");
        return e;
    }

    @Override
    public void genCode(CodeFile codeFile) {

    }

    @Override
    public void check(Block curScope, Library library) {
        curScope.findDecl(name, this);
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
