package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 20/10/15.
 */
public class SimpleExpression extends PascalSyntax {
    public SimpleExpression(int lNum) {
        super(lNum);
    }

    types.Type type;

    PrefixOpr prefixOpr;

    List<Term> term = new ArrayList<>();
    List<TermOpr> termOpr = new ArrayList<>();

    static SimpleExpression parse(Scanner s) {
        enterParser("simple expr");
        SimpleExpression se = new SimpleExpression(s.curLineNum());

        if (s.curToken.kind.isPrefixOpr()) {
            se.prefixOpr = PrefixOpr.parse(s);
        }

        se.term.add(Term.parse(s));

        while (s.curToken.kind.isTermOpr()) {
            se.termOpr.add(TermOpr.parse(s));
            se.term.add(Term.parse(s));
        }

        leaveParser("simple expr");
        return se;
    }

    @Override
    public void check(Block curScope, Library library) {
        if (prefixOpr != null)
            prefixOpr.check(curScope, library);

        term.forEach(t -> t.check(curScope, library));

        termOpr.forEach(tOp -> tOp.check(curScope, library));
    }

    public void genCode(CodeFile codeFile) {

    }

    @Override
    public String identify() {
        return "<simple expr> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        if(prefixOpr != null)
            prefixOpr.prettyPrint();

        term.get(0).prettyPrint();

        if(termOpr != null) {
            
            for(int i = 0; i < termOpr.size(); i++) {
                termOpr.get(i).prettyPrint();
                term.get(i+1).prettyPrint();
            }
        }
    }
}
