package Back_End.Project.Parse;

import java.util.regex.Pattern;

public class RegularExpression  {
    public static final String DIRECTION_REGEX = "up|down|upleft|upright|downleft|downright";
    public static final String UP_REGEX = "up";
    public static final String DOWN_REGEX = "down";
    public static final String UPLEFT_REGEX = "upleft";
    public static final String UPRIGHT_REGEX = "upright";
    public static final String DOWNLEFT_REGEX = "downleft";
    public static final String DOWNRIGHT_REGEX = "downright";

    // String reserved words
    public static final String RESERVEDWORD_REGEX = "collect|done|down|downleft|downright|else|if|invest|move|nearby|opponent|relocate|shoot|then|up|upleft|upright|while";

    // String infoExpression, opponent, nearby
    public static final String INFOEXPRESSION_REGEX = "InfoExpression";
    public static final String OPPONENT_REGEX = "opponent";
    public static final String NEARBY_REGEX = "nearby";

    // String action, done, move, relocate, invest, collect, shoot
    public static final String ACTION_REGEX = "done|relocate|move|invest|collect|shoot";
    public static final String REGION_REGEX = "invest|collect";
    public static final String DONE_REGEX = "done";
    public static final String MOVE_REGEX = "move";
    public static final String RELOCATE_REGEX = "relocate";
    public static final String INVEST_REGEX = "invest";
    public static final String COLLECT_REGEX = "collect";
    public static final String SHOOT_REGEX = "shoot";

    // String if, then, else
    public static final String IF_REGEX = "if";
    public static final String THEN_REGEX = "then";
    public static final String ELSE_REGEX = "else";

    // String while
    public static final String WHILE_REGEX = "while";

    // String operator, assign, parentheses
    public static final String OPERATOR_REGEX = "[-+*/%^]";
    public static final String ASSIGN_REGEX = "=";
    public static final String PARENTHESES_REGEX = "[({})]";

    // String number, variable
    public static final String NUMBER_REGEX = "([0-9]+[.]*[0-9]+)|([0-9]+)";
    public static final String VARIABLE_REGEX = "((upleft|upright|downleft|downright|done|relocate|move|invest|collect|shoot|up|down|if|then|else|while|opponent|nearby)[a-zA-Z0-9]+)|([a-zA-Z]+[a-zA-Z0-9]*)";

    // String random
    public static final String RANDOM_REGEX = "random";

    // String all
    public static final String ALL_REGEX = "([-+*/%^])|(=)|([({})])|([0-9]+[.]*[0-9]+)|([0-9]+)|((upleft|upright|downleft|downright|done|relocate|move|invest|collect|shoot|up|down|if|then|else|while|opponent|nearby)[a-zA-Z0-9]+)|(upleft|upright|downleft|downright|move|shoot|left|right|up|down|if|then|else|while|nearby)|([a-zA-Z]+[a-zA-Z0-9]*)|([^ \\r\\n]+)";

    // String split
    public static final String SPLIT_REGEX = ("([\\s]++)|(?<=[=+\\-*/%(){}^])|(?=[=+\\-*/%(){}^])");

    // Pattern direction
    public static final Pattern DIRECTION_PATTERN = Pattern.compile(DIRECTION_REGEX);
    public static final Pattern UP_PATTERN = Pattern.compile(UP_REGEX);
    public static final Pattern DOWN_PATTERN = Pattern.compile(DOWN_REGEX);
    public static final Pattern UPLEFT_PATTERN = Pattern.compile(UPLEFT_REGEX);
    public static final Pattern UPRIGHT_PATTERN = Pattern.compile(UPRIGHT_REGEX);
    public static final Pattern DOWNLEFT_PATTERN = Pattern.compile(DOWNLEFT_REGEX);
    public static final Pattern DOWNRIGHT_PATTERN = Pattern.compile(DOWNRIGHT_REGEX);

    // Pattern reserved words
    public static final Pattern RESERVEDWORD_PATTERN = Pattern.compile(RESERVEDWORD_REGEX);

    // Pattern infoExpression, opponent, nearby
    public static final Pattern INFOEXPRESSION_PATTERN = Pattern.compile(INFOEXPRESSION_REGEX);
    public static final Pattern OPPONENT_PATTERN = Pattern.compile(OPPONENT_REGEX);
    public static final Pattern NEARBY_PATTERN = Pattern.compile(NEARBY_REGEX);

    // Pattern action, done, move, relocate, invest, collect, shoot
    public static final Pattern ACTION_PATTERN = Pattern.compile(ACTION_REGEX);
    public static final Pattern REGION_PATTERN = Pattern.compile(REGION_REGEX);
    public static final Pattern DONE_PATTERN = Pattern.compile(DONE_REGEX);
    public static final Pattern MOVE_PATTERN = Pattern.compile(MOVE_REGEX);
    public static final Pattern RELOCATE_PATTERN = Pattern.compile(RELOCATE_REGEX);
    public static final Pattern INVEST_PATTERN = Pattern.compile(INVEST_REGEX);
    public static final Pattern COLLECT_PATTERN = Pattern.compile(COLLECT_REGEX);
    public static final Pattern SHOOT_PATTERN = Pattern.compile(SHOOT_REGEX);

    // Pattern if, then, else
    public static final Pattern IF_PATTERN = Pattern.compile(IF_REGEX);
    public static final Pattern THEN_PATTERN = Pattern.compile(THEN_REGEX);
    public static final Pattern ELSE_PATTERN = Pattern.compile(ELSE_REGEX);

    // Pattern while
    public static final Pattern WHILE_PATTERN = Pattern.compile(WHILE_REGEX);

    // Pattern operator, assign, parentheses
    public static final Pattern OPERATOR_PATTERN = Pattern.compile(OPERATOR_REGEX);
    public static final Pattern ASSIGN_PATTERN = Pattern.compile(ASSIGN_REGEX);
    public static final Pattern PARENTHESES_PATTERN = Pattern.compile(PARENTHESES_REGEX);

    // Pattern number, variable
    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);
    public static final Pattern VARIABLE_PATTERN = Pattern.compile(VARIABLE_REGEX);

    // Pattern random
    public static final Pattern RANDOM_PATTERN = Pattern.compile(RANDOM_REGEX);

    // Pattern all
    public static final Pattern ALL_PATTERN = Pattern.compile(ALL_REGEX);

    // Pattern split
    public static final Pattern SPLIT_PATTERN = Pattern.compile(SPLIT_REGEX);
}
