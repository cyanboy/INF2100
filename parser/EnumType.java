package parser;

import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 20/10/15.
 */
public class EnumType extends Type {
    public EnumType(int n) {
        super(n);
    }

    List<EnumLiteral> literals = new ArrayList<>();

    static EnumType parse(Scanner s) {
        enterParser("enum-type");
        EnumType e = new EnumType(s.curLineNum());

        s.skip(TokenKind.leftParToken);
        //TODO: while , enumLiteral

        while (s.curToken.kind == TokenKind.commaToken) {

        }

        s.skip(TokenKind.rightParToken);

        leaveParser("enum-type");
        return e;
    }

    @Override
    void check(Block curScope, Library library) {
        for (EnumLiteral el : literals) {
            el.check(curScope, library);
        }
    }

    @Override
    public String identify() {
        return super.identify();
    }
}
