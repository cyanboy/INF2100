package parser;


/**
 * Created by cyanboy on 16/10/2016.
 */
public class Library extends Block {
    Library(int lnum) {
        super(lnum);
        addDecl("write", new FuncDecl("write", lnum));
        addDecl("eol", new ConstDecl("eol", lnum));
        addDecl("true", new ConstDecl("true", lnum));
        addDecl("false", new ConstDecl("false", lnum));
    }

    @Override
    public void addDecl(String id, PascalDecl d) {
        super.addDecl(id, d);
    }
}
