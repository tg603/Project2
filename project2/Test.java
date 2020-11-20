public class Test{
public static void main(String[] args) {
	Player<PathMyopicCol> easy = new PathMyopicColEasyPlayer();
    Player<PathMyopicCol> random = new RandomPlayer<PathMyopicCol>();
    int minSize = 10;
    int maxSize = 15;
    double density = .2;
    PositionFactory<PathMyopicCol> positionGenerator = new PathMyopicCol.PositionBuilder(minSize, maxSize, density);
    Referee ref = new Referee(easy, random, positionGenerator);
    ref.call();
}
}