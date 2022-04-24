
/**
 * Write a description of class TicTacToe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
//0 is blank
//1 is x
//2 is o

//Must access [y][x] cause im dumb
public class TicTacToe
{
    public int [][] board;
    public boolean turn = true;
    int count;
    public TicTacToe()
    {
        count = 0;
        board = new int[3][3];
    }
    public void reset(){
        board =new int[3][3];
        turn = true;
        count = 0;
    }
    public void copy(TicTacToe game){
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                game.board[i][j] = this.board[i][j];
            
            }
        }
        game.turn = this.turn;
        game.count = this.count;
        
    }
    public String toString(){
        String str= "";
        char c = ' ';
        
        for (int i = board.length-1; i >= 0; i --){
            for (int j = 0; j < board[i].length; j ++){
                //should be a switch but its fine
                if (board[i][j] == 0){
                    c = ' ';
                }
                else if (board[i][j] == 1) {
                    c = 'X';
                }
                else if (board[i][j] == 2) {
                    c = 'O';
                }
                str += c + " ";
            }
            str += "\n";
        }
        return str;
    }
    public boolean place(int x, int y){
        
        if (board[y][x] != 0){
            return false;
        }
        if (turn == true){
            board[y][x] = 1;
            
        }
        else{
            board[y][x] = 2;
        }
        turn = !turn;
        count ++;
 
        return true;
    }
    
    public Boolean checkDraw(){
        if (count == 9 && !checkWin()){
            return true;
        }
        return false;
    }
    public Boolean canMove(int x, int y){
        return board[y][x] == 0;
    }
    public Boolean checkWin(){
        //check the diagonals then check the ups and downs

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]&& board[0][0] != 0){
            turn = !turn;
            return true;
        }

        if (board[2][0] == board[1][1] && board[2][0] == board[0][2]&& board[2][0] != 0){
            turn = !turn;
            return true;
        }
        if (checkVertical(0,0) || checkHorizontal(0,0) || checkVertical(1,1) || checkHorizontal(1,1) || checkVertical(2,2) || checkHorizontal(2,2)){
            turn = !turn;
            return true;
        }
        return false;
    }
    private Boolean checkVertical(int x, int y){
        if (board[y][x] == 0){
            return false;
        }
        //Check up ward
        //Check downward
        for(int i = x; i < board.length; i ++){
            if (board[y][i] != board[y][x]){
                return false;
            }
        }
        for(int i = x; i >= 0;  i --){
            if (board[y][i] != board[y][x]){
                return false;
            }
        }
        //horizontal checking
        
        return true;
    }
    private Boolean checkHorizontal(int x, int y){
        if (board[y][x] == 0){
            return false;
        }
        for(int i = y; i < board.length; i ++){
                if (board[i][x] != board[y][x]){
                    return false;
                }
            }
        for(int i = x; i >= 0;  i --){
            if (board[i][y] != board[y][x]){
                return false;
            }
        }
        return true;
    }
    public static int charToInt(char c){
         switch(c){
             case(' '):
                 return 0;
            case ('X'):
                return 1;
            case('O'):
                return 2;
            default:
                return -1;                 
     
         }
    }
    public char getTurn(){
        
        if (turn == true){
            return 'X';
        }
        return 'O';
    }
 

 
  
}
