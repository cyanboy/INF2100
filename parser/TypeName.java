package parser;

import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class TypeName extends Type {
    public TypeName(int lNum) {
        super(lNum);
    }

    String name;

    static TypeName parse(Scanner s) {
        enterParser("type-name");
        TypeName t = new TypeName(s.curLineNum());

        s.test(TokenKind.nameToken);
        t.name = s.curToken.id;
        s.readNextToken();

        leaveParser("type-name");
        return t;
    }

    void check(Block curScope, Library library) {
        curScope.findDecl(name, this);
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
