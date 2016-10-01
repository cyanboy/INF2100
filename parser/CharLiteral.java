package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 02/11/15.
 */
public class CharLiteral extends Constant {
    char constant;

    public CharLiteral(int lNum) {
        super(lNum);
    }

    static CharLiteral parse(Scanner s){
        enterParser("char-literal");
        CharLiteral sl = new CharLiteral(s.curLineNum());


        //TODO: FIX THIS SHIT

        s.readNextToken();

        leaveParser("CharLiteral");
        return sl;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        codeFile.genInstr("", "movl", "$" +
                "" + (int)constant + ",%eax", "");
    }

    @Override
    public void check(Block curScope, Library library) {
        return;
    }

}
