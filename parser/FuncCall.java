package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cyanboy on 21/10/15.
 */
public class FuncCall extends Factor {
    public FuncCall(int n) {
        super(n);
    }

    @Override
    public String identify() {
        return super.identify();
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);

        if (!expressions.isEmpty()) {
            Main.log.prettyPrint("(");

            Iterator<Expression> it = expressions.iterator();

            while (it.hasNext()) {
                Expression exp = it.next();
                exp.prettyPrint();
                if (it.hasNext()) {
                    Main.log.prettyPrint(", ");
                }
            }

            Main.log.prettyPrint(")");
        }


    }

    String name;
    List<Expression> expressions = new ArrayList<>();
    PascalDecl decl;
    types.Type type;

    static FuncCall parse(Scanner s) {
        enterParser("func call");
        FuncCall f = new FuncCall(s.curLineNum());

        f.name = s.curToken.id;
        s.skip(TokenKind.nameToken);

        if (s.curToken.kind == TokenKind.leftParToken) {

            s.skip(TokenKind.leftParToken);
            f.expressions.add(Expression.parse(s));

            while (s.curToken.kind == TokenKind.commaToken) {
                s.skip(TokenKind.commaToken);
                f.expressions.add(Expression.parse(s));
            }

            s.skip(TokenKind.rightParToken);
        }

        leaveParser("func call");
        return f;
    }

    @Override
    public void genCode(CodeFile codeFile) {
    }

    @Override
    public void check(Block curScope, Library library) {
        decl = curScope.findDecl(name, this);
        decl.checkWhetherFunction(this);
        expressions.forEach(exp -> exp.check(curScope, library));
    }
}
