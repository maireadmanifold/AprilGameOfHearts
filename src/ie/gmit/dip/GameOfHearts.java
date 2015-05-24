package ie.gmit.dip;

import java.util.Scanner;


/** Write a description of class GameOfHearts here.
 * 
 * @author Mairead Manifold
 * @version Ver 1
 */
public class GameOfHearts
{
	// instance variables 
	private static Deck deck;

	private static Card [] table = new Card [4]; // stores cards on table

	private boolean firstRoundFlag = true; 
	private int firstPlayer = -1;
	private int nextPlayer = -1;
	private boolean heartsPlayed=false;
	private int trump = 3; // rem 0 for spades; 1 for hearts, 2 for diamonds and 3 for clubs


	Scanner pause = new Scanner(System.in);



	private static ComputerPlayer cp1 = new ComputerPlayer();
	private static ComputerPlayer cp2 = new ComputerPlayer();
	private static ComputerPlayer cp3 = new ComputerPlayer();
	private static HumanPlayer me = new HumanPlayer();


	public static void main(String[] args) 
	{
		//0 for spades, 1 for hearts; 2 for diamonds 3 for clubs; default = 3 for clubs as 2 of clubs starts game
		GameOfHearts goh=new GameOfHearts();
		System.out.println();
		System.out.println("This is a new Game of Hearts");
		System.out.println();
		System.out.println("So Enjoy the game");
		System.out.println();
		System.out.println("The game starts with passing cards to your neighbour");
		System.out.println();


		while(cp1.getPenalties()<100 && cp2.getPenalties()<100 && cp3.getPenalties()<100 && me.getPenalties()<100)
		{


			goh.dealCards();
			goh.pass3Cards();
			goh.playCards();
			System.out.println("Exiting at cRR");
			System.exit(0);
			//goh.checkRoundResults();
			goh.checkGameResults();
		}
	}


	public void dealCards()
	{
		deck=new Deck();
		deck.shuffle();
		for(int i=0;i<13;i++)
		{
			cp1.hand.addCard(deck.deck[i]);
		}
		for(int i=13;i<26;i++)
		{
			cp2.hand.addCard(deck.deck[i]);
		}
		for(int i=26;i<39;i++)
		{
			cp3.hand.addCard(deck.deck[i]);
		}
		for(int i=39;i<52;i++)
		{
			me.hand.addCard(deck.deck[i]);
		}

		//test printing out hands of all players.
		/*System.out.println();
		System.out.println("CP1's hand is");
		System.out.println();
		cp1.hand.printHand();
		System.out.println();
		System.out.println("CP2's hand is");
		System.out.println();
		cp2.hand.printHand();
		System.out.println();
		System.out.println("CP3's hand is");
		System.out.println();
		cp3.hand.printHand();
		 */

	}// end of dealCards()





	public void pass3Cards(){

		cp1.receive3Cards(me.getWorstThreeCards());
		cp2.receive3Cards(cp1.getWorstThreeCards());
		cp3.receive3Cards(cp2.getWorstThreeCards());
		me.receive3Cards(cp3.getWorstThreeCards());
	}// end of pass3Cards()



