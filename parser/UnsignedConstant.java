package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
abstract class UnsignedConstant extends Factor {
    UnsignedConstant(int lNum) {
        super(lNum);
    }

    static UnsignedConstant parse(Scanner s) {
        UnsignedConstant uc = null;

        enterParser("unsigned constant");

        if(s.curToken.kind == TokenKind.nameToken){
            uc = Name.parse(s);
        }else if(s.curToken.kind == TokenKind.intValToken){
            uc = NumericLiteral.parse(s);
        } else if (s.curToken.kind == TokenKind.charValToken) {
            uc = CharLiteral.parse(s);
        }else{
            Main.error("Error with UnsignedConstant. Found a " + s.curToken.kind.toString() + " token with a "
                    + s.nextToken.kind.toString() + " after it");
        }

        leaveParser("unsigned constant");

        return uc;
    }


    public void check(Block curScope, Library library) {}

    @Override
    public String identify() {
        return "";
    }

}
