package Back_End.Project.Parse;

import Back_End.Project.Statement.Node.ExecuteNode;

import java.util.List;

public interface Parser {
    List<ExecuteNode> parse();
}
