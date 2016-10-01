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
    TypeDeclPart typeDeclPart;
    VarDeclPart varDeclPart;

    StatementList statementList;

    List<FuncDecl> funcDeclList = new ArrayList<>();
    List<ProcDecl> procDeclList = new ArrayList<>();

    public void genCode(CodeFile codeFile) {
        int off = 32;

        if (varDeclPart != null) {
            for (VarDecl v : varDeclPart.variables) {
                v.declLevel = declLevel;
                off += 4;
                v.declOffset = off;
            }
        }


        if (!funcDeclList.isEmpty()) {
            for (FuncDecl fun : funcDeclList) {

                fun.genCode(codeFile);
            }
        }
        if (!procDeclList.isEmpty()) {
            for (ProcDecl proc : procDeclList) {
                proc.codeGen(codeFile);
            }
        }
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


    void addDecl(String id, PascalDecl d) {
        if (decls.containsKey(id))
            d.error(id + " declared twice in same block!");
        decls.put(id, d);
    }

    void check(Block curScope, Library lib) {
        if (constDeclPart != null) {
            for (ConstDecl cd : constDeclPart.constants) {
                addDecl(cd.name, cd);
            }
        }

        if (typeDeclPart != null) {
            for (TypeDecl td : typeDeclPart.typeDecls) {
                addDecl(td.name, td);
            }
        }


        if (varDeclPart != null) {
            for (VarDecl vd : varDeclPart.variables) {
                vd.check(curScope,lib);
                addDecl(vd.name, vd);
            }
        }

        int lvl = declLevel;
        for (FuncDecl fd : funcDeclList) {
            addDecl(fd.name, fd);
            fd.declLevel = ++lvl;
            fd.check(curScope, lib);

        }

        for (ProcDecl pd : procDeclList) {
            addDecl(pd.name, pd);
            pd.declLevel = ++lvl;
            pd.check(curScope, lib);
        }

        if (statementList != null) {
            for (Statement st : statementList.statements) {
                st.check(curScope, lib);
            }
        }
    }

    PascalDecl findDecl(String id, PascalSyntax where) {
        PascalDecl d = decls.get(id);
        if (d != null) {
            Main.log.noteBinding(id, where, d);
            return d; }
        if (outerScope != null)
            return outerScope.findDecl(id,where);
        where.error("Name " + id + " is unknown!");
        return null;  // Required by the Java compiler.
    }

    public String identify() {
        return "";
    }

}
