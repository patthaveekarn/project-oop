package Backend.Parse;
import Backend.Parse.ProcessParse;
import Backend.Parse.ReadingFile;
import Backend.Statement.Node.*;
import Backend.Tokenizer.GrammarTokenizer;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestingProcessParse{
    protected ProcessParse parser;

    @Test
    public void testReadFileConstructionPlan() {
        String filePath = "Backend\\src\\constructionPlanCode.txt";
        try {
            String content = ReadingFile.readFile(filePath);

            // Perform any additional validation, e.g., checking the number of nodes
            Assertions.assertNotNull(content, "Result should not be null");
            Assertions.assertFalse(content.isEmpty(), "Result should not be empty");
        } catch (IOException e) {
            Assertions.fail("Error reading the file: " + e.getMessage());
        } catch (RuntimeException e) {
            Assertions.fail("Exception occurred while reading the file: " + e.getMessage());
        }
    }

    @Test
    public void testReadFileThenParse() {
        String filePath = "Backend\\src\\constructionPlanCode.txt";
        try {
            String content = ReadingFile .readFile(filePath);
            GrammarTokenizer tokenizer = new GrammarTokenizer(content);
            ProcessParse parser = new ProcessParse(tokenizer);
            List<ExecuteNode> result = parser.parse();

            Assertions.assertNotNull(result, "Result should not be null");
            Assertions.assertFalse(result.isEmpty(), "Result should not be empty");
            //System.out.println(result);
        } catch (IOException e) {
            Assertions.fail("Error reading the file: " + e.getMessage());
        } catch (RuntimeException e) {
            Assertions.fail("Parsing error: " + e.getMessage());
        }
    }

    @Test
    public void testParseMultiStatement(){
        String plan = """
                while (deposit) { # still our region
                  if (deposit - 100)
                  then collect (deposit / 4)  # collect 1/4 of available deposit
                  else if (budget - 25) then invest 25
                  else {}
                  if (budget - 100) then {} else done  # too poor to do anything else
                  opponentLoc = opponent
                  if (opponentLoc / 10 - 1)
                  then  # opponent afar
                    if (opponentLoc % 10 - 5) then move downleft
                    else if (opponentLoc % 10 - 4) then move down
                    else if (opponentLoc % 10 - 3) then move downright
                    else if (opponentLoc % 10 - 2) then move downright
                    else if (opponentLoc % 10 - 1) then move upright
                    else move up
                  else if (opponentLoc)
                  then  # opponent adjacent to city crew
                    if (opponentLoc % 10 - 5) then {
                      cost = 10 ^ (nearby upleft % 100 + 1)
                      if (budget - cost) then shoot upleft cost else {}
                    }
                    else if (opponentLoc % 10 - 4) then {
                      cost = 10 ^ (nearby downleft % 100 + 1)
                      if (budget - cost) then shoot downleft cost else {}
                    }
                    else if (opponentLoc % 10 - 3) then {
                      cost = 10 ^ (nearby down % 100 + 1)
                      if (budget - cost) then shoot down cost else {}
                    }
                    else if (opponentLoc % 10 - 2) then {
                      cost = 10 ^ (nearby downright % 100 + 1)
                      if (budget - cost) then shoot downright cost else {}
                    }
                    else if (opponentLoc % 10 - 1) then {
                      cost = 10 ^ (nearby upright % 100 + 1)
                      if (budget - cost) then shoot upright cost else {}
                    }
                    else {
                      cost = 10 ^ (nearby up % 100 + 1)
                      if (budget - cost) then shoot up cost else {}
                    }
                  else {  # no visible opponent; move in a random direction
                    dir = random % 6
                    if (dir - 4) then move upleft
                    else if (dir - 3) then move downleft
                    else if (dir - 2) then move down
                    else if (dir - 1) then move downright
                    else if (dir) then move upright
                    else move up
                  }
                }  # end while
                # city crew on a region belonging to nobody, so claim it
                if (budget - 1) then invest 1 else {}
                """
                ;
        parser = new ProcessParse(new GrammarTokenizer(plan));
        Assertions.assertDoesNotThrow(parser::parse);
        System.out.println(parser);
    }

    @Test
    public void testParseEmptyStatement() {
        assertThrows(SyntaxError.StateRequire.class, () -> new ProcessParse(new GrammarTokenizer(null)));
        assertThrows(SyntaxError.StateRequire.class, () -> new ProcessParse(new GrammarTokenizer("")));
    }

    @Test
    public void testParseExpression() {
        String input = "1 + 2 * (3 - 4) / 5 ^ 6";
        GrammarTokenizer tokenizer = new GrammarTokenizer(input);
        ProcessParse parser = new ProcessParse(tokenizer);
        Assertions.assertThrows(SyntaxError.Command404.class, parser::parse);
    }

    @Test
    public void testParseWrongWord() {
        String input = "money";
        GrammarTokenizer tokenizer = new GrammarTokenizer(input);
        ProcessParse parser = new ProcessParse(tokenizer);
        Assertions.assertThrows(SyntaxError.Command404.class, parser::parse, "Expected a syntax error due to the wrong word");
    }

    @Test
    public void testParseReservedWord() {
        String input = "downleft";
        GrammarTokenizer tokenizer = new GrammarTokenizer(input);
        ProcessParse parser = new ProcessParse(tokenizer);
        Assertions.assertThrows(SyntaxError.ReservedWord.class, parser::parse);
    }
}


