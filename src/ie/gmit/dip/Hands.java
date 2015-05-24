package ie.gmit.dip;


/**
 * class Hands - Each player in Game of Hearts will be given a hand of cards. 
 * Cards are added and removed from the hand as the cards are dealt and played. You can also
 * use this class to check if player has any cards left in their hand and if they contain the
 * two of clubs. This is important because the player with the two of clubs starts the first round
 * in a new game.  
 * @author Mairead Manifold
 * @version Ver 1
 */
public class Hands
{
	//instance variables

	int[][] hand = null;// 2d array for each player's hand of card (Rows: suit, Columns: value)

	Card cardCP = new Card();



	//constructor
	public Hands(){
		hand = new int[4][13];//(Rows: suit[4], Columns: value[13])
	}


	/**
	 * @return the hand as a 2-d array
	 */
	public int[][] getHand() {// to get the players hand 
		return hand;
	}

	/**
	 * [0]-Spades; [1]-Hearts; [2]-Diamonds; [3]-clubs.
	 */

	public void addCard(Card c)
	{
		int suit=c.getSuit();
		int value=c.getValue();

		//the values to be stored in the hand array
		hand [suit][value-2]=value;// Rows: suit and column: value 2 to 14 where 14 is an ace. 

		// test printout to check that card has been added. 
		// System.out.println("Your hand after adding: "+value+" of "+cardCP.getSuitAsString(suit)+" is now " );

	}

	// When cards are played and/or passed	
	public void removeCard(int suit, int value){
		//Remove card by changing the hand array value to zero
		hand [suit][value-2]= 0;// Rows refers to suit and column refers to its numerical or picture card value 

		//test print out of hand after adding a new card
		//System.out.println("Hand after removing card: "+ value+" of "+cardCP.getSuitAsString(suit)+" is now ");

	}




	public void printHand() {// print out the cards to console , called by the human player 
		String suitString = null;
		for (int row = 0; row < hand.length; row++) {
			//int tempSuit = row; 
			if (row ==0)
				suitString = "Spades";
			else if (row == 1)
				suitString = "Hearts";
			if (row ==2)
				suitString = "Diamonds";
			else if (row == 3)
				suitString = "Clubs";
			
			System.out.println();
			
			System.out.println("Cards in "+suitString+" suit");
			for (int col=0;col<hand[row].length; col++)
				if (hand[row][col]!=0)
					System.out.println("I have "+hand[row][col]+" of "+suitString);
		}///end of col loop
	}// end of row loop printHand method

	public void printHand(int suit) {// print out the cards to console , called by the human player 
		String suitString = cardCP.getSuitAsString(suit);

		System.out.println("Cards in "+suitString+" suit");
		for (int col=0;col<hand[suit].length; col++)
			if (hand[suit][col]!=0)
				System.out.println("I have "+hand[suit][col]+" of "+suitString);
	}// end of suit parameter printHand method




	// checks to see if player has 2 clubs as the player with the 2 of clubs starts the game
	public boolean hasTwoOfClubs() {
		if (hand[3][0] != 0) {
			return true;// the hand has the the 2 of clubs
		} else {
			return false;
		}
	}//end of hasTwoOfClubs method

	public boolean hasNoTrump(int trump){
		for (int col=0;col<hand[trump].length; col++){
			if (hand[trump][col] != 0)
				return false;
		}// end of for loop	
		return true;
	}



	//checks to see that player has a card to play game
	public boolean hasCardsLeft() { 
		if(size() != 0){// your hand is empty
			return true;
		} else{
			return false;	
		}
	}//end of hasCardsLeft method

	// returns the number of cards 
	public int size() {
		int counter = 0;// variable store the size of array hand
		for (int row = 0; row < hand.length; row++) {
			for (int col=0;col<hand[row].length; col++)
				if (hand[row][col] != 0)
					counter++;// incrementing
		}// end of for loop
		//System.out.println();
		//System.out.println("The size of the hand is: "+ counter);
		//System.out.println();
		return counter;

	}// end of size method
}




