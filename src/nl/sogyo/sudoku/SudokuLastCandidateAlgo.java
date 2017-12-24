package nl.sogyo.sudoku;

/**
 * Project: java-opdrachten
 * FQCN: nl.sogyo.javaopdrachten.sudoku.poging2.SudokuBacktrackAlgo
 * <p>
 * Created by kverlaan
 * on 06-Dec-17
 */
public class SudokuLastCandidateAlgo extends Sudoku {

    private boolean[][][] possibilityMatrix;

    public SudokuLastCandidateAlgo(String input) {
        init(input.toCharArray());
    }

    void init(char[] input) {
        gridsize = (int) Math.sqrt(input.length);
        blocksize = (int) Math.sqrt(gridsize);
        possibilityMatrix = new boolean[gridsize][gridsize][gridsize];
        solution = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int val = input[i] - 48;
            if (val != 0) {
                int x = i % gridsize;
                int y = i / gridsize;
                updatePossibilities(x, y, val - 1);
            }
        }
    }

    private void updatePossibilities(int x, int y, int val) {

        for (int i = 0; i < gridsize; i++) {
            possibilityMatrix[x][i][val] = true;
            possibilityMatrix[i][y][val] = true;
            possibilityMatrix[(i / blocksize) + (x / blocksize) * blocksize][(i % blocksize) + (y / blocksize) * blocksize][val] = true;
        }
        solution[x + (y * gridsize)] = val + 1;
    }

    public void solve() {

        for (int x = 0; x < gridsize; x++) {
            for (int y = 0; y < gridsize; y++) {
                int falseCount = 0;
                int val = 0;
                for (int i = 0; i < gridsize; i++) {
                    if (!possibilityMatrix[x][y][i]) {
                        falseCount++;
                        val = i; //save for later usage
                    }
                }
                if (falseCount == 1) {
                    updatePossibilities(x, y, val);
                }
            }
        }
    }
}