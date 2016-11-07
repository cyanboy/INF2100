package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cyanboy on 16/10/15.
 */
public class ProcCall extends Statement {
    public ProcCall(int lNum) {
        super(lNum);
    }

    String name;
    List<Expression> expressions = new ArrayList<>();
    PascalDecl decl;

    public static ProcCall parse(Scanner s) {
        enterParser("proc call");

        ProcCall p = new ProcCall(s.curLineNum());
        p.name = s.curToken.id;
        s.skip(TokenKind.nameToken);

        if (s.curToken.kind == TokenKind.leftParToken) {

            s.skip(TokenKind.leftParToken);
            p.expressions.add(Expression.parse(s));

            while (s.curToken.kind == TokenKind.commaToken) {
                s.skip(TokenKind.commaToken);
                p.expressions.add(Expression.parse(s));
            }

            s.skip(TokenKind.rightParToken);
        }

        leaveParser("proc call");
        return p;
    }

    @Override
    public void genCode(CodeFile codeFile) {

    }

    @Override
    public void check(Block curScope, Library library) {
        decl = curScope.findDecl(name, this);
        decl.checkWhetherProcedure(this);

        expressions.forEach(expression -> expression.check(curScope, library));

        if (name.equals("write")) {
            expressions.forEach(e -> {
                if (e.type instanceof types.ArrayType) {
                    this.error("write can't print array");
                }
            });

        } else if(expressions.isEmpty()) {
            if (((ProcDecl) decl).declList != null)
                this.error("Too many parameters in call on " + name);

        } else if (expressions.size() != ((ProcDecl) decl).declList.parameters.size()) {
            this.error("Too many parameters in call on " + name);
        } else {
            for (int i = 0; i < expressions.size(); i++) {
                Expression exp = expressions.get(i);
                ParamDecl pd = ((ProcDecl) decl).declList.parameters.get(i);
                pd.type.checkType(exp.type, "parameter", this, "Parameter type mismatch");
            }
        }
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint(name);
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
