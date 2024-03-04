package Backend.Parse;
import Backend.Statement.Node.*;
import java.util.List;

public interface Parser {
    List<ExecuteNode> parse();
}
