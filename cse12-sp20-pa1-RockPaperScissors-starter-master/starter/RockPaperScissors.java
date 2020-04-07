/**
 * Class RockPaperScissors. Plays repeated games of Rock Paper Scissors
 * with a user.
 *
 * NAME: Nicolas Schaefer
 * ID: A16132183
 * EMAIL: nschaefe@ucsd.edu
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * This class is used to play repeated games of Rock Paper Scissors
 * with a user. It uses a few helper methods along with methods that
 * interact with user input to accomplish this.
 */
public class RockPaperScissors {
    String[] systemMoves;			// Stores the computer's moves
    ArrayList<String> userMoves;	// Stores the user's moves
    int initialCapacity;            // Initial capacity of systemMoves
    int size;                       // Number of moves the system makes
    boolean playing;                // If user is still playing game or not
    Counter totalGames;             // Total number of games played
    Counter playerWin;              // Number of times player wins
    Counter cpuWin;                 // Number of times cpu wins
    Counter tie;                    // Number of ties


    // Used to calculate percentages
    private static final int PERCENT = 100;

    // Number of most recent games we want to print when game ends
    private static final int NUM_RECENT_GAMES = 10;

    // Use these variables for consistency
    private static final String ROCK = "r";
    private static final String PAPER = "p";
    private static final String SCISSORS = "s";
    private static final String QUIT = "q";
    private static final String ROCK_TIE = "I chose rock. It's a tie.";
    private static final String PAPER_SYS_WIN = "I chose paper. I win!";
    private static final String SCISSORS_USR_WIN =
        "I chose scissors. You win.";
    private static final String PAPER_TIE = "I chose paper. It's a tie.";
    private static final String SCISSORS_SYS_WIN = "I chose scissors. I win!";
    private static final String ROCK_USR_WIN = "I chose rock. You win.";
    private static final String SCISSORS_TIE = "I chose scissors. It's a tie.";
    private static final String ROCK_SYS_WIN = "I chose rock. I win!";
    private static final String PAPER_USR_WIN = "I chose paper. You win.";
    private static final String INVALID_INPUT =
        "That is not a valid move. Please try again.";
    private static final String THANKS =
        "Thanks for playing!\nOur most recent games were: ";
    private static final String SYS_USR_MOVES = "Me: %s, You: %s\n";
    private static final String OVERALL_STATS =
        "Our overall stats are:\n" +
        "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    private static final String PROMPT_MOVE =
        "Let's play! What's your move?" +
        "(r=rock, p=paper, s=scissors or q to quit)";

    /**
     * Constructor for the RockPaperScissors class
     * initializes instance variables
     */
    public RockPaperScissors() {
        // TODO
        this.initialCapacity = 5;
        this.systemMoves = new String[this.initialCapacity];
        this.userMoves = new ArrayList<String>();
        this.size = 0;
        this.playing = true;
        this.totalGames = new Counter();
        this.playerWin = new Counter();
        this.cpuWin = new Counter();
        this.tie = new Counter();
    }

    /**
     * Generates next cpu move
     *
     * @return String - "r", "p", or "s"
     */
    static String genCPUMove() {
        //TODO
        int val = (int)(Math.random()*3);
        switch(val){
            case 0:
              return ROCK;
            case 1:
              return PAPER;
            case 2:
              return SCISSORS;
        }
        return null;
    }

    /**
     * Expands (doubles) the capacity of systemMoves
     *
     * @return void
     */
    public void expandCapacity() {
        // TODO
        String[] temp = new String[this.systemMoves.length];
        for (int i = 0;i<this.systemMoves.length;i++) {
            temp[i] = this.systemMoves[i];
        }
        this.systemMoves = new String[temp.length*2];
        for (int i = 0;i<temp.length;i++) {
            this.systemMoves[i] = temp[i];
        }
    }

    /**
     * Adds system move to systemMoves array
     *
     * @param  sysMove - move of the system
     * @return void
     */
    public void addSystemMove(String sysMove) {
        // TODO
        for (int i = 0;i<this.systemMoves.length;i++) {
            if (this.systemMoves[i]==null){
                this.systemMoves[i] = sysMove;
                if (i==this.systemMoves.length-1){
                    expandCapacity();
                }
                break;
            }


        }
    }

