package parser;

import main.CodeFile;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 21/10/15.
 */
public class Term extends PascalSyntax {
    public Term(int n) {
        super(n);
    }

    types.Type type;

    public List<Factor> factors = new ArrayList<>();
    List<FactorOpr> factorOprs = new ArrayList<>();

    static Term parse(Scanner s) {
        enterParser("term");
        Term t = new Term(s.curLineNum());

        t.factors.add(Factor.parse(s));

        while (s.curToken.kind.isFactorOpr()) {
            t.factorOprs.add(FactorOpr.parse(s));
            t.factors.add(Factor.parse(s));
        }

        leaveParser("term");
        return t;
    }

    void check(Block curScope, Library library) {

        for (Factor f : factors) {
            f.check(curScope, library);
        }

        for (FactorOpr f : factorOprs) {
            f.check(curScope, library);
        }

        type = factors.get(0).type;

        if (!factorOprs.isEmpty()) {

            for (int i = 0; i < factorOprs.size(); i += 2) {
                FactorOpr op = factorOprs.get(i);
                if (op.op == TokenKind.andToken) {
                    type.checkType(library.booleanType, op.op.toString(), this, "Type mismatch, expected Boolean");
                }
                type.checkType(factors.get(i).type, op.op.toString(), this, "Type mismatch");
                type.checkType(factors.get(i + 1).type, op.op.toString(), this, "Type mismatch");
            }

        }

    }

    public void genCode(CodeFile f) {
        if (!factors.isEmpty()) {

            for (int i = 0; i < factors.size(); ++i) {
                factors.get(i).genCode(f);

                if (i > 0)
                    factorOprs.get(i - 1).genCode(f);

                if (i < factors.size() - 1)
                    f.genInstr("", "pushl", "%eax", "");
            }

        } else {
            factors.get(0).genCode(f);
        }
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        factors.get(0).prettyPrint();

        if (!factorOprs.isEmpty()) {

            for (int i = 0; i < factorOprs.size(); i++) {
                factorOprs.get(i).prettyPrint();
                factors.get(i + 1).prettyPrint();
            }

        }
    }
}
