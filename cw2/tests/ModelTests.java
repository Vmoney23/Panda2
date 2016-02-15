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

	  public void playInModel(Move move) {
          play(move);
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
	public void testCorrectPieceIsReturned() throws Exception {
		Set<Piece> pieces = new HashSet<Piece>();
		Piece piece1 = new Piece(Colour.Red, 3, 5);
		Piece piece2 = new Piece(Colour.White, 1, 1);
		Piece piece3 = new Piece(Colour.Red, 7, 4);
		pieces.add(piece1);
		pieces.add(piece2);
		pieces.add(piece3);

		DraughtsModel model = new DraughtsModel("Test", null, Colour.Red, pieces);
		assertEquals("The correct piece (1) should be returned", piece1, model.getPiece(3, 5));
		assertEquals("The correct piece (2) should be returned", piece2, model.getPiece(1, 1));
		assertEquals("The correct piece (3) should be returned", piece3, model.getPiece(7, 4));
	}

	// A piece is removed if the move is greater than 1 square
	@Test
	public void testRemovePieceUpdatesCorrectly() throws Exception {
		TestModel model = new TestModel("Test", new TestPlayer());
		Point position = new Point(2,1);
		Point destination1 = new Point(3,0);
		Point destination2 = new Point(4,1);
		Point destination3 = new Point(6,3);

		assertEquals("RemovePiece works correctly, no removal", false, model.removePieceInModel(position, destination1));
		assertEquals("RemovePiece works correctly, no removal", false, model.removePieceInModel(destination1, destination2));
		assertEquals("RemovePiece works correctly, removal", true, model.removePieceInModel(destination2, destination3));
	}

	@Test
	public void testPlay() throws Exception {
		Set<Piece> pieces = new HashSet<Piece>();
        Piece piece = new Piece(Colour.Red, 1, 1);
        pieces.add(piece);
        TestModel model = new TestModel("Test", new TestPlayer(), Colour.Red, pieces);
        Move move = new Move(piece, 2, 2);
        model.playInModel(move);

		assertEquals("Play move works correctly, piece 1 moved to new spot", piece, model.getPiece(2,2));
		assertEquals("Play move works correctly, piece 1 no longer in original position", null, model.getPiece(1,1));
	}

	@Test
	public void testJump() throws Exception {
		Set<Piece> pieces = new HashSet<Piece>();
        Piece piece1 = new Piece(Colour.Red, 1, 1);
        Piece piece2 = new Piece(Colour.White, 2, 2);
        pieces.add(piece1);
        pieces.add(piece2);
        TestModel model = new TestModel("Test", new TestPlayer(), Colour.Red, pieces);
        Move move = new Move(piece1, 3, 3);
        model.playInModel(move);

		assertEquals("Jump works correctly, piece moved to new spot", piece1, model.getPiece(3,3));
		assertEquals("Jump works correctly, piece jumped over has been removed", null, model.getPiece(2,2));
	}

}
