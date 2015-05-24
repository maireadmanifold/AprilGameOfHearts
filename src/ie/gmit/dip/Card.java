package ie.gmit.dip;


/**
 * This class creates all the cards that can be found in a deck of playing cards
 * 
 * @author Mairead Manifold
 * @version Ver 1
 */
public class Card
{


	public final static int SPADES = 0;   // Codes for the 4 suits
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;

	public final static int ACE = 14;     // Codes for ranking the non-numeric cards.
	public final static int JACK = 11;    //    
	public final static int QUEEN = 12;   //   
	public final static int KING = 13;

	// The suit cannot be changed after it has been created
	private  int suit=0;

	//  The value of the cards cannot be changed it has been created
	private int value=0;

	// to store card points
	private int points=0;



	/**
	 * Creates a card with a specified suit and value.
	 * @param value the value of the new card.  For a regular card,
	 * the value must be in the range 1 through 13, with 14 representing an Ace.
	 * You can use the constants Card.ACE, Card.JACK, Card.QUEEN, and Card.KING.  
	 * @param suit the suit of the new card.  This must be one of the values
	 * Card.SPADES, Card.HEARTS, Card.DIAMONDS or Card.CLUBS.
	 * @throws IllegalArgumentException if the parameter values are not in the
	 * permissible ranges
	 */



	public Card() {// default constructor

	}

	public Card(int suit, int value) {

		if (suit != SPADES && suit != HEARTS && suit != DIAMONDS && suit != CLUBS)

			throw new IllegalArgumentException("Illegal playing card suit");

		else{
			this.value = value;
			this.suit = suit; 

			if (suit==1) {// all Hearts get 1 point
				this.setPoints(1);
			}
			if (suit==0 && value==12) {// Queen of Spades gets 13 points
				this.setPoints(13);				
			}// end of last if

		}// end of else


	}// end of Card constructor that uses the suit and value parameters. 


	/*Returns card.
	 * @returns the suit, which is one of the constants Card.SPADES, 
	 * Card.HEARTS, Card.DIAMONDS, Card.CLUBS
	 * and returns the value of this card, which is one of the numbers 1 through 13.
	 */

	public Card getCard(int suit, int value){
		this.suit = suit;
		this.value = value;
		return this;
	}
	
	//
	public Card getNewCard(int suit, int value){
		Card c = new Card();
		c.suit = suit;
		c.value = value;
		return c;
	}

	public void setSuit(int s){
		this.suit=s;
	}

	public int getSuit() {
		return suit;
	}

	/* Returns the value of this card.
	 * @return the value, which is one of the numbers 1 through 13.
	 */

	public void setValue(int v){
		this.value=v;
	}

	public int getValue() {
		return value;
	}

	/**
	 * Returns a String representation of the card's suit.
	 * @return one of the strings "Spades", "Hearts", "Diamonds", "Clubs".
	 */

	public String getSuitAsString(int suit2) {// switches suit toString
		switch ( suit2 ) {
		case SPADES:   return "Spades";
		//case HEARTS:   return "Hearts";
		case DIAMONDS: return "Diamonds";
		case CLUBS:    return "Clubs";
		default:  return "Hearts";
		}
		//	return null;
	}

	/**
	 * Returns a String representation of the card's value.
	 * @return for a regular card, one of the strings "Ace", "2",
	 * "3", ..., "10", "Jack", "Queen", or "King".  
	 */
	public String getValueAsString() {

		switch ( value ) {
		case 14:   return "Ace";
		case 2:   return "2";
		case 3:   return "3";
		case 4:   return "4";
		case 5:   return "5";
		case 6:   return "6";
		case 7:   return "7";
		case 8:   return "8";
		case 9:   return "9";
		case 10:  return "10";
		case 11:  return "Jack";
		case 12:  return "Queen";
		default:  return "King";
		}
	}



	/**
	 * Returns a string representation of this card, including both
	 * its suit and its value.  Sample return values
	 * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades",
	 * "Joker", "Joker #2"
	 */
	
	public String toString() {// return the card itself as a String

		return this.getValueAsString() + " of " + this.getSuitAsString(suit);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