	public void playCards() {

		while (cp1.hand.hasCardsLeft() && cp2.hand.hasCardsLeft() && cp3.hand.hasCardsLeft() && me.hand.hasCardsLeft()) {

			if(firstRoundFlag){

				if (cp1.hasTwoOfClubs())
					firstPlayer = 1;
				else if (cp2.hasTwoOfClubs())
					firstPlayer = 2;
				else if (cp3.hasTwoOfClubs())
					firstPlayer = 3;
				else
					firstPlayer = 4;
			}// end of if firstRoundFlag = true
			while (table[0]==null || table[1]==null || table[2]==null || table[3]==null){

				//setting next player based on who played last and whether it is first round or not; 
				if(firstRoundFlag){
					System.out.println("Playing first round");

					if(firstPlayer==1)
					{

						table[0]= cp1.playCard(trump, heartsPlayed,firstRoundFlag);// table[0] for cp1

						System.out.println();
						System.out.println("CP1 has played a card");
						System.out.println("CP1 has played: "+table[0]);
						System.out.print("Press any key to continue . . . ");
						pause.nextLine();
					}   

					if(firstPlayer==2)
					{
						table[1] = cp2.playCard(trump, heartsPlayed,firstRoundFlag); //table[1] for cp2

						System.out.println();
						System.out.println("CP2 has played a card");
						System.out.println("CP2 has played: "+table[1]);
						System.out.print("Press any key to continue . . . ");
						pause.nextLine();
					}
					if(firstPlayer==3)
					{
						table[2] = cp3.playCard(trump, heartsPlayed,firstRoundFlag);//table[2] for cp3

						System.out.println();
						System.out.println("CP3 has played a card");
						System.out.println("CP3 has played: "+table[2]);
						System.out.print("Press any key to continue . . . ");
						pause.nextLine();
					}
					if(firstPlayer==4){
						table[3] = me.playCard(trump, heartsPlayed,firstRoundFlag); //table[3] for me

						System.out.println("I have just played a card"+table[3]);
						System.out.print("Press any key to continue . . . ");
						pause.nextLine();
					}
					firstRoundFlag = false;

				}else{
					System.out.println("Starting new round");
					//next player after first player to play a card
					if(firstPlayer==1)
					{

						table[1] = cp2.playCard(trump, heartsPlayed, firstRoundFlag);//cp2's turn goes after cp1
						
						nextPlayer=2;

						System.out.println("CP2 has played a card");
						System.out.println("CP2 has played: "+table[1]);
						trump = table[1].getSuit();
						System.out.println("The suit is now: "+table[1].getSuitAsString(trump));
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(firstPlayer==2)
					{

						table[2] = cp3.playCard(trump, heartsPlayed, firstRoundFlag);//cp3's turn goes after cp2
						nextPlayer=3;

						System.out.println();
						System.out.println("CP3 has played a card");
						System.out.println("CP3 has played: "+table[2]);
						trump = table[2].getSuit();
						System.out.println("The suit is now: "+table[2].getSuitAsString(trump));
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(firstPlayer==3)
					{

						table[3] = me.playCard(trump, heartsPlayed, firstRoundFlag);//my turn goes after cp3's turn
						nextPlayer=4;

						System.out.println();
						System.out.println("I have played a card");
						System.out.println("I have played: "+table[3]);
						trump = table[3].getSuit();
						System.out.println("The suit is now: "+table[3].getSuitAsString(trump));
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(firstPlayer==4)
					{

						table[0] = cp1.playCard(trump, heartsPlayed, firstRoundFlag);//cp1's turn is after my turn
						nextPlayer=1;

						System.out.println();
						System.out.println("CP1 has played a card");
						System.out.println("CP1 has played: "+table[0]);
						trump = table[0].getSuit();
						System.out.println("The suit is now: "+table[0].getSuitAsString(trump));
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(nextPlayer==1)
					{

						table[1] = cp2.playCard(trump, heartsPlayed, firstRoundFlag);//cp2's turn goes after cp1
						nextPlayer=2;

						System.out.println();
						System.out.println("CP2 has played a card");
						System.out.println("CP2 has played: "+table[1]);
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(nextPlayer==2)
					{

						table[2] = cp3.playCard(trump, heartsPlayed, firstRoundFlag);//cp3's turn goes after cp2
						nextPlayer=3;

						System.out.println();
						System.out.println("CP3 has played a card");
						System.out.println("CP3 has played: "+table[2]);
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(nextPlayer==3)
					{
						table[3] = me.playCard(trump, heartsPlayed, firstRoundFlag);//my turn goes after cp3's turn
						nextPlayer=4;

						System.out.println();
						System.out.println("I have played a card");
						System.out.println("I have played: "+table[3]);
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					if(nextPlayer==4)
					{
						table[0] = cp1.playCard(trump, heartsPlayed, firstRoundFlag);//cp1's turn is after my turn
						nextPlayer=1;

						System.out.println();
						System.out.println("CP1 has played a card");
						System.out.println("CP1 has played: "+table[0]);
						System.out.print("Press any key to continue . . . ");
						System.out.println();
						pause.nextLine();
					}
					firstPlayer = -1;// resetting. We need this so firstPlayer is chosen by winner of last round.  
				}// end of playing card based on first round or not and taking cards of correct players. 
			}// end of while checking table array for all 4 cards played

			checkRoundResults();
			System.out.println();
			System.out.println("Trick finished");
			System.out.println();

		}// checking that all players have cards left to play a card


	}// end of playCards method


	public void checkRoundResults(){
		int tablePositionWinner = -1; //resets index position of person who wins round
		int roundPenalties = 0;	// resets to zero at start of every checkRoundResults

		//checking which card on table is the winner of the round
		for(int i = 0; i<4; i++){ 
			if (table[i].getSuit()==trump){
				if((table[i].getValue() >= table[0].getValue()) && (table[i].getValue() >= table[1].getValue()) && (table[i].getValue() >= table[2].getValue()) && (table[i].getValue() >= table[3].getValue()))
					tablePositionWinner = i;
			}
		}// end of for loop that check who has won round

			// *** Test
			System.out.println("tablePositionWinner =" + tablePositionWinner);
			
		//calculate penalties in round by looping through all 4 cards on table and awards them to loosing player

		for(int k = 0; k<4; k++){
				if(table[k].getSuit()==1)// is hearts - get a point for each heart
					System.out.println("One penalty added for a heart found in round");
				roundPenalties++;
				if(table[k].getSuit()==0 && table[k].getValue()==12)// if Queen of Spades present - get 13 points 
					System.out.println("13 penalty points added for Queen of Spades found in round");
				roundPenalties = roundPenalties+13; 
		}// end of for loop going through all 4 cards on table
			
			// *** Test
			System.out.println("roundPenalties =" + roundPenalties);

		//}//end of checking played cards on table for winning card and calculating penalty points associated with win

		if(tablePositionWinner == 0)
		{
			cp1.setPenalties(roundPenalties);
			System.out.println();
			System.out.println("CP1 received "+roundPenalties+" penalty points");
			System.out.println();

		}else if(tablePositionWinner == 1){
			cp2.setPenalties(roundPenalties);
			System.out.println();
			System.out.println("CP2 received "+roundPenalties+" penalty points");
			System.out.println();


		}else if(tablePositionWinner == 2){
			cp3.setPenalties(roundPenalties);
			System.out.println();
			System.out.println("CP3 received "+roundPenalties+" penalty points");
			System.out.println();

		}else{
			me.setPenalties(roundPenalties);
			System.out.println();
			System.out.println("I received "+roundPenalties+" penalty points");
			System.out.println();

		}

		System.out.println("A summary of the penalty points awarded so far");
		System.out.println("CP1: "+cp1.getPenalties());
		System.out.println("CP2: "+cp2.getPenalties());
		System.out.println("CP3: "+cp3.getPenalties());
		System.out.println("Mine: "+me.getPenalties());
		System.out.println();

		System.out.println("The winner of the last round is: ");
		if (tablePositionWinner == 0)
			System.out.println("CP1");
		else if (tablePositionWinner ==1)
			System.out.println("CP2");
		else if (tablePositionWinner ==2)
			System.out.println("CP3");
		else if (tablePositionWinner ==3)
			System.out.println("I won");
		
		System.out.println();
		firstPlayer = tablePositionWinner;
		tablePositionWinner = -1; //resetting for next round

		//Prepare table for next round so setting all table values to zero
		for(int k = 0; k<4; k++) {
			table[k] = null;
			System.out.println();
			System.out.println("Ready for next round: table"+k+" "+table[k]);
			System.out.println();
		}
	}// end of checkRoundResults


	private void checkGameResults() {
		//to do

	}


}// end of class













