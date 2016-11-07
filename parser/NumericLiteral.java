package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;

/**
 * Created by cyanboy on 20/10/15.
 */
public class NumericLiteral extends UnsignedConstant {
    NumericLiteral(int lNum) {
        super(lNum);
    }

    int val;

    static NumericLiteral parse(Scanner s) {
        enterParser("numeric literal");
        NumericLiteral nl = new NumericLiteral(s.curLineNum());

        s.test(TokenKind.intValToken);
        nl.val = s.curToken.intVal;
        s.readNextToken();

        leaveParser("numeric literal");
        return nl;
    }

    @Override
    public void check(Block curScope, Library library) {
        type = library.integerType;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        codeFile.genInstr("", "movl", "$" + val + ",%eax", "");
    }

    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(Integer.toString(val));
    }
}
