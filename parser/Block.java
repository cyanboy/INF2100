package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;
import types.*;

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

    public void check(Block curScope, Library lib) {
        outerScope = curScope;
        declLevel = outerScope.declLevel + 1;

        if (constDeclPart != null) {
            constDeclPart.constants.forEach(constDecl -> addDecl(constDecl.name, constDecl));
            constDeclPart.check(this, lib);
        }

        if (varDeclPart != null) {
            varDeclPart.check(this, lib);
            int counter = 0;

            for (VarDecl varDecl : varDeclPart.variables) {
                addDecl(varDecl.name, varDecl);
                if (varDecl.pType instanceof ArrayType) {
                    counter += varDecl.type.size()/4;
                    varDecl.declOffset = -32 - (counter * 4);
                } else {
                    varDecl.declOffset = -32 - (++counter * 4);
                }
            }


        }

        funcDeclList.forEach(funcDecl -> {
            addDecl(funcDecl.name, funcDecl);
            funcDecl.check(this, lib);
        });

        procDeclList.forEach(procDecl -> {
            addDecl(procDecl.name, procDecl);
            procDecl.check(this, lib);
        });

        statementList.check(this, lib);
    }

    @Override
    public void genCode(CodeFile f) {
        statementList.genCode(f);
    }

    public void addDecl(String id, PascalDecl d) {
        if (decls.containsKey(id))
            d.error(id + " declared twice in same block!");
        d.declLevel = this.declLevel;
        decls.put(id, d);
    }

    public PascalDecl findDecl(String id, PascalSyntax where) {
        PascalDecl d = decls.get(id);
        if (d != null) {
            Main.log.noteBinding(id, where, d);
            d.declLevel = this.declLevel;
            return d;
        }
        if (outerScope != null)
            return outerScope.findDecl(id,where);
        where.error("Name " + id + " is unknown!");
        return null; // Required by the Java compiler.
    }

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

        procDeclList.forEach(pd -> { pd.prettyPrint(); Main.log.prettyPrintLn(); } );
        funcDeclList.forEach(fd -> { fd.prettyPrint(); Main.log.prettyPrintLn(); } );

        Main.log.prettyPrintLn("begin");

        Main.log.prettyIndent();

        statementList.prettyPrint();

        Main.log.prettyOutdent();
        Main.log.prettyPrint("end");
    }

}
