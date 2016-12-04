package parser;

import main.CodeFile;
import main.Main;
import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 19/10/15.
 */
public class FuncDecl extends PascalDecl {
    Block body;
    ParamDeclList declList;
    TypeName typeName;

    public FuncDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override
    void checkWhetherAssignable(PascalSyntax where) {
        // OK -- returverdi
    }

    @Override
    void checkWhetherFunction(PascalSyntax where) {
        // OKIDOKI
    }

    @Override
    void checkWhetherProcedure(PascalSyntax where) {
        where.error("A function is not a procedure");

    }

    @Override
    void checkWhetherValue(PascalSyntax where) {
        //OK?
    }

    static FuncDecl parse(Scanner s) {
        enterParser("func decl");
        s.skip(TokenKind.functionToken);
        FuncDecl f = new FuncDecl(s.curToken.id, s.curLineNum());
        s.skip(TokenKind.nameToken);


        if(s.curToken.kind == TokenKind.leftParToken){
            f.declList = ParamDeclList.parse(s);
        }

        s.skip(TokenKind.colonToken);
        f.typeName = TypeName.parse(s);
        s.skip(TokenKind.semicolonToken);
        f.body = Block.parse(s);
        s.skip(TokenKind.semicolonToken);
        leaveParser("func decl");

        return f;
    }


    @Override
    public void genCode(CodeFile f) {
        String funcLabel = f.getLabel(name);

        for (FuncDecl fd : body.funcDeclList)
            fd.genCode(f);

        for (ProcDecl pd : body.procDeclList)
            pd.genCode(f);

        f.genInstr(String.format("func$%s", funcLabel), "", "", "");
        int decls = 0;

        if (body.varDeclPart != null)
            decls = 4 * body.varDeclPart.variables.size();

        f.genInstr("", "enter", String.format("$%d, $%d", 32 + decls, body.declLevel), "");
        body.genCode(f);

        f.genInstr("", "movl", "-32(%ebp), %eax", "");

        f.genInstr("", "leave", "", "");
        f.genInstr("", "ret", "", "");
    }

    void check(Block curScope, Library library) {
        if (declList != null)
            declList.check(body, library);

        typeName.check(curScope, library);
        type = typeName.type;
        body.check(curScope, library);
    }

    @Override
    public String identify() {
        return "<func decl> at line " + lineNum;
    }

    @Override
    void prettyPrint() {
        Main.log.prettyPrint("function " + name + " ");

        if (declList!=null)
            declList.prettyPrint();

        Main.log.prettyPrint(": ");

        typeName.prettyPrint();

        Main.log.prettyPrintLn(";");
        body.prettyPrint();
        Main.log.prettyPrintLn(";");

    }
}
