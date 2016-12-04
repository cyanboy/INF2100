package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;

/**
 * Created by cyanboy on 02/11/15.
 */
public class CharLiteral extends UnsignedConstant {
    char constant;
//    types.Type type = new CharType();

    public CharLiteral(int lNum) {
        super(lNum);
    }

    @Override
    void prettyPrint() {
        if (constant == '\'')
            Main.log.prettyPrint("''" + Character.toString(constant) + "'");
        else
            Main.log.prettyPrint("'" + Character.toString(constant) + "'");
    }

    static CharLiteral parse(Scanner s){
        enterParser("char literal");
        CharLiteral sl = new CharLiteral(s.curLineNum());

        sl.constant = s.curToken.charVal;

        s.skip(TokenKind.charValToken);

        leaveParser("char literal");
        return sl;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        // cast to int
        codeFile.genInstr("", "movl", "$" + (int) constant + ",%eax", "");
    }

    @Override
    public void check(Block curScope, Library library) {
        type = library.charType;
    }

    @Override
    public String identify() {
        return "<char literal> on line " + lineNum;
    }

}
