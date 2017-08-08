package checkers;


import java.util.Vector;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class CheckerMoveTest {
	
	int[][] testBoard;
	int[] testMove;
	public CheckerMoveTest() {
	}

	@Before
	public void setUp() {
		testBoard = new int[8][8];
		testMove = new int[4];
	}

	@Test
	public void testGetIndex(){
		//Expected to return the array [1, 2]
		int[] tstArr = new int[2];
		tstArr[0] = 1; tstArr[1] = 2;
		int[] chckArr = checkers.CheckerMove.getIndex(55, 105);
		assertTrue(tstArr[0] == chckArr[0] && tstArr[1] == chckArr[1]);
		//Add numbers too high, expected to return the array [0, 0]
		chckArr = checkers.CheckerMove.getIndex(500, 4550);
		assertTrue(chckArr[0] == 0 && chckArr[1] == 0);
		//Add numbers too low, expected to return the array [0, 0]
		chckArr = checkers.CheckerMove.getIndex(-100, -1);
		assertTrue(chckArr[0] == 0 && chckArr[1] == 0);
	}
	
	@Test
	public void testColour(){
		//Should return the value of 1, the value of a red colored piece
		assertEquals(1, checkers.CheckerMove.colour(1));
		assertEquals(1, checkers.CheckerMove.colour(3));
		//Should return the value of 2, the value of a yellow colored piece
		assertEquals(2, checkers.CheckerMove.colour(2));
		assertEquals(2, checkers.CheckerMove.colour(4));
		//Should return the value of 0, an empty square
		assertEquals(0, checkers.CheckerMove.colour(0));
		//Should return the value of 0, by default, on an improper pass
		assertEquals(0, checkers.CheckerMove.colour(100));
	}
	
	//This tests the canCapture method that takes in an x and a y coordinate
	//Not the one that takes in a piece that should be moved
	@Test
	public void testCanCapture1(){
		testBoard[0][7] = 2; testBoard[1][6] = 1;
		assertTrue(checkers.CheckerMove.canCapture(testBoard, 2));
		testBoard[0][7] = 0;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 1));
		testBoard[0][7] = 2;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 1));
	}
	
	//This tests the canCapture method that takes in an integer that represents
	//the player who's turn it is
	@Test
	public void testCanCapture2(){
		testBoard[7][0] = 1; testBoard[6][1] = 2;
		assertTrue(checkers.CheckerMove.canCapture(testBoard, 7, 0));
		testBoard[5][2] = 2;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 7, 0));
		testBoard[0][7] = 2; testBoard[1][6] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 1, 6));
		testBoard[6][1] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 7, 0));
		testBoard[5][2] = 3; testBoard[6][1] = 2;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 5, 2));
		testBoard[7][0] = 0;
		assertTrue(checkers.CheckerMove.canCapture(testBoard, 5, 2));
		testBoard[6][1] = 3; testBoard[7][0] = 4;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 6, 1));
		testBoard[3][4] = 3; testBoard[4][3] = 1; testBoard[4][5] = 1; testBoard[2][3] = 1; testBoard[2][5] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 3, 4));
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				testBoard[i][j] = 0;
			}
		}
		testBoard[3][4] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 3, 4));
		testBoard[3][4] = 2;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 3, 4));
		testBoard[3][4] = 3;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 3, 4));
		testBoard[3][4] = 4;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 3, 4));
		testBoard[3][4] = 0;
		
		testBoard[0][7] = 2; testBoard[1][6] = 1;
		assertTrue(checkers.CheckerMove.canCapture(testBoard, 0, 7));
		testBoard[2][5] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 0, 7));
		testBoard[6][1] = 2; testBoard[7][0] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 1, 6));
		testBoard[2][5] = 0; testBoard[1][6] = 2;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 0, 7));
		testBoard[2][5] = 4; testBoard[1][6] = 1; testBoard[0][7] = 0;
		assertTrue(checkers.CheckerMove.canCapture(testBoard, 2, 5));
		testBoard[0][7] = 1;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 2, 5));
		testBoard[1][6] = 4;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 1, 6));
		testBoard[0][7] = 4;
		assertFalse(checkers.CheckerMove.canCapture(testBoard, 0, 7));
		testBoard[2][5] = 0; testBoard[1][6] = 0; testBoard[0][7] = 0; testBoard[7][0] = 0; testBoard[6][1] = 0;
	}
	
	
	@Test
	public void testApplymove(){
		int[][] endBoard = new int[8][8];
		testBoard[7][0] = 1;
		endBoard[6][1] = 1;
		checkers.CheckerMove.ApplyMove(testBoard, 7, 0, 6, 1);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1]);
		
		testBoard[7][0] = 1; testBoard[6][1] = 2;
		endBoard[6][1] = 0; endBoard[5][2] = 1;
		checkers.CheckerMove.ApplyMove(testBoard, 7, 0, 5, 2);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1] && endBoard[5][2] == testBoard[5][2]);
		
		testBoard[7][0] = 1; testBoard[6][1] = 0; testBoard[5][2] = 0;
		endBoard[7][0] = 1; endBoard[6][1] = 0; endBoard[5][2] = 0;
		checkers.CheckerMove.ApplyMove(testBoard, 7, 0, 8, 1);
		assertTrue(endBoard[7][0] == testBoard[7][0]);
		
		testBoard[6][1] = 1;
		endBoard[6][1] = 1;
		checkers.CheckerMove.ApplyMove(testBoard, 7, 0, 6, 1);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1]);
		
		testBoard[6][1] = 2; testBoard[4][3] = 2;
		endBoard[6][1] = 0; endBoard[7][0] = 0; endBoard[3][4] = 1;
		checkers.CheckerMove.ApplyMove(testBoard, 7, 0, 5, 2);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1]);
	}
	
	@Test
	public void testIsMoveLegal(){
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 8, 0, 7, 1, 1), 2);
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 8, 1, 1), 2);
		testBoard[7][0] = 1;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 6, 1, 1), 1);
		testBoard[6][1] = 2;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 6, 1, 1), 2);
		testBoard[6][1] = 1;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 6, 1, 1), 2);
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 5, 2, 1), 2);
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 7, 1, 1), 2);
		testBoard[6][1] = 2;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 5, 2, 1), 3);
		testBoard[5][2] = 1;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 5, 2, 1), 2);
		testBoard[5][2] = 2;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 5, 2, 1), 2);
		testBoard[5][2] = 0; testBoard[4][3] = 2;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 7, 0, 5, 2, 1), 3);
		testBoard[7][0] = 0; testBoard[5][0] = 1;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 5, 0, 4, 1, 1), 2);
		testBoard[7][0] = 1; testBoard[5][0] = 0; testBoard[1][0] = 1;
		assertEquals(checkers.CheckerMove.isMoveLegal(testBoard, 1, 0, 2, 1, 1), 2);
		testBoard[7][0] = 0; testBoard[1][0] = 0; testBoard[6][1] = 0; testBoard[4][3] = 0;
	}
	
	@Test
	public void testIsWalkLegal(){
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 8, 0, 7, 1), 2);
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 7, 0, 8, 1), 2);
		testBoard[7][0] = 1; testBoard[6][1] = 2;
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 7, 0, 6, 1), 2);
		testBoard[6][1] = 1;
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 7, 0, 6, 1), 2);
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 7, 0, 5, 2), 2);
		testBoard[7][0] = 0;
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 6, 1, 7, 0), 2);
		testBoard[1][6] = 2;
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 1, 6, 7, 0), 2);
		assertEquals(checkers.CheckerMove.isWalkLegal(testBoard, 1, 6, 0, 5), 1);
		testBoard[1][6] = 0; testBoard[7][0] = 0; testBoard[6][1] = 0;
	}
	
	@Test
	public void testCanWalk(){
		testBoard[7][0] = 1; testBoard[0][7] = 2;
		assertTrue(checkers.CheckerMove.canWalk(testBoard, 7, 0));
		assertTrue(checkers.CheckerMove.canWalk(testBoard, 0, 7));
		
		testBoard[6][1] = 2; testBoard[1][6] = 1;
		assertFalse(checkers.CheckerMove.canWalk(testBoard, 7, 0));
		assertFalse(checkers.CheckerMove.canWalk(testBoard, 0, 7));
		
		testBoard[1][2] = 3; testBoard[5][6] = 4;
		assertTrue(checkers.CheckerMove.canWalk(testBoard, 1, 2));
		assertTrue(checkers.CheckerMove.canWalk(testBoard, 5, 6));
		
		testBoard[7][0] = 3; testBoard[0][7] = 4;
		assertFalse(checkers.CheckerMove.canWalk(testBoard, 7, 0));
		assertFalse(checkers.CheckerMove.canWalk(testBoard, 0, 7));
		testBoard[7][0] = 0; testBoard[0][7] = 0; testBoard[1][2] = 0; testBoard[5][6] = 0; testBoard[1][6] = 0; testBoard[6][1] = 0;
	}
	
	@Test
	public void testGenerateMoves(){
		assertTrue(checkers.CheckerMove.generateMoves(testBoard, 1).isEmpty());
		testBoard[0][7] = 2; testBoard[0][2] = 4;
		assertTrue(checkers.CheckerMove.generateMoves(testBoard, 1).isEmpty());
		testBoard[0][2] = 0; testBoard[1][6] = 1; testBoard[2][5] = 1;
		assertTrue(checkers.CheckerMove.generateMoves(testBoard, 2).isEmpty());
		
		//This creates a basic starting board
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
				testBoard[i][j] = 0;

			for (int j=0; j<3; j++)
			    if ((i+j) % 2 == 1)		//If i + j is odd, then it can be placed
				    testBoard[i][j] =  1;

			for (int j=5; j<8; j++)
			    if ((i+j) % 2 == 1)
				    testBoard[i][j] =  2;
		}
		//Create the movesList for the board
		Vector movesList = checkers.CheckerMove.generateMoves(testBoard, 1);
		//Populate a different Vector with the expected results
		int[] testArr0 = new int[4];
		testArr0[0] = 7; testArr0[1] = 2; testArr0[2] = 6; testArr0[3] = 3;
		int[] testArr1 = new int[4];
		testArr1[0] = 5; testArr1[1] = 2; testArr1[2] = 4; testArr1[3] = 3;
		int[] testArr2 = new int[4];
		testArr2[0] = 5; testArr2[1] = 2; testArr2[2] = 6; testArr2[3] = 3;
		int[] testArr3 = new int[4];
		testArr3[0] = 3; testArr3[1] = 2; testArr3[2] = 2; testArr3[3] = 3;
		int[] testArr4 = new int[4];
		testArr4[0] = 3; testArr4[1] = 2; testArr4[2] = 4; testArr4[3] = 3;
		int[] testArr5 = new int[4];
		testArr5[0] = 1; testArr5[1] = 2; testArr5[2] = 0; testArr5[3] = 3;
		int[] testArr6 = new int[4];
		testArr6[0] = 1; testArr6[1] = 2; testArr6[2] = 2; testArr6[3] = 3;
		//Test Arrays for equivalence
		int[] movesArr0 = (int[])movesList.get(0);
		int[] movesArr1 = (int[])movesList.get(1);
		int[] movesArr2 = (int[])movesList.get(2);
		int[] movesArr3 = (int[])movesList.get(3);
		int[] movesArr4 = (int[])movesList.get(4);
		int[] movesArr5 = (int[])movesList.get(5);
		int[] movesArr6 = (int[])movesList.get(6);
		//Boolean values created to store comparisons
		boolean true0 = true; boolean true1 = true; boolean true2 = true; boolean true3 = true;
			boolean true4 = true; boolean true5 = true; boolean true6 = true;
		for(int i = 0; i < 4; i++){
			true0 = (true0 && testArr0[i] == movesArr0[i]);
			true1 = (true1 && testArr1[i] == movesArr1[i]);
			true2 = (true2 && testArr2[i] == movesArr2[i]);
			true3 = (true3 && testArr3[i] == movesArr3[i]);
			true4 = (true4 && testArr4[i] == movesArr4[i]);
			true5 = (true5 && testArr5[i] == movesArr5[i]);
			true6 = (true6 && testArr6[i] == movesArr6[i]);
		}	
		assertTrue(true0 && true1 && true2 && true3 && true4 && true5 && true6);
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				testBoard[i][j] = 0;
			}
		}
	}
	
	@Test
	public void testMoveComputer(){
		//A red checker in the corner, moving to an open spot
		testBoard[7][0] = 1;	//Red checker in the top right corner
		int[][] endBoard = new int[8][8];
		endBoard[6][1] = 1;	//Red checker one move away from the corner
		testMove[0] = 7; testMove[1] = 0; testMove[2] = 6; testMove[3] = 1;	//Move it diagonally downwards
		checkers.CheckerMove.moveComputer(testBoard, testMove);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1]);
		
		//A red checker jumps a yellow piece
		testBoard[7][0] = 1; testBoard[6][1] = 2;
		endBoard[7][0] = 0; endBoard[6][1] = 0; endBoard[5][2] = 1;
		testMove[0] = 7; testMove[1] = 0; testMove[2] = 5; testMove[3] = 2;
		checkers.CheckerMove.moveComputer(testBoard, testMove);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1] && endBoard[5][2] == testBoard[5][2]);
		
		//A red checker moves out of bounds
		testBoard[7][0] = 1; testBoard[6][1] = 0;
		endBoard[7][0] = 1; endBoard[6][1] = 0; endBoard[5][2] = 0;
		testMove[0] = 7; testMove[1] = 0; testMove[2] = 8; testMove[3] = 1;
		checkers.CheckerMove.moveComputer(testBoard, testMove);
		assertTrue(endBoard[7][0] == testBoard[7][0] && endBoard[6][1] == testBoard[6][1]);
		
		//A move is started from an empty spot
		testMove[0] = 6; testMove[1] = 1; testMove[2] = 5; testMove[3] = 2;
		checkers.CheckerMove.moveComputer(testBoard, testMove);
		assertTrue(endBoard[5][2] == 0);
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				testBoard[i][j] = 0;
			}
		}
	}
}
