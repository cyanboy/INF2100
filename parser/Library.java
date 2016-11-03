package parser;


/**
 * Created by cyanboy on 16/10/2016.
 */
public class Library extends Block {
    class TypeDecl extends PascalDecl {

        public TypeDecl(String id, int lNum) {
            super(id, lNum);
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
        public String identify() {
            return "<type decl> " + name + " in the library";
        }

        @Override
        void prettyPrint() {}
    }
    public Library(int lnum) {
        super(lnum);

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
        addDecl("true", new ConstDecl("true", lnum) {
            @Override
            public String identify() {
                return "<const decl> " + this.name + " in the library";
            }
        });


        addDecl("false", new ConstDecl("false", lnum) {
            @Override
            public String identify() {
                return "<const decl> " + this.name + " in the library";
            }
        });

        addDecl("integer", new TypeDecl("integer", lnum));
        addDecl("char", new TypeDecl("char", lnum));
        addDecl("boolean", new TypeDecl("boolean", lnum));
    }
}
