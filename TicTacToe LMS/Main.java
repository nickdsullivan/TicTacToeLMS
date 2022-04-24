import java.util.ArrayList;
import java.io.*;

/**
 * Create TicTacToe and LMS learner
 *
 * @author (Nicholas Sullivan)
 * @version (a version number or a date)
 */


public class Main
{
    
    public static TicTacToe [] games = new TicTacToe[20];
    public static int index = 0;
    
    public static void main(String[] args)
    {
        ArrayList<Float> winPercent = new ArrayList<Float>();
        TicTacToe game = new TicTacToe();
        LMSController controller = new LMSController(game);
        
        //This LMS does not change through out.  It is given an intial value and will not change.
        LMSController StaticController = new LMSController(game);
        //Unchaning controllers weights
        float [] ws = {0,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100,(float)(Math.random()-.5)*100};
        StaticController.setWeights(ws);   
        final int gameCounter = 10000;
        int counter = 0;
        int xWins = 0;
        int randoWins = 0;
        int draws = 0;
        int total =0;
        
        
        while(counter < gameCounter){
            counter ++;
            
            
            while(true){
                //System.out.println(game);
                int [] rando = new int[2];
                rando[0] = (int)(Math.random()*3);
                rando[1] = (int)(Math.random()*3);
                while(game.canMove(rando[0],rando[1]) == false){
                    rando[0] = (int)(Math.random()*3);
                    rando[1] = (int)(Math.random()*3);
                    
                    
                }
                //move(rando,game);
                int [] lmsmove = new int[2];
                lmsmove= controller.getMove();
                move(lmsmove,game);
     
                //Check if the game is done
                if(game.checkWin()){
                    total++;
                    System.out.println("Game won by " + game.getTurn());
                    
                    //System.out.println(game.toString());
                    
                    xWins ++;
                    controller.incWins();
                    controller.train(100,index,games);
                    StaticController.train(-100,index,games);
                    break;
                    
                   
                    
                }
                if(game.checkDraw()){
                    System.out.println("draw");
                    
                    draws ++;
                    controller.train(0,index,games);
                    StaticController.train(0,index,games);
                    break;
                   
                    
                }
                
                //static controller move / O's move
                lmsmove = new int[2];
                lmsmove= StaticController.getMove();
                //move(lmsmove,game);
                
                rando = new int[2];
                rando[0] = (int)(Math.random()*3);
                rando[1] = (int)(Math.random()*3);
                while(game.canMove(rando[0],rando[1]) == false){
                    rando[0] = (int)(Math.random()*3);
                    rando[1] = (int)(Math.random()*3);
                    
                    
                }
                move(rando,game);
                
 
                
                

                if(game.checkWin()){
                    total++;
                    System.out.println("Game won by " + game.getTurn());
                    System.out.println(game);
                    controller.incWins();
                    controller.train(-100,index,games);
                    StaticController.train(100,index,games);
                    randoWins ++;
                    break;
                    
                }
                if(game.checkDraw()){
                    System.out.println("draw");
                    //System.out.println(game.toString());
                    draws++;
                    controller.train(0,index,games);
                    StaticController.train(0,index,games);
                    break;
                   
                    
                }
            }
            //Have to do some reseting here
            game.reset();
            games = new TicTacToe[20];
            index = 0;
            System.out.println((float)xWins/(float)total);
            
            //This is for the chart
            winPercent.add((float)xWins/(float)total *(float) 100);
        }
        System.out.println(controller.toString());
        System.out.println(StaticController.toString());
        System.out.println(xWins);
        System.out.println(randoWins);
        System.out.println(draws);
        toCSV(winPercent);
        
        
        
    }
    

    public static void toCSV(ArrayList<Float> arr){
        FileWriter file;
        try{
            file = new FileWriter("data.csv");
            file.append("Game #, LMS's win percentage\n");
            for(int i = 0; i < arr.size(); i ++){
                file.append(i + "," + arr.get(i) + "\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //This is terrible code.  I started to get tired
    public static boolean move(int [] move, TicTacToe game){
        int x = move[0];
        int y = move[1];
        TicTacToe temp = new TicTacToe();
        //Check if can be here if so move it + other stuff if not return false
        if (game.canMove(move[0],move[1])){
            game.copy(temp);
            games[index] = temp;
            index++;
            game.place(move[0],move[1]);
        }
        else{
            return false;
        }

        game.place(x,y);
        
 
        return true;
        
    }
    
    //Takes in a boolean that column if true then run for the column if not its the row
    public static int numberOf(boolean column,int num, char piece,TicTacToe game){
        int count = 0;
        int p = TicTacToe.charToInt(piece);
        
            for(int i =0 ; i < game.board.length; i ++){
                //inefficent but it looks clear than 2 for loops.  Improves space complexity I guess
               if (column){
                    if(game.board[num][i] == p){
                        count ++;
                    }
                }
                else{
                    //Can put the i in any position becasue the thing is square.  I know I know doesnt work for expanded board.
                    if(game.board[i][num] == p){
                        count ++;
                    }
                }
                    
                
                
            }
        
        return count;        
    }
    public static int numberOfDiagonal(boolean leftToRight, char piece, TicTacToe game){
        int count = 0;
        int p = TicTacToe.charToInt(piece);
        if (leftToRight){
            for(int i = 0; i < game.board.length; i ++){
                if (game.board[i][i] == p){
                    count++;
                }
            }
        }
        else{
           if (game.board[2][0] == p){
               count ++;
           }
           if (game.board[1][1] == p){
               count++;
           }
           if(game.board[0][2] == p){
               count++;
           }
        }
        return count;
    }
    

}
