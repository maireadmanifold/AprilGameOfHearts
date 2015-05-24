package ie.gmit.dip;


/**
 * Constructs a regular 52-card deck.  Initially, the cards
 * are in a sorted order.  The shuffle() method can be called to
 * randomize the order.  (Note that "new Deck()" is equivalent
 * to "new Deck(false)".)

 * @author Mairead Manifold
 * @version Ver 1
 */
public class Deck
{
	// instance variables 

	Card[] deck; 
	
	private int cardsUsed;//Keeps track of the number of cards that have been dealt from
	//the deck so far.



	//constructor
	public Deck() {
		deck = new Card[52];
		int counter = 0; // How many elements(cards) have been created so far.
		for ( int suit = 0; suit <= 3; suit++ ) {
			for ( int value = 2; value <= 14; value++ ) {
				deck[counter] = new Card(suit,value);
				counter++;  // should be 52 when all suits and their respective cards have been created
			}
		}
	}// end of constructor Deck(); 

	public Card getCard(int suit, int value){
		return null;

	}




	/**
	 * Put all the used cards back into the deck (if any), and
	 * shuffle the deck into a random order.
	 */
	public void shuffle() {// mixing cards randomly 
		for ( int i = deck.length-1; i > 0; i-- ) {// starting at the max number of cards
			int randomCard = (int)(Math.random()*(i+1));
			Card temp = deck[i];// i= 51 down to 0
			deck[i] = deck[randomCard];
			deck[randomCard] = temp;
		}
		cardsUsed = 0;// The cards have been used yet
	}

	/**
	 * As cards are dealt from the deck, the number of cards left
	 * decreases.  This function returns the number of cards that
	 * are still left in the deck.  The return value would be
	 * 52  when the deck is first created or after the deck has been
	 * shuffled.  It decreases by 1 each time the dealCard() method
	 * is called.
	 */
	public int cardsLeft() {
		return deck.length - cardsUsed;
	}

	
	
	/**
	 * Checks to see if there are any more cards left in the deck
	 */
	public boolean isDeckEmpty() { 
		if(cardsUsed == deck.length){// The dealer's hand is empty
			return true;
		} else{
			return false;	
		}
	}
	/**
	 * Removes the next card from the deck and return it.  It is illegal
	 * to call this method if there are no more cards in the deck.  You can
	 * check the number of cards remaining by calling the cardsLeft() function.
	 * @return the card which is removed from the deck.
	 * @throws IllegalStateException if there are no cards left in the deck
	 */
	public Card dealCard() {
		if (isDeckEmpty())// deck is empty
			throw new IllegalStateException("No cards are left in the deck.");
		else {
			cardsUsed++;
		}

		return deck[cardsUsed - 1];//  
		// Programming note:  Cards are not literally removed from the array
		// that represents the deck.  We just keep track of how many cards
		// have been used.
	}

}
