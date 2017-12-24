package nl.sogyo.sudoku;

/**
 * Project: java-opdrachten
 * FQCN: nl.sogyo.javaopdrachten.sudoku.poging2.SudokuBacktrackAlgo
 * <p>
 * Created by kverlaan
 * on 06-Dec-17
 */
public class SudokuBacktrackAlgo extends Sudoku {

    SudokuBacktrackAlgo(String in) {
        init(in.toCharArray());
    }

    void init(char[] input) {

        gridsize = (int) Math.sqrt(input.length);
        blocksize = (int) Math.sqrt(gridsize);
        solution = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int val = input[i] - 48;
            if (val != 0) {
                int x = i % gridsize;
                int y = i / gridsize;
                updateSolutionArray(x, y, val - 1);
            }
        }
    }

    private void updateSolutionArray(int x, int y, int val) {

        solution[x + (y * gridsize)] = val + 1;
    }

    public void solve() {

        int[] guessSolution = new int[solution.length];
        System.arraycopy(solution, 0, guessSolution, 0, solution.length);

        if (guess(0, guessSolution))
            solution = guessSolution;
        else
            System.out.println("backtracking failed!");

    }

    private boolean isValid(int val, int index, int[] matrix) {

        int x = index % gridsize;
        int y = index / gridsize;
        for (int i = 0; i < gridsize; i++) {
            if (matrix[x + (i * gridsize)] == val || matrix[i + (y * gridsize)] == val || matrix[((i / blocksize) + (x / blocksize) * blocksize) + (((i % blocksize) + (y / blocksize) * blocksize) * gridsize)] == val)
                return false;
        }
        return true;
    }

    private boolean guess(int index, int[] matrix) {

        int nextIndex = index + 1;
        if (index >= (gridsize * gridsize)) {
            //end of the sudoku reached, we must have solved it
            solution = matrix;
            return true;
        }

        if (matrix[index] != 0) {
            //already filled in, we skip this one
            if (guess(nextIndex, matrix))
                return true;
        }

        for (int i = 1; i <= gridsize; i++) {
            if (!isValid(i, index, matrix)) //check for contradictions
                continue; //try another number

            matrix[index] = i; //save the guess as a possibility

            if (guess(nextIndex, matrix)) //recursively try to complete the sudoku
                return true;

            //reached a dead end, reset the guess
            matrix[index] = 0;
        }
        //no valid guesses
        return false;
    }

}