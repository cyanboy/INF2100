package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cyanboy on 12/10/15.
 */
public class Block extends PascalSyntax {
    HashMap<String, PascalDecl> decls = new HashMap<>();
    public Block outerScope = null;

    public int declLevel;

    Program context;
    ConstDeclPart constDeclPart;
    VarDeclPart varDeclPart;

    StatementList statementList;

    List<FuncDecl> funcDeclList = new ArrayList<>();
    List<ProcDecl> procDeclList = new ArrayList<>();

    public static Block parse(Scanner s) {
        enterParser("block");
        Block b = new Block(s.curLineNum());


        if (s.curToken.kind == TokenKind.constToken) {
            b.constDeclPart = ConstDeclPart.parse(s);
        }

        if (s.curToken.kind == TokenKind.varToken) {
            b.varDeclPart = VarDeclPart.parse(s);
        }

        while (s.curToken.kind == TokenKind.functionToken || s.curToken.kind == TokenKind.procedureToken) {

            if (s.curToken.kind == TokenKind.functionToken) {
                b.funcDeclList.add(FuncDecl.parse(s));
            } else {
                b.procDeclList.add(ProcDecl.parse(s));
            }
        }

        s.skip(TokenKind.beginToken);

        b.statementList = StatementList.parse(s);

        s.skip(TokenKind.endToken);
        leaveParser("block");
        return b;
    }

    Block(int n) {
        super(n);
    }

    public String identify() {
        return "<block> on line " + lineNum;
    }

    public void prettyPrint() {
        if (constDeclPart != null) {
            constDeclPart.prettyPrint();
        }

        if (varDeclPart != null) {
            varDeclPart.prettyPrint();
        }

        procDeclList.forEach(ProcDecl::prettyPrint);
        funcDeclList.forEach(FuncDecl::prettyPrint);

        Main.log.prettyPrintLn("begin");
        Main.log.prettyIndent();
        statementList.prettyPrint();
        Main.log.prettyOutdent();
        Main.log.prettyPrint("end");
    }

}
