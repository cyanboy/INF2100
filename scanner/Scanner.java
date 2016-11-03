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

    /* It's a regex from hell, but it works */
    Pattern pattern = Pattern.compile(
            "('(?:[^']|\\.|'')')|(/\\*)|(\\*/)|(:=)|(\\.\\.)|(>=)|(<>)|(<=)|(\\w+)|(\\p{Punct})"
    );

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


    private String skipComment(String commentStart) {
        String tmp = commentStart;
        String commentEnd = "";
        int lineNum = getFileLineNum();

        if (tmp.equals("/*")) {
            commentEnd = "*/";
        } else if (tmp.equals("{")) {
            commentEnd = "}";
        } else {
            Main.panic("skipComment only accepts /* or { as argument at line: " + curLineNum());
            System.exit(1);
        }

        while (true) {

            if (matcher.find()) {
                tmp = matcher.group();

                if (tmp.equals(commentEnd)) {
                    return tmp;
                }

            } else {
                readNextLine();
                if (sourceFile == null) {
                    Main.error("No ending for comment starting on line " + lineNum);
                }
                matcher = pattern.matcher(sourceLine);
            }

        }

    }

    public void readNextToken() {
        curToken = nextToken;
        nextToken = null;

        // Del 1 her:
        if (sourceLine.equals("")) {
            readNextLine();
            matcher = pattern.matcher(sourceLine);
        }

        if (sourceFile == null) {
            nextToken = new Token(eofToken, getFileLineNum());
            Main.log.noteToken(nextToken);
            return;
        }

        while (!matcher.find()) {
            readNextLine();
            matcher = pattern.matcher(sourceLine);

            if (sourceFile == null) {
                nextToken = new Token(eofToken, getFileLineNum());
                Main.log.noteToken(nextToken);
                return;
            }
        }

        String tmp = matcher.group();

        while (tmp.equals("/*") || tmp.equals("{")) {
            if (tmp.equals("/*") || tmp.equals("{")) {
                tmp = skipComment(tmp);
            }

            while (tmp.equals("*/") || tmp.equals("}")) {
                if (matcher.find()) {
                    tmp = matcher.group();
                } else {
                    readNextLine();
                    matcher = pattern.matcher(sourceLine);
                }
            }
        }


        if (tmp.startsWith("'") && tmp.endsWith("'")) {
            if (tmp.length() == 3) { //any other char
                nextToken = new Token(tmp.charAt(1), getFileLineNum());
            } else if (tmp.equals("''''")) { // single quote
                nextToken = new Token('\'', getFileLineNum());
            } else {
                Main.error(getFileLineNum(), "Illegal char literal: " + tmp);
            }
        } else if (tmp.equals(".")) {
            nextToken = new Token(dotToken, curLineNum());
            sourceFile = null; //We reached the dot. There is no point in continuing

        } else {
            TokenKind kind = getTokenKind(tmp);
            if (kind != null) {

                if (kind == intValToken) {
                    nextToken = new Token(Integer.parseInt(tmp), getFileLineNum());
                } else {
                    nextToken = new Token(kind, getFileLineNum());
                    nextToken.id = tmp.toLowerCase();
                }

            } else {
                Main.error(getFileLineNum(), "Illegal token: " + tmp);
            }
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
