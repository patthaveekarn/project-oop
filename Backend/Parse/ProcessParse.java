package Backend.Parse;
import Backend.Statement.*;
import Backend.Statement.Node.*;
import Backend.Tokenizer.Tokenizer;
import java.util.*;


public class ProcessParse implements Parser{
    protected final Tokenizer tkz;
    // List string of commands
    private final List<String> commands = Arrays.stream(
            new String[]{"done", "relocate", "move", "invest", "collect", "shoot"}
    ).toList();
    // List string of reserved
    private final List<String> reserved = Arrays.stream(
            new String[]{"collect", "done", "down", "downleft", "downright", "else", "if", "invest", "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft", "upright", "while"}
    ).toList();

    public ProcessParse(Tokenizer tkz) {
        if(!tkz.hasNextToken()){
            throw new SyntaxError.StateRequire(tkz.getNewline());
        }
        this.tkz = tkz;
    }

    @Override
    public List<ExecuteNode> parse(){
        List<ExecuteNode> doState = parsePlan();

        if(tkz.hasNextToken()){
            throw new NodeException.LeftoverToken(tkz.peek());
        }
        return doState;
    }

    // Plan → Statement+
    private List<ExecuteNode> parsePlan(){
        List<ExecuteNode> plan = new ArrayList<>();
        plan.add(parseStatement());
        parseStatements(plan);
        return plan;
    }

    // Statement → Command | BlockStatement | IfStatement | WhileStatement
    public void parseStatements(List<ExecuteNode> statements){
        while(!tkz.peek("}") && tkz.hasNextToken()){
            statements.add(parseStatement());
        }
    }

    protected ExecuteNode parseStatement(){
        if(tkz.peek("if")){
            return parseIfStatement();
        }else if(tkz.peek("while")){
            return parseWhileStatement();
        }else if(tkz.peek("{")){
            return parseBlockStatement();
        }else{
            return parseCommand();
        }
    }

    private ExecuteNode parseBlockStatement(){
        List<ExecuteNode> statements = new ArrayList<>();
        tkz.consume("{");
        parseStatements(statements);
        tkz.consume("}");
        return new BlockStatementNode(statements);
    }

    private ExecuteNode parseWhileStatement(){
        tkz.consume("while");
        tkz.consume("(");
        ExpressionNode expressionNode = parseExpression();
        tkz.consume(")");
        ExecuteNode statements = parseStatement();
        return new WhileStatementNode(expressionNode, statements);
    }

    private ExecuteNode parseIfStatement(){
        tkz.consume("if");
        tkz.consume("(");
        ExpressionNode expressionNode = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        ExecuteNode trueStatement = parseStatement();
        tkz.consume("else");
        ExecuteNode falseStatement = parseStatement();
        return new IfStatementNode(expressionNode, trueStatement, falseStatement);
    }


    private ExecuteNode parseCommand(){
        if(commands.contains(tkz.peek())){
            return parseActionCommand();
        }else{
            return parseAssignmentStatement();
        }
    }

    // AssignmentStatement → <identifier> = Expression
    private ExecuteNode parseAssignmentStatement(){
        String identifier = parseIdentifier();

        if(tkz.peek("=")){
            tkz.consume();
        }else{
            throw new SyntaxError.Command404(identifier, tkz.getNewline());
        }
        ExpressionNode expressionNode = parseExpression();
        return new AssignmentStatementNode(identifier, expressionNode);
    }

    private String parseIdentifier(){
        String identifier = tkz.consume();
        if(reserved.contains(identifier)){
            throw new SyntaxError.ReservedWord(identifier, tkz.getNewline());
        }
        return identifier;
    }

    // ActionCommand → DoneCommand | RelocateCommand | MoveCommand | RegionCommand | AttackCommand
    private ExecuteNode parseActionCommand() throws SyntaxError{
        String command = tkz.consume();

        if(command.matches(RegularExpression.DONE_REGEX)){
            return new DoneCommand();
        }else if(command.matches(RegularExpression.RELOCATE_REGEX)){
            return new RelocateCommand();
        }else if(command.matches(RegularExpression.MOVE_REGEX)){
            return parseMoveCommand();
        }else if(command.matches(RegularExpression.INVEST_REGEX)){
            return parseInvestCommand();
        }else if(command.matches(RegularExpression.COLLECT_REGEX)){
            return parseCollectCommand();
        }else if(command.matches(RegularExpression.SHOOT_REGEX)){
            return parseShootCommand();
        }else{
            throw new SyntaxError.CommandIsFail(command, tkz.getNewline());
        }
    }

