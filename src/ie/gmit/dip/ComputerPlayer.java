package ie.gmit.dip;


/**
 * ComputerPlayer is used to manage computer managed players in the game of hearts
 * It contains the following methods: receive3Cards, playCard, getWorstThreeCards, getTwoOfClubs, 
 * & hasTwoOfClubs, hasQueenOfSpades, setPenalties, getPenalties
 * 
 * @author Mairead Manifold
 * @version Ver 1
 */
public class ComputerPlayer
{

	private int penalties; //tracks no of penalty points received after every round; 
	// penalties accumulates as rounds proceed and game is over when total penalties reaches 100


	//provides player with a hand of cards and access to a 2-d array which stores all player's cards
	Hands hand=new Hands();

	//This variable is used to return a card when the playCard method is called
	Card cardToBePlayed; 

	//used to play a card and to pass 3 worst cards to another player at beginning of game
	Card cardCP = new Card(); 


	public void receive3Cards(Card[] receivedThreeCards) {
		hand.addCard(receivedThreeCards[0]);
		hand.addCard(receivedThreeCards[1]);
		hand.addCard(receivedThreeCards[2]);
		System.out.println();
		System.out.println("A computer player received 3 cards");
		System.out.println();
	}


	public Card playCard(int suit, boolean heartsPlayed, boolean firstRound) {

		int []toBePlayed=new int[2];
		int trump= suit;
		boolean cardFound = false; // used to exit for loops when card has been chosen for play

		while (cardFound == false){



			if (firstRound && hand.getHand()[3][2-2]!=0){ //checking to see if we have 2 of clubs if we are in first round
				toBePlayed[0]=3;//i.e. clubs
				toBePlayed[1]=2-2; //2 so 2 of clubs used if it is in hand. 
				cardFound = true;

				//remove card and return cardToBePlayed if you have found a 2 of clubs in previous if statement. 
				if (cardFound==true){
					hand.removeCard(3,2);// remove card played from our hand 2 day array
					cardToBePlayed = cardCP.getNewCard(3, 2);
					return cardToBePlayed; 
				}// end of cardFound check after locating 2 of clubs.
			}// end of check for 2 of clubs


			//Only gets to here if you have not already returned a card
			// First check - look for trump card
			// The trump card dictates which suit to search through
			for (int col = 2; col < 15 ; col++) { 
				if (hand.getHand()[trump][col-2] != 0){// finds lowest trump card
					toBePlayed[0]=trump;
					toBePlayed[1]=hand.getHand()[trump][col-2];// stores value of trump suit in the toBePlayed array
					cardFound = true;
				} //end of if statement

				//remove card and return cardToBePlayed if you have found a trump card in previous if statement. 
				if (cardFound==true){
					hand.removeCard(trump,col);// remove card played from our hand 2 day array
					cardToBePlayed = cardCP.getNewCard(trump, col);
					return cardToBePlayed; 
				}// end of cardFound check after looking through trump cards.

			}// end of for loop

			// only get to here if you have not been able to find a trump card
			// Second check: if you don't have trump card - play highest heart
			for (int i = 14; i >=2; i--) {// traversing through the hearts from highest to lowest
				if (hand.getHand()[1][i-2] != 0){// looking for highest heart card
					toBePlayed[0]=1;// storing suit value in first cell of toBePlayed array: rem hearts=1
					toBePlayed[1]=hand.getHand()[1][i-2];// put highest heart value in 2 index of single d array toBePlayed 
					cardFound = true;
				}//end of if statement 

				//remove card and return cardToBePlayed if you have found a trump card in previous if statement. 
				if (cardFound==true){
					hand.removeCard(1,i);// remove card played from our hand 2 day array
					cardToBePlayed = cardCP.getNewCard(1, i);
					return cardToBePlayed; 
				}// end of if cardFound statement 

			}//end of for loop; searching for highest heart card 


			// Third check: Check all suits and choose lowest card (rem that trump and hearts suits will have already been checked)
			//so it doesn't matter if checking for lowest heart
			for (int row = 0; row < 4; row++) {// traversing the suits in the array
				for (int col2=2;col2<15; col2++){ // traversing each card in a suit
					if (hand.getHand()[row][col2-2] != 0){// looking for lowest ranking card
						toBePlayed[0]=row; // stores card's suit in toBePlayed array index position 0
						toBePlayed[1]=hand.getHand()[row][col2-2];// stores card's value in toBePlayed array index position 1
						cardFound=true;
					}// end of if check for a card within the hands array.	

					//remove card and return cardToBePlayed if you have found a card in previous if statement. 
					if (cardFound==true){
						hand.removeCard(row,col2);// remove card played from our hand 2 day array
						cardToBePlayed = cardCP.getNewCard(row, col2);
						return cardToBePlayed; 
					}// end of if cardFound
				}// end of cards within a suit for loop 
			}//end of for row loop

		}//end of while cardFound==false loop
		return cardToBePlayed;
	}// end of playCard method



	public Card[]  getWorstThreeCards() {// look for the worst three cards to pass: 
		//in order of preference, we select the following cards to give away
		//Queen of Spades 
		//Highest Hearts - 
		//all other cards, the highest of their value


		//counter to tracks finding of cards to pass to neighbour
		int worstCardsFound = 0;

		//used to return cards to caller
		Card []worstCardsArray= new Card[3];

		while(worstCardsFound < 3){
			//1st Check
			//Queen of Spades (At index 20 in the hand array)
			// check if there is a value in the Queen of Spades cell and if so, pass its suit and value to threeWorstCards array
			if (hand.hand[0][12-2] != 0) {
				worstCardsArray[0] = cardCP.getNewCard(0, 12);
				worstCardsFound++; 
				hand.removeCard(0,12);// remove card played from the hand 2-d array
			}


			//2nd Check - look for highest hearts 
			// if you have hearts give away the highest hearts
			for (int i = 14; i >=2; i--) {// through the hearts from highest to lowest
				if (hand.hand[1][i-2] != 0) {// checking to see if there is one with a value
					if (worstCardsFound==0){
						worstCardsFound++;
						worstCardsArray[0]= cardCP.getNewCard(1, i);
						hand.removeCard(1,i);// remove card played from our hand 2 day array
					}else if (worstCardsFound==1){
						worstCardsFound++;
						worstCardsArray[1]= cardCP.getNewCard(1, i);
						hand.removeCard(1,i);// remove card played from our hand 2 day array
					}else if (worstCardsFound==2){
						worstCardsFound++;
						worstCardsArray[2]= cardCP.getNewCard(1, i);
						hand.removeCard(1,i);// remove card played from our hand 2 day array
					}
					
				} //end of if statement
			}// end of for loop


			// 3rd condition: Check all suits for highest number to add to worstCard 
			for (int row = 0; row < 4; row++) {// traversing each suit within the array from 0 (spades) to 3 (clubs)
				for (int col3=2;col3<15; col3++){ // traversing all cards within a suit

					if (hand.hand[row][col3-2] != 0) {// checking to see if there is one with a value
						if (worstCardsFound==0){
							worstCardsFound++;
							worstCardsArray[0]= cardCP.getNewCard(row, col3);
							hand.removeCard(row, col3);// remove card played from our hand 2 day array
						}else if (worstCardsFound==1){
							worstCardsFound++;
							worstCardsArray[1]= cardCP.getNewCard(row, col3);
							hand.removeCard(row, col3);// remove card played from our hand 2 day array
						}else if (worstCardsFound==2){
							worstCardsFound++;
							worstCardsArray[2]= cardCP.getNewCard(row, col3);
							hand.removeCard(row, col3);// remove card played from our hand 2 day array
						} // end of checking all suits

					}//end of if statement
				}//end of col for loop
			}//end of row for loop
		}// end of while worstCardsFound loop
		// System.out.println("CP " + worstCardsArray[0]);
		// System.out.println("CP " + worstCardsArray[1]);
		// System.out.println("CP " + worstCardsArray[2]);
		return worstCardsArray;
	}//end of getWorstCard method








	public void getTwoOfClubs() {//removes two of clubs from hand by setting it to zero
		if (hasTwoOfClubs()) {
			hand.hand[3][0]=0;
		}
	}

	public boolean hasTwoOfClubs() {//checks if hand contains two of clubs
		if (hand.hand[3][0]!=0) {
			return true;
		} else {
			return false;
		}
	}



	public boolean hasQueenOfSpades() {// check to see if you have the queen of spades 
		if (hand.hand[0][12] != 0) {
			return true;
		} else {
			return false;
		}
	}


	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}


}// end of public class ComputerPlayer
