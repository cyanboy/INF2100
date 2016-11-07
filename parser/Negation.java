package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 21/10/15.
 */
public class Negation extends Factor {
    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("not ");
        factor.prettyPrint();
    }

    Factor factor;

    public Negation(int n) {
        super(n);
    }

    static Negation parse(Scanner s) {
        enterParser("negation");
        Negation n = new Negation(s.curLineNum());
        s.skip(TokenKind.notToken);

        n.factor = Factor.parse(s);

        leaveParser("negation");
        return n;
    }

    @Override
    public void genCode(CodeFile codeFile) {
        factor.genCode(codeFile);
        codeFile.genInstr("", "xorl", "$1, %eax", "");
    }

    @Override
    public void check(Block curScope, Library library) {

        factor.check(curScope, library);
        factor.type.checkType(library.booleanType, "not", this, "Factor isn't a boolean, can't be negated");
    }
}
