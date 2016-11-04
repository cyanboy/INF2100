package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;

/**
 * Created by cyanboy on 03/11/15.
 */
public class Name extends UnsignedConstant {
    String name;
    PascalDecl decl;
    types.Type type;

    public Name(int lNum) {
        super(lNum);
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);
    }

    static Name parse(Scanner s) {
        enterParser("name");
        Name n = new Name(s.curLineNum());

        s.test(TokenKind.nameToken);
        n.name = s.curToken.id;
        s.readNextToken();

        leaveParser("name");
        return n;
    }

    @Override
    public void genCode(CodeFile codeFile) {

    }

    @Override
    public void check(Block curScope, Library library) {
        decl = curScope.findDecl(name, this);
        type = decl.type;
    }
}
