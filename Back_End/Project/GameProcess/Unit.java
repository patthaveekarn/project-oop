package Back_End.Project.GameProcess;

import Back_End.Project.Parse.Parser;
import Back_End.Project.Parse.ProcessParse;
import Back_End.Project.Player.ConfigPlayer;
import Back_End.Project.Player.Player;
import Back_End.Project.Region.ConfigRegion;
import Back_End.Project.Region.Position;
import Back_End.Project.Region.Region;
import Back_End.Project.Statement.AssignmentStatementNode;
import Back_End.Project.Statement.Node.ExecuteNode;
import Back_End.Project.Tokenizer.GrammarTokenizer;
import Back_End.Project.Tokenizer.Tokenizer;

import java.util.*;

public final class Unit {
    private static int id = 1;

    private static Map<String, Long> evaluate(List<ExecuteNode> lists) {
        Map<String, Long> bindings = new HashMap<>();
        for(ExecuteNode list : lists){
            if(!(list instanceof AssignmentStatementNode)){
                throw new GameException.InvalidConfiguration();
            }
            ((AssignmentStatementNode) list).execute(bindings);
        }
        return bindings;
    }

    private static class ConfigurationParse extends ProcessParse {
        public ConfigurationParse(Tokenizer tkz) {
            super(tkz);
        }

        @Override
        public List<ExecuteNode> parse(){
            List<ExecuteNode> defaultValue = new ArrayList<>(10);
            parseStatements(defaultValue);
            return defaultValue;
        }
    }

    public static Configuration loadConfiguration(String config){
        Parser parser = new ConfigurationParse(new GrammarTokenizer(config));
        List<ExecuteNode> lists = parser.parse();
        Map<String, Long> bindings = evaluate(lists);
        Configuration configuration = new Configuration() {
            @Override
            public long rows() {
                return bindings.getOrDefault("rows", 50L);
            }

            @Override
            public long cols() {
                return bindings.getOrDefault("cols", 50L);
            }

            @Override
            public long init_plan_min() {
                return bindings.getOrDefault("init_plan_min", 5L);
            }

            @Override
            public long init_plan_sec() {
                return bindings.getOrDefault("init_plan_sec", 0L);
            }

            @Override
            public long init_budget() {
                return bindings.getOrDefault("init_budget", 10000L);
            }

            @Override
            public long init_center_dep() {
                return bindings.getOrDefault("init_center_dep", 100L);
            }

            @Override
            public long plan_rev_min() {
                return bindings.getOrDefault("plan_rev_min", 50L);
            }

            @Override
            public long plan_rev_sec() {
                return bindings.getOrDefault("plan_rev_sec", 0L);
            }

            @Override
            public long rev_cost() {
                return bindings.getOrDefault("rev_cost", 100L);
            }

            @Override
            public long max_dep() {
                return bindings.getOrDefault("max_dep", 1000000L);
            }

            @Override
            public double interest_pct(long turn, long deposit) {
                return (long) (bindings.getOrDefault("interest_pct", 0L) * Math.log10(deposit) * Math.log(turn));
            }
        };
        if(configuration.init_plan_sec() >= 60) throw new GameException.InvalidConfiguration();
        if(configuration.plan_rev_sec() >= 60) throw new GameException.InvalidConfiguration();
        return configuration;
    }

    public static Configuration defaultConfig(){
        return loadConfiguration("""
                rows=10
                cols=10
                init_plan_min=5
                init_plan_sec=0
                init_budget=10000
                init_center_dep=100
                plan_rev_min=30
                plan_rev_sec=0
                rev_cost=100
                max_dep=1000000
                interest_pct=5
                """);
    }

    public static Region setIdleRegion(List<Region> territory){
        Random rand = new Random();
        Region region;
        do{
            int indexOfRegion = rand.nextInt(territory.size());
            region = territory.get(indexOfRegion);
        }while(region.getOwner() != null);
        return region;
    }

    // create new territory from given configuration
    public static List<Region> createTerritory(Configuration config){
        List<Region> territory = new ArrayList<>((int) (config.rows() * config.cols()));

        for(int r = 0; r < config.rows(); r++){
            for(int c = 0; c < config.cols(); c++){
                territory.add(new ConfigRegion(Position.of(c, r), config.max_dep()));
            }
        }
        return territory;
    }

    // create new a player
    public static Player createPlayer(Configuration config, String name, List<Region> territory){
        Region region = setIdleRegion(territory);
        Player player = new ConfigPlayer(id++, name, config.init_center_dep());
        region.setCityCenter(player);
        region.updateDeposit(config.init_budget());
        return player;
    }

    // create new game instance
    public static Game createGame(String nameP1, String nameP2){
        Configuration config = defaultConfig();
        List<Region> territory = createTerritory(config);
        Player P1 = createPlayer(config, nameP1, territory);
        Player P2 = createPlayer(config, nameP2, territory);
        return new ConfigGame(P1, P2, config, territory);
    }

    // create new game with specific configuration
    public static Game createCustom(String configuration, String nameP1, String nameP2){
        Configuration config = loadConfiguration(configuration);
        List<Region> territory = createTerritory(config);
        Player P1 = createPlayer(config, nameP1, territory);
        Player P2 = createPlayer(config, nameP2, territory);
        return new ConfigGame(P1, P2, config, territory);
    }
}
