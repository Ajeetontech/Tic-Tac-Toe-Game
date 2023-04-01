package game;

import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
	
	TicTacToe()
	{
		board= new char[3][3];
		initBoard();
	}
	
	static void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for (int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}

static void DispBoard()
{
	System.out.println("-------------");
	for(int i=0;i<board.length;i++)
	{
		System.out.print("| ");
		for(int j=0;j<board[i].length;j++)
		{
			System.out.print(board[i][j]+" | ");
		}
		System.out.println();
		System.out.println("-------------");
	}
	
}

static void PlaceMark(int row,int col,char mark)
{
	if(row>=0 && row<=2 && col>=0 && col<=2)
	{
		board[row][col]=mark;
	}
	else
	{
		System.out.println("Invalid Positon");
	}
}

static boolean CheckColWin()
{
	for(int j=0;j<=2;j++)
	{
		if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
		{
			return true;
		}
	}
	return false;
}

static boolean CheckRowWin()
{
	for(int i=0;i<=2;i++)
	{
		if(board[i][0]!=' ' && board[i][0]==board[i][1] 
				&& board[i][1]==board[i][2])
		{
			return true;
		}
	}
	return false;
}

static boolean CheckDiagWin()
{
	if(board[0][0]!=' ' && board[0][0]==board[1][1] 
			&& board[1][1]==board[2][2] 
			|| board[0][2]!=' ' && board[0][2]==board[1][1]
					&&  board[1][1]==board[2][0])
	{
		return true;
	}
return false;
}

static boolean CheckDraw()
{
	for(int i=0;i<=2;i++)
	{
		for(int j=0;j<=2;j++)
		{
			if(board[i][j]==' ')
			{
				return false;
			}
		}
	}
	return true;
}

}


abstract class Player
{
	String name;
	char mark;
	abstract void MakeMove();
	
	static boolean isValidMove(int row,int col)
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			if(TicTacToe.board[row][col]==' ')
			{
				return true;
			}
		}
		return false;
	}
}


class HumanPlayer extends Player
{
	
	HumanPlayer(String name,char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	void MakeMove()
	{
		Scanner sc = new Scanner(System.in);
		int row,col;
		do
		{
			System.out.print("Enter the row and Column : ");
			row = sc.nextInt();
			col = sc.nextInt();
			
		}while(!isValidMove(row,col));
		TicTacToe.PlaceMark(row, col, mark);
	}
}



class AIPlayer extends Player
{
	
	AIPlayer(String name,char mark)
	{
		this.name= name;
		this.mark = mark;
	}
	void MakeMove()
	{
		int row,col;
		Scanner sc = new Scanner(System.in);
		do
		{
			Random rdm = new Random();

			row = rdm.nextInt(3);
			col = rdm.nextInt(3);
		}while(!isValidMove(row,col));
		TicTacToe.PlaceMark(row, col, mark);
	}
}


public class CommonGame {

	public static void main(String[] args) {
		TicTacToe tt = new TicTacToe();
		tt.DispBoard();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name: ");
		System.out.println("Enter the Mark or Symbol do you want: ");
		HumanPlayer p1 = new HumanPlayer(sc.nextLine(),sc.next().charAt(0));
		AIPlayer p2 = new AIPlayer("AI",'O');
		Player cp;
		cp=p1;
		while(true)
		{
			System.out.println(cp.name+" "+"turn");
			cp.MakeMove();
			tt.DispBoard();
			if(TicTacToe.CheckRowWin() || TicTacToe.CheckColWin()
					|| TicTacToe.CheckDiagWin())
			{
				System.out.println(cp.name+ " "+"has Won");
				break;
			}
			else if(TicTacToe.CheckDraw())
			{
				System.out.print("Game is Draw");
				break;
			}
			else
			{
				if(cp==p1)
				{
					cp=p2;
				}
				else
				{
					cp=p1;
				}
			}
		}
	}

}
