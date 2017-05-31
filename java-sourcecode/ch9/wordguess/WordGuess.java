/* * WordGuess.java * An n-player version of the Word Guess Game * To compile: javac -classpath nplayer.jar:. WordGuess.java * To run: java -classpath nplayer.jar:. WordGuess */public class WordGuess extends ComputerGame implements CLUIPlayableGame {    private String secretWord;    private StringBuffer currentWord;    private StringBuffer previousGuesses;    private int unguessedLetters;    public WordGuess() { super(1); init(); }    public WordGuess(int m) { super(m); init(); }     public void init() {       secretWord = getSecretWord();       currentWord = new StringBuffer(secretWord);       previousGuesses = new StringBuffer();       for (int k = 0; k < secretWord.length(); k++)          currentWord.setCharAt(k,'?');       unguessedLetters = secretWord.length();   }    public String getPreviousGuesses() {	return previousGuesses.toString();    }    public String getCurrentWord() {	return currentWord.toString();    }    private String getSecretWord() {           int num = (int)(Math.random()*10);        switch (num)        {   case 0: return "SOFTWARE";            case 1: return "SOLUTION";            case 2: return "CONSTANT";            case 3: return "COMPILER";            case 4: return "ABSTRACT";            case 5: return "ABNORMAL";            case 6: return "ARGUMENT";            case 7: return "QUESTION";            case 8: return "UTILIZES";            case 9: return "VARIABLE";            default: return "MISTAKES";        } //switch    } //getSecretWord()    /* Returns true if letter is a new correct letter    in which case the letter is filled into currentWord.    */    private boolean guessLetter(char letter) {   	previousGuesses.append(letter);	if (secretWord.indexOf(letter) == -1)            return false; // letter is not in secretWord        else // find positions of letter in secretWord        {   for (int k = 0; k < secretWord.length(); k++)            {   if (secretWord.charAt(k) == letter)                {   if (currentWord.charAt(k) == letter)                        return false; //already guessed                    currentWord.setCharAt(k,letter);                    unguessedLetters--; //one less to find                } //if            } //for            return true;        } //else    } //guessLetter()    /** Overridden method from TwoPlayerGame */    public String getRules() {           return "\n*** The Rules of Word Guess ***\n" +        "(1) The game generates a secret word.\n" +        "(2) Two players alternate taking moves.\n" +        "(3) A move consists of guessing a letter in the word.\n" +        "(4) A player continues guessing until a letter is wrong.\n" +        "(5) The game is over when all letters of the word are guessed\n" +        "(6) The player guessing the last letter of the word wins.\n";    } //getRules()    /** Implementation of abstract method from TwoPlayerGame */    public boolean gameOver() {           return (unguessedLetters <= 0);    } // gameOver()    /** Implementation of abstract method from TwoPlayerGame */    public String getWinner() {           if (gameOver())            return "Player " + getPlayer();        else return "The game is not over.";    } // getWinner() public void play(UserInterface ui) {    ui.report(getRules());    ui.report(listPlayers());    ui.report(reportGameState());    while(!gameOver()) {          WordGuesser p = (WordGuesser)player[whoseTurn];        if (p.isComputer())            ui.report(submitUserMove(p.makeAMove(getGamePrompt())));        else {            ui.prompt(getGamePrompt());            ui.report(submitUserMove(ui.getUserInput()));        }        ui.report(reportGameState());    } // while }    /** Implementation of method from CLUIPlayable */    public String reportGameState() {   	if (!gameOver())	    return "\nCurrent word " + currentWord.toString() + " Previous guesses " 		+ previousGuesses + "\nPlayer " + getPlayer() + " guesses next.";	else 	    return "\nThe game is now over! The secret word is " + secretWord +		"\n" + getWinner() + " has won!\n";    } // report()    /** Implementation of method from CLUIPlayable */    public String getGamePrompt()    {    return "\nGuess a letter that you think is in the secret word: ";    } //prompt()    /** Implementation of method from CLUIPlayable */    public String submitUserMove(String s) {           char letter = s.toUpperCase().charAt(0);        if (guessLetter(letter)) {   //if correct             return "Yes, the letter " + letter +                   " IS in the secret word\n";         } else {                changePlayer();             return "Sorry, " + letter + " is NOT a " +                    "new letter in the secret word\n";         }     } //move()    /** A WordGuess Application Program    */    public static void main(String args[])  {  	KeyboardReader kb = new KeyboardReader();        kb.prompt("How many players are playing, 1,2  or more? ");	int m = kb.getKeyboardInteger();        ComputerGame game = new WordGuess(m);        for (int k = 0; k < m; k++) {            kb.prompt("Player " + k + ", what type, Human  = 1, or Computer = 2 ? ");	    int choice = kb.getKeyboardInteger();            if (choice == 1) {                Player player = new WordGuesser((WordGuess)game, k, Player.HUMAN);                game.addPlayer(player);	    } else {                Player player = new WordGuesser((WordGuess)game, k, Player.COMPUTER);                game.addPlayer(player);	    }	}       ((CLUIPlayableGame)game).play(kb);    } //main()} //WordGuess class