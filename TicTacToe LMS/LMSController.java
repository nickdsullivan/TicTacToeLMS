
/**
 * Write a description of class LMSController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LMSController
{
    
    LMS lms;
    int size;
    int wins;
    TicTacToe mainGame;
    
    public LMSController(TicTacToe game)
    {
        this.mainGame = game;
        size = 16;
        lms = new LMS(size);
    }
    public int[] getInputs(TicTacToe game){
        
        
        int[] inputs = new int[size+1];
        int index = 0;
        inputs[index] = 1;
        index ++;
        //How many x's are on each col
        for (int i = 0; i < 3; i ++){
            inputs[index] = Main.numberOf(true,i,'X', game);
            index ++;
        }
        //How many x's are on row 
        for (int i = 0; i < 3; i ++){
            inputs[index] = Main.numberOf(false,i,'X', game);
            index ++;
        }
        //How many O's are on cols 
        for (int i = 0; i < 3; i ++){
            inputs[index] = Main.numberOf(true,i,'O', game);
            index ++;
        }
        //How many O's are on cols 
        for (int i = 0; i < 3; i ++){
            inputs[index] = Main.numberOf(false,i,'O', game);
            index ++;
        }
        //Diagonals for each
        inputs[index] = Main.numberOfDiagonal(true,'X',game);
        index++;
        inputs[index] = Main.numberOfDiagonal(false,'X',game);
        index++;
        inputs[index] = Main.numberOfDiagonal(true,'O',game);
        index++;
        inputs[index] = Main.numberOfDiagonal(false,'O',game);
 
        return inputs;
    }
    public int[] getMove(){
        int[] move = new int[2];
        move[0] = -1;
        move[1] = -1;
        TicTacToe game = new TicTacToe();
        mainGame.copy(game);
        
        float max = (float)-Double.MAX_VALUE;
        float eval;
        for(int i = 0; i < game.board.length; i ++){
            for(int j = 0; j < game.board.length; j ++){
                
                if (!mainGame.canMove(i,j)){
                    continue;
                }
                if(mainGame.canMove(i,j)){
                    mainGame.copy(game);
                    game.place(i,j);
                    
                    eval = lms.getEvaluations(getInputs(game));
                    
      
                    
                    if (max < eval){
                        
                        max = eval;
                        move[0] = i;
                        move[1] = j;
                        
                        
                        
                        
                    }
                    
                    
                    
                    
                }
                
                
            }
        }

 

        return move;
        
        
    }
    public void train(int win,int index,TicTacToe [] games){ 
        /*
        System.out.println("PLS");
        System.out.println(games[index-1]);
        System.out.println(games[index-2]);
        System.out.println("PLS");
        */
        lms.train(win,getInputs(games[index-2]));
    }
    public void incWins(){
        wins++;
    }
    public String toString(){
        String str = "Wins: " + this.wins + "\n" + lms.toString();
        return str;
    }
    public void setWeights(float [] w){
        lms.setWeights(w);
    }
    

 
}
