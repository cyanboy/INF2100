package parser;


import main.CodeFile;
import types.*;
import types.ArrayType;
import types.Type;

/**
 * Created by cyanboy on 16/10/2016.
 */
public class Library extends Block {
    @Override
    public void genCode(CodeFile f) {

    }

    class TypeDecl extends PascalDecl {
        public TypeDecl(String id, int lNum, Type t) {
            super(id, lNum);
            type = t;
        }

        @Override
        void checkWhetherAssignable(PascalSyntax where) {}

        @Override
        void checkWhetherFunction(PascalSyntax where) {}

        @Override void checkWhetherProcedure(PascalSyntax where) {}

        @Override
        void checkWhetherValue(PascalSyntax where) {}

        @Override
        void check(Block curScope, Library lib) {}

        @Override
        public void genCode(CodeFile f) {}

        @Override
        public String identify() {
            return "<type decl> " + name + " in the library";
        }

        @Override
        void prettyPrint() {}
    }

    types.Type booleanType = new types.BoolType();
    types.Type integerType = new types.IntType();
    types.Type charType = new types.CharType();

    public Library() {
        super(-1);
        int lnum = -1;

        addDecl("write", new ProcDecl("write", lnum) {
            @Override
            public String identify() {
                return "<proc decl> " + this.name + " in the library";
            }
        });

        addDecl("eol", new ConstDecl("eol", lnum) {
            @Override
            public String identify() {
                return "<const decl> " + this.name + " in the library";
            }
        });

        ConstDecl trueD = new ConstDecl("true", lnum) {
            @Override
            public String identify() {
                return "<const decl> " + this.name + " in the library";
            }
        };
        trueD.type = booleanType;


        ConstDecl falseD = new ConstDecl("false", lnum) {
            @Override
            public String identify() {
                return "<const decl> " + this.name + " in the library";
            }
        };
        falseD.type = booleanType;

        addDecl("true", trueD);
        addDecl("false", falseD);

        addDecl("integer", new TypeDecl("integer", lnum, integerType));
        addDecl("char", new TypeDecl("char", lnum, charType));
        addDecl("boolean", new TypeDecl("boolean", lnum, booleanType));
    }
}
