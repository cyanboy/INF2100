package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;
import types.Type;

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
        term.forEach(t -> t.check(curScope, library));
        termOpr.forEach(tOp -> tOp.check(curScope, library));

        if (prefixOpr != null) {
            prefixOpr.check(curScope, library);
            term.get(0).type.checkType(library.integerType, prefixOpr.op.toString(), this, "expected integer");
            type = library.integerType;
        } else {
            type = term.get(0).type;
        }

        if(!termOpr.isEmpty()) {

            for(int i = 0; i < termOpr.size(); i++) {
                String op = termOpr.get(i).op.toString();
                Type type2 = term.get(i+1).type;

                if (op.equals("or")) {
                    type.checkType(library.booleanType, op, this, "Expected boolean");
                }

                type2.checkType(type, op, this, "Type mismatch");

            }
        }
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

        if(!termOpr.isEmpty()) {
            
            for(int i = 0; i < termOpr.size(); i++) {
                termOpr.get(i).prettyPrint();
                term.get(i+1).prettyPrint();
            }
        }
    }
}
