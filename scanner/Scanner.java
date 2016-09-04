package scanner;

import main.Main;

import static scanner.TokenKind.*;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner {
    public Token curToken = null, nextToken = null;

    private LineNumberReader sourceFile = null;
    private String sourceFileName, sourceLine = "";

    Pattern old = Pattern.compile(
            "(;)|(,)|(\\+)|(-)|(\')|(\\[|\\])|(=)|(\\{|\\})|(/\\*)|(\\.\\.?)|(\\*/?)|(:=?)|(\\w+)|(\\p{Punct})"
    );

    Pattern pattern = Pattern.compile("(/\\*)|(\\*/)|(:=)|(\\.\\.)|(>=)|(<>)|(<=)|(\\w+)|(\\p{Punct})");
    Matcher matcher = null;

    private int sourcePos = 0;

    public Scanner(String fileName) {
        sourceFileName = fileName;
        try {
            sourceFile = new LineNumberReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            Main.error("Cannot read " + fileName + "!");
        }

        readNextToken();
        readNextToken();
    }


    public String identify() {
        return "Scanner reading " + sourceFileName;
    }


    public int curLineNum() {
        return curToken.lineNum;
    }


    private void error(String message) {
        Main.error("Scanner error on " +
                (curLineNum() < 0 ? "last line" : "line " + curLineNum()) +
                ": " + message);
    }


    public void readNextToken() {
        curToken = nextToken;
        nextToken = null;

        // Del 1 her:
        if (sourceLine.equals("")) {
            readNextLine();
            matcher = pattern.matcher(sourceLine);
        }


        while (!sourceLine.equals("")) {
            while (matcher.find()) {
                System.out.println(matcher.group());
                if (matcher.hitEnd()) {
                    readNextLine();
                    matcher = pattern.matcher(sourceLine);
                }
            }
            readNextLine();
            matcher = pattern.matcher(sourceLine);
        }

        Main.log.noteToken(nextToken);
    }


    private void readNextLine() {
        if (sourceFile != null) {
            try {
                sourceLine = sourceFile.readLine();
                if (sourceLine == null) {
                    sourceFile.close();
                    sourceFile = null;
                    sourceLine = "";
                } else {
                    sourceLine += " ";
                }
                sourcePos = 0;
            } catch (IOException e) {
                Main.error("Scanner error: unspecified I/O error!");
            }
        }
        if (sourceFile != null)
            Main.log.noteSourceLine(getFileLineNum(), sourceLine);
    }


    private int getFileLineNum() {
        return (sourceFile != null ? sourceFile.getLineNumber() : 0);
    }


    // Character test utilities:

    private boolean isLetterAZ(char c) {
        return 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z';
    }


    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }


    // Parser tests:

    public void test(TokenKind t) {
        if (curToken.kind != t)
            testError(t.toString());
    }

    public void testError(String message) {
        Main.error(curLineNum(),
                "Expected a " + message +
                        " but found a " + curToken.kind + "!");
    }

    public void skip(TokenKind t) {
        test(t);
        readNextToken();
    }
}
