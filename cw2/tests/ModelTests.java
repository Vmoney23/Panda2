import draughts.*;

import java.awt.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ModelTests {

    private class TestPlayer implements Player {

        @Override
        public Move notify(Set<Move> moves) {
            if (moves.iterator().hasNext()) return moves.iterator().next();
            return null;
        }

    }

    public class TestModel extends DraughtsModel {

      public TestModel(String gameName, Player player, Colour currentPlayer, Set<Piece> pieces) {
          super(gameName, player, currentPlayer, pieces);
      }

      public TestModel(String gameName, Player player) {
          super(gameName, player);
      }

      public boolean removePieceInModel(Point position, Point destination) {
          return removePiece(position, destination);
      }

      public void turnInModel() {
          turn();
      }

    }

    @Test
    public void testGameNameIsCorrect() throws Exception {
        DraughtsModel model = new DraughtsModel("Test", null);

        assertEquals("The game name should be the same as the one passed in", "Test", model.getGameName());
    }

    @Test
    public void testCurrentPlayerIsRedAtStartOfGame() throws Exception {
        DraughtsModel model = new DraughtsModel("Game", null);

        assertEquals("The red player should be the current player at the beginning of the game.", Colour.Red, model.getCurrentPlayer());
    }

    @Test
    public void testCurrentPlayerUpdatesCorrectly() throws Exception {
        TestModel model = new TestModel("Test", new TestPlayer());

        assertEquals("The current player should be red initially", Colour.Red, model.getCurrentPlayer());

        model.turnInModel();
        assertEquals("The current player should be white after one turn", Colour.White, model.getCurrentPlayer());

        model.turnInModel();
        assertEquals("The current player should be red after two turns", Colour.Red, model.getCurrentPlayer());
    }

	@Test
	public void testCorrectPieceIsReturned() {
		Set<Piece> pieces = new HashSet<Piece>();
		Piece piece1 = new Piece(Colour.Red, 3, 5);
		Piece piece2 = new Piece(Colour.White, 1, 1);
		Piece piece3 = new Piece(Colour.Red, 7, 4);
		pieces.add(piece1);
		pieces.add(piece2);
		pieces.add(piece3);

		DraughtsModel model = new DraughtsModel("Test", null, Colour.Red, pieces);
		assertEquals("The correct piece should be returned", piece1, model.getPiece(3, 5));
		assertEquals("The correct piece should be returned", piece2, model.getPiece(1, 1));
		assertEquals("The correct piece should be returned", piece3, model.getPiece(7, 4));
	}

	@Test
	public void testRemovePieceUpdatesCorrectly() {
		TestModel model = new TestModel("Test", new TestPlayer());
		Point position1 = new Point(2,1);
		Point destination1 = new Point(3,0);
		Point position2 = new Point(3,0);
		Point destination2 = new Point(4,1);
		Point position3 = new Point(4,1);
		Point destination3 = new Point(6,3);

		assertEquals("RemovePiece works correctly", false, model.removePieceInModel(position1, destination1));
		assertEquals("RemovePiece works correctly", false, model.removePieceInModel(position2, destination2));
		assertEquals("RemovePiece works correctly", true, model.removePieceInModel(position3, destination3));
	}

}
