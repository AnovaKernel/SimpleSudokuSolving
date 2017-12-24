package nl.sogyo.sudoku;

/**
 * Created by Koen on 24/12/2017.
 */
public abstract class Sudoku {

    int gridsize, blocksize;
    int[] solution;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\r\n|- - - - - -|\r\n|");
        for (int i = 0; i < solution.length; i++) {
            sb.append(solution[i]);
            if ((i % blocksize) + 1 == blocksize)
                sb.append("|");
            if ((i % gridsize) + 1 == gridsize)
                sb.append("\r\n");
            if (((i % blocksize) + 1 == blocksize) && ((i % gridsize) + 1 == gridsize))
                sb.append("|");
            if ((i % (blocksize * gridsize)) + 1 == (blocksize * gridsize))
                sb.append("- - - - - -|\r\n|");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    abstract void init(char[] input);

    public abstract void solve();

}
