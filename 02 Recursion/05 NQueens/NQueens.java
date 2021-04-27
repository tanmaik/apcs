// Name: J1-10 
// Date: 10/27/20

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NQueens extends JPanel {
   // Instance Variables: Encapsulated data for EACH NQueens problem
   private JButton[][] board;
   private int N;
   JSlider speedSlider;
   private int timerDelay;

   public NQueens(int n) {
      N = n;
      this.setLayout(new BorderLayout());

      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      JLabel label = new JLabel(N + "Queens solution");
      north.add(label);

      JPanel center = new JPanel();
      center.setLayout(new GridLayout(N, N));
      add(center, BorderLayout.CENTER);
      board = new JButton[N][N];
      for (int r = 0; r < N; r++)
         for (int c = 0; c < N; c++) {
            board[r][c] = new JButton();
            board[r][c].setBackground(Color.blue);
            center.add(board[r][c]);
         }

      speedSlider = new JSlider();
      speedSlider.setInverted(true);
      add(speedSlider, BorderLayout.SOUTH);
   }

   /** Returns the number of queens to be placed on the board. **/
   public int numQueens() {
      return N;
   }

   /** Solves (or attempts to solve) the N Queens Problem. **/
   public boolean solve() {

      return isPlaced(0, 0);
   }

   /**
    * Iteratively try to place the queen in each column. Recursively try the next
    * row.
    **/
   private boolean isPlaced(int row, int col) {
      if (col == N) // matrix is filled
         return true;

      for (int r = 0; r < N; r++) {
         if (locationIsOK(r, col)) {
            addQueen(r, col);
            if (isPlaced(r, col + 1)) {
               return true;
            } else {
               removeQueen(r, col);

            }
         }
      }
      return false;
   }

   /**
    * Verify that another queen can't attack this location. You only need to check
    * the locations above this row. Iteration is fine here.
    **/
   private boolean locationIsOK(int r, int c) {
      for (int i = 0; i < N; i++) {
         if (board[r][i].getBackground().equals(Color.RED)) {
            return false;
         }
         if (board[i][c].getBackground().equals(Color.RED)) {
            return false;
         }
      }
      for (int x = r, y = c; x >= 0 && y >= 0; x--, y--) {
         if (board[x][y].getBackground().equals(Color.RED)) {
            return false;
         }
      }
      for (int x = r, y = c; x >= 0 && y < N; x--, y++) {
         if (board[x][y].getBackground().equals(Color.RED)) {
            return false;
         }
      }
      for (int x = r, y = c; x < N && y >= 0; x++, y--) {
         if (board[x][y].getBackground().equals(Color.RED)) {
            return false;
         }
      }
      for (int x = r, y = c; x < N && y < N; x++, y++) {
         if (board[x][y].getBackground().equals(Color.RED)) {
            return false;
         }
      }
      return true;

   }

   /**
    * Adds a queen to the specified location.
    **/
   private void addQueen(int r, int c) {
      board[r][c].setBackground(Color.RED);
      pause();
   }

   /**
    * Removes a queen from the specified location.
    **/
   private void removeQueen(int r, int c) {
      board[r][c].setBackground(Color.BLUE);
      pause();
   }

   private void pause() {
      int timerDelay = speedSlider.getValue();
      for (int i = 1; i <= timerDelay * 1E7; i++) {
      }

   }
}