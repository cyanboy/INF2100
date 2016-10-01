package parser;

import scanner.Scanner;
import scanner.TokenKind;

/**
 * Created by cyanboy on 20/10/15.
 */
public class RangeType extends Type {
    public RangeType(int n) {
        super(n);
    }

    Constant from;
    Constant to;

    static RangeType parse(Scanner s) {
        enterParser("range-type");
        RangeType r = new RangeType(s.curLineNum());

        r.from = Constant.parse(s);
        s.skip(TokenKind.rangeToken);
        r.to = Constant.parse(s);

        leaveParser("range-type");
        return r;
    }

    @Override
    void check(Block curScope, Library library) {
        from.check(curScope, library);
        to.check(curScope, library);
    }

    @Override
    public String identify() {
        return super.identify();
    }
}