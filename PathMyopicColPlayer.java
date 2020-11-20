public class PathMyopicColPlayer extends Player<PathMyopicCol>{

		
	public PathMyopicColPlayer(){
		//no state necessary
	}

	public String toString(){
		//returning name of my player
		return "TG";
	}
	public PathMyopicCol getMove(PathMyopicCol position, int playerId){
			PureLinkedList<Integer> game = position.getPath();
			//PureLinkedList<Integer> options = game.getPath();
		if (CombinatorialGame.LEFT == playerId){
			/*
			if(game.getFirst() == PathMyopicCol.UNCOLORED){
				game.set(0, CombinatorialGame.LEFT);
				return new PathMyopicCol(game);
			}
			*/
			for(int i = 0; i <= game.length(); i++){
				game.get(i);
				if(game.get(i) == PathMyopicCol.UNCOLORED){
					game.set(i, CombinatorialGame.LEFT);
					return new PathMyopicCol(game);
				}
				else{
					i++;
				}
			}
				//return new PathMyopicCol(CombinatorialGame.LEFT);
				return new PathMyopicCol(game);
		}
		if(CombinatorialGame.RIGHT == playerId){
		return new PathMyopicCol(game);
		}
		return null;
	}
}
