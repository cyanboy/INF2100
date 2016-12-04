package parser;

import main.CodeFile;
import main.Main;
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
    PascalDecl decl;

    static TypeName parse(Scanner s) {
        enterParser("type name");

        TypeName t = new TypeName(s.curLineNum());
        t.name = s.curToken.id;
        s.skip(TokenKind.nameToken);

        leaveParser("type name");
        return t;
    }

    @Override
    void check(Block curScope, Library library) {
        decl = library.findDecl(name, this);
        type = decl.type;
    }

    @Override
    public void genCode(CodeFile f) {

    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);
    }
}
