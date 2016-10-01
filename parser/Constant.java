package parser;

import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
abstract class Constant extends Factor {
    Constant(int lNum) {
        super(lNum);
    }

    static Constant parse(Scanner s) {
        Constant c = null;

        enterParser("constant");

        if(s.curToken.kind == TokenKind.nameToken){
            c = Name.parse(s);
        }else if(s.curToken.kind == TokenKind.intValToken){
            c = NumericLiteral.parse(s);
        } else if (s.curToken.kind == TokenKind.charValToken) {
            c = CharLiteral.parse(s);
        }else{
            Main.error("Error with Constant. Found a " + s.curToken.kind.toString() + " token with a " + s.nextToken.kind.toString() + " after it");
        }

        leaveParser("constant");

        return c;
    }

    abstract public void check(Block curScope, Library library);

    @Override
    public String identify() {
        return super.identify();
    }
}