    /**
     * Takes the user move, the system move, and determines who wins.
     * Updates instance variables accordingly. Ends game if playerMove is "q".
     *
     * @param playerMove - move of the player
     * @param sysMove - move of the system
     * @return void
     */
    void play(String playerMove, String sysMove) {
        // TODO : write code for the game
        if (playerMove.equals(QUIT)){
            this.playing = false;
            end();
            return;
        }

        if (playerMove.equals(ROCK)){
            userMoves.add(ROCK);
            if (sysMove.equals(ROCK)){
                addSystemMove(ROCK);
                System.out.println(ROCK_TIE);
                tie.increment();
                totalGames.increment();
            } else if (sysMove.equals(PAPER)){
                addSystemMove(PAPER);
                System.out.println(PAPER_SYS_WIN);
                cpuWin.increment();
                totalGames.increment();
            } else {
                addSystemMove(SCISSORS);
                System.out.println(SCISSORS_USR_WIN);
                playerWin.increment();
                totalGames.increment();
            }
        }else if (playerMove.equals(PAPER)){
            userMoves.add(PAPER);
            if (sysMove.equals(ROCK)){
                addSystemMove(ROCK);
                System.out.println(ROCK_USR_WIN);
                playerWin.increment();
                totalGames.increment();
            } else if (sysMove.equals(PAPER)){
                addSystemMove(PAPER);
                System.out.println(PAPER_TIE);
                tie.increment();
                totalGames.increment();
            } else {
                addSystemMove(SCISSORS);
                System.out.println(SCISSORS_SYS_WIN);
                cpuWin.increment();
                totalGames.increment();
            }
        }else if (playerMove.equals(SCISSORS)){
            userMoves.add(SCISSORS);
            if (sysMove.equals(ROCK)){
                addSystemMove(ROCK);
                System.out.println(ROCK_SYS_WIN);
                cpuWin.increment();
                totalGames.increment();
            } else if (sysMove.equals(PAPER)){
                addSystemMove(PAPER);
                System.out.println(PAPER_USR_WIN);
                playerWin.increment();
                totalGames.increment();
            } else {
                addSystemMove(SCISSORS);
                System.out.println(SCISSORS_TIE);
                tie.increment();
                totalGames.increment();
            }
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * This method is given to you, make sure to call it at the end of
     * the game! Do not change any of the given code.
     * Print out the end game stats: moves played and win percentages
     *
     * @return void
     */
    void end() {
    	// Calculate percentages
    	float systemWinPercent = (float) this.cpuWin.getCount() /
            (float) this.totalGames.getCount() * PERCENT;
    	float playerWinPercent = (float) this.playerWin.getCount() /
            (float) this.totalGames.getCount() * PERCENT;
    	float tiedPercent = (float) this.tie.getCount() /
            (float) this.totalGames.getCount() * PERCENT;

    	System.out.println(THANKS);

        // Get which index to print to
        int printTo = (this.totalGames.getCount() < NUM_RECENT_GAMES) ?
            0 : this.totalGames.getCount() - NUM_RECENT_GAMES;

        // Print system and user moves
    	for (int i = this.totalGames.getCount() - 1 ; i >= printTo; i--) {
            System.out.printf(SYS_USR_MOVES, this.systemMoves[i],
                this.userMoves.get(i));
    	}

    	System.out.printf(OVERALL_STATS, systemWinPercent, playerWinPercent,
            tiedPercent);
    }

    /**
     * This method is given to you, it will call the play method
     * Main method that reads user input, generates cpu move, and plays game
     *
     * @param args - arguments passed in from command line in String form
     * @return void
     */
    public static void main(String[] args)
    {
        // Create new game and scanner
        RockPaperScissors game = new RockPaperScissors();
        Scanner in = new Scanner(System.in);

        // While user does not input "q" (logic in play method), play game
        while(game.playing) {
            System.out.println(PROMPT_MOVE);
            String userMove = in.nextLine();
            // Generate computer's move
            String cpuMove = genCPUMove();
            game.play(userMove, cpuMove);
        }

        in.close();
    }
}