    // MoveCommand → move Direction
    private ExecuteNode parseMoveCommand(){
        DirectionNode direction = parseDirection();
        return new MoveCommand(direction);
    }

    // invest Expression
    private ExecuteNode parseInvestCommand(){
        ExpressionNode expressionNode = parseExpression();
        return new InvestCommand(expressionNode);
    }

    // collect Expression
    private ExecuteNode parseCollectCommand(){
        ExpressionNode expressionNode = parseExpression();
        return new CollectCommand(expressionNode);
    }

    // AttackCommand → shoot Direction Expression
    private ExecuteNode parseShootCommand(){
        DirectionNode direction = parseDirection();
        ExpressionNode expressionNode = parseExpression();
        return new AttackCommand(direction, expressionNode);
    }

    // Direction → up|down|upleft|upright|downleft|downright
    private DirectionNode parseDirection(){
        String direction = tkz.consume();

        if(direction.matches(RegularExpression.UP_REGEX)) {
            return DirectionNode.up;
        }else if(direction.matches(RegularExpression.UPLEFT_REGEX)){
            return DirectionNode.upleft;
        }else if(direction.matches(RegularExpression.UPRIGHT_REGEX)){
            return DirectionNode.upright;
        }else if(direction.matches(RegularExpression.DOWN_REGEX)){
            return DirectionNode.down;
        }else if(direction.matches(RegularExpression.DOWNLEFT_REGEX)){
            return DirectionNode.downleft;
        }else if(direction.matches(RegularExpression.DOWNRIGHT_REGEX)){
            return DirectionNode.downright;
        }else{
            throw new SyntaxError.WrongDirection(direction, tkz.getNewline());
        }
    }

    // Expression → Expression + Term | Expression - Term | Term
    private ExpressionNode parseExpression(){
        ExpressionNode left = parseTerm();

        while(tkz.peek("+") || tkz.peek("-")) {
            String op = tkz.consume();
            ExpressionNode right = parseTerm();
            left = new BinaryArithmeticNode(left, op, right);
        }
        return left;
    }

    // Term → Term * Factor | Term / Factor | Term % Factor | Factor
    private ExpressionNode parseTerm(){
        ExpressionNode left = parseFactory();

        while(tkz.peek("*") || tkz.peek("/") || tkz.peek("%")){
            String op = tkz.consume();
            ExpressionNode right = parseFactory();
            left = new BinaryArithmeticNode(left, op, right);
        }
        return left;
    }

    // Factor → Power ^ Factor | Power
    private ExpressionNode parseFactory(){
        ExpressionNode left = parsePower();

        if(tkz.peek("^")){
            String op = tkz.consume();
            ExpressionNode right = parseFactory();
            left = new BinaryArithmeticNode(left, op, right);
        }
        return left;
    }

    // Power → <number> | <identifier> | ( Expression ) | InfoExpression
    private ExpressionNode parsePower(){
        if(Character.isDigit(tkz.peek().charAt(0))){
            return new VariableExpressionNode(Integer.parseInt(tkz.consume()));
        }else if(tkz.peek(RegularExpression.OPPONENT_REGEX)
                ||tkz.peek(RegularExpression.NEARBY_REGEX)){
            return parseInfoExpression();
        }else if(tkz.peek("(")){
            tkz.consume("(");
            ExpressionNode expressionNode = parseExpression();
            tkz.consume(")");
            return expressionNode;
        }
        return new VariableExpressionNode(tkz.consume());
    }

    // InfoExpression → opponent | nearby Direction
    private ExpressionNode parseInfoExpression(){
        if(tkz.peek(RegularExpression.OPPONENT_REGEX)){
            tkz.consume();
            return new OpponentNode();
        }else if(tkz.peek(RegularExpression.NEARBY_REGEX)){
            tkz.consume();
            DirectionNode direction = parseDirection();
            return new NearbyNode(direction);
        }else{
            throw new SyntaxError.WrongInfoExpression(tkz.peek(), tkz.getNewline());
        }
    }
}