package ie.gmit.dip;

import java.util.Scanner;


/**
 * HumanPlayer class contains the following methods: playCard, getWorstThreeCards, receive3Cards
 * & hasTwoOfClubs, getTwoOfClubs, hasQueenOfSpades, setPenalties, getPenalties.
 * 
 * @author Mairead Manifold
 * @version Ver 1
 */
public class HumanPlayer {


	//provides player with a hand of cards
	Hands hand=new Hands();

	//tracks no of penalty points received after every round; 
	//accumulates as rounds proceed and game over when it reaches 100
	private int penalties; 	

	//used to create card items that are used in the return statements in the playCard and getThreeWorstCards methods
	Card cardCP = new Card(); //used to play a card and to pass 3 worst cards to another player

	// Open the Scanner
	Scanner input = new Scanner(System.in);

	//the GameOfHearts class calls this method
	public Card playCard(int suit, boolean heartsPlayed, boolean firstRound) {

		int trump=suit;

		Card cardToBePlayed = null;
		System.out.println();
		System.out.println("Please choose a card from your hand and you need to choose a trump card if you have one");
		System.out.println();
		System.out.println("Please note that trumps are " + cardCP.getSuitAsString(trump));
		System.out.println();
		
		System.out.print("Press any key to view the cards in your hand: ");
		input.nextLine();		

		System.out.println();
		//show human player his current cards
		hand.printHand();
		System.out.println();
		System.out.println("Please input 1 to 4 for card suit you want to play: 1 for Spades, 2 for Hearts, 3 for Diamonds and 4 for clubs");
		// accept suit selection from human placer
		int hpsuit = input.nextInt();

		// Adjust for zero index
		hpsuit--;

		// checks that human player has played a trump card if there is a trump card in the player's hand
		if (hpsuit != trump && hand.hasNoTrump(trump)==false){
			for (int col = 2; col < 15 ; col++) {// Player needs to play a trump card if he/she has one
				while (hand.getHand()[trump][col-2] == 0){// finds a trump card in player's hand
					//tell user he has to play a trump card 
					System.out.println("The current trump is "+cardCP.getSuitAsString(trump));
					System.out.println("Please input a suit corresponding to current trump: 1 for Spades, 2 for Hearts, 3 for Diamonds and 4 for clubs");
					hpsuit = input.nextInt();
					// Adjust for zero index
					hpsuit--;

				}// end of if getHand shows we have a trump card
			}//end of for loop going through all cards in the trump suit

		}// end of if i != trump

		System.out.println("You have correctly chosen a suit: "+cardCP.getSuitAsString(hpsuit));

		System.out.println();
		
		if (hand.hasNoTrump(trump)==false){ // you have trump cards
			System.out.println("You have the following trump cards");
			hand.printHand(trump);
		}else
		{// you don't have any trump cards
			System.out.println("Your cards in this suit are:");
			hand.printHand(hpsuit);
		}

		System.out.println();

		//finding out which card number human player is choosing
		System.out.println("Please input 1 to 14 for card you want to play; choose 11 for Jack, 12 for Queen, 13 for King and 14 for Ace");

		int hpvalue = input.nextInt();

		cardToBePlayed  = cardCP.getNewCard(hpsuit, hpvalue); 	

		hand.removeCard(hpsuit, hpvalue);// remove card played from our hand 2 day array
		return cardToBePlayed; 
	}//end of playCard method

	public void receive3Cards(Card[] receivedThreeCards) {
		hand.addCard(receivedThreeCards[0]);
		hand.addCard(receivedThreeCards[1]);
		hand.addCard(receivedThreeCards[2]);
	}


	public Card[] getWorstThreeCards() {

		//counter to track number of cards selected by human player
		int worstCardsFound = 0;

		//used to return cards to caller
		Card []worstCardsArray= new Card[3];

		System.out.println();
		System.out.println("Human Player; you need to choose three cards to pass to computer player cp1");
		System.out.println("It is always best to get rid of hearts and Queen (card 12) of Spades ");
		System.out.println();

		while(worstCardsFound < 3){

			int hpValue = 2; // used to find card number for chosen suit

			System.out.println();
			System.out.println("Please choose a card from your hand to pass to your neighbour.");
			System.out.println();
			System.out.println("Your cards are: ");
			
			System.out.println();
			System.out.print("Press any key to display all your cards . . . ");
			System.out.println();
			input.nextLine();
			hand.printHand();
			System.out.println();

			System.out.println("Please input 1 to 4 for card suit you want to pass: 1 for Spades, 2 for Hearts, 3 for Diamonds and 4 for clubs");
			System.out.println();
			int chooseSuit = input.nextInt();
			int hpSuit = chooseSuit - 1; // correct for zero index
			if (chooseSuit >= 1 && chooseSuit <= 4){
				System.out.println("You have chosen "+cardCP.getSuitAsString(hpSuit));
				System.out.println();
				System.out.println("You have the current cards in this suit");
				System.out.println();
				hand.printHand(hpSuit);
				System.out.println();

				System.out.println("Please input 2 to 14 for card numbers; 11 for Jack, 12 for Queen, 13 for King and 14 for Ace");
				hpValue = input.nextInt();

			}else
				System.out.println("You have not chosen a number between 1 and 4");


			//check that player has this card and if so put it in the worstCardsArray
			if (hand.hand[hpSuit][hpValue-2] != 0){
				worstCardsFound++;
				if (worstCardsFound == 1){
					worstCardsArray[0] = cardCP.getNewCard(hpSuit, hpValue);
					hand.removeCard(hpSuit, hpValue);
				} else if (worstCardsFound == 2){
					worstCardsArray[1] = cardCP.getNewCard(hpSuit, hpValue);
					hand.removeCard(hpSuit, hpValue);
				} else {
					worstCardsArray[2]=cardCP.getNewCard(hpSuit, hpValue);
					hand.removeCard(hpSuit, hpValue);
				}
			}else{
				System.out.println();
				System.out.println("You do not have this card, please try again");
				System.out.println();
			}//end of if card is in hand
			System.out.println();
			System.out.println("You have chosen "+worstCardsFound+" cards to pass");
			System.out.println();
		}//end of while worstCardsFound < 3  
		System.out.println();
		System.out.println("You have finished choosing 3 cards to pass to your left.");
		System.out.println();
		return worstCardsArray;
	}//end of getWorstThreeCards() method



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

}


