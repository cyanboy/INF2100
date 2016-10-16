package parser;

import main.CodeFile;
import scanner.Scanner;

/**
 * Created by cyanboy on 16/10/15.
 */
public class EmptyStatement extends Statement {
    public EmptyStatement(int lNum) {
        super(lNum);
    }

    @Override
    public void genCode(CodeFile codeFile) {
        return;
    }

    public static EmptyStatement parse(Scanner s) {
        enterParser("empty-statement");
        leaveParser("empty-statement");
        return new EmptyStatement(s.curLineNum());
    }

    @Override
    public void check(Block curScope, Library library) {
        return;
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {

    }
}
