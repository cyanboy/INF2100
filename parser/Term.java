package parser;

import main.CodeFile;
import scanner.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyanboy on 21/10/15.
 */
public class Term extends PascalSyntax {
    public Term(int n) {
        super(n);
    }

    public List<Factor> factors = new ArrayList<>();
    List<FactorOpr> factorOprs = new ArrayList<>();

    static Term parse(Scanner s) {
        enterParser("term");
        Term t = new Term(s.curLineNum());

        t.factors.add(Factor.parse(s));

        while(s.curToken.kind.isFactorOpr()){
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
    }

    public void genCode(CodeFile codeFile) {
        for (int i = 0; i < factors.size(); i++) {
            Factor f = factors.get(i);
            f.genCode(codeFile);
        }
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {

    }
}
