package nl.sogyo.sudoku;


import java.util.Scanner;

//some example input strings
//0000000000000000
//123456789456789123789132456234567891567891234891324567345678912678912345912345678
//000000000000000000000000000000000000000000000000000000000000000000000000000000000

//000820090500000000308040007100000040006402503000090010093004000004035200000700900
//040000000000001803037060500060010000005306400000090020008050760906800000000000010
//000813700300205000012000000000700301940000067705008000000000630000604009009321000
//hardest sudoku:
//006000400000050070070100030800079006060301050700620004090007020030060000008000900

public class Main {

    public static void main(String[] args) {

        String input = "";
        do {
            input = parseInput(args);
        } while (!validate(input));

        SudokuBacktrackAlgo sudoku = new SudokuBacktrackAlgo(input);
        //SudokuLastCandidateAlgo sudoku = new SudokuLastCandidateAlgo(input);

        long startTime = System.currentTimeMillis();
        sudoku.solve();
        long endTime = System.currentTimeMillis();

        System.out.println("result after applying the algorithm: ");
        System.out.println(sudoku);
        System.out.println("Took " + (endTime - startTime) + " ms.");

    }

    private static String parseInput(String[] args) {
        String input;
        if (args != null && args.length > 0)
            input = args[0];
        else
            input = retryInput();
        return validate(input) ? input : retryInput();
    }

    private static String retryInput() {
        System.out.println("Insert the 81 digits of a sudoku puzzle, use 0 for empty cells");
        return new Scanner(System.in).next();
    }

    private static boolean validate(String input) {
        if (input == null) {
            System.out.println("no input received");
            return false;
        }
        //check for perfect square
        if (input.length() < 16 || ((int) Math.pow(Math.pow(Math.sqrt(Math.sqrt(input.length())), 2), 2) != input.length())) {
            System.out.println("received " + input.length() + "digits. Try a length of x^2^2, minimum x = 2.");
            return false;
        }
        //check for digits
        if (!input.matches("\\d+")) {
            String illegalChar = input.replaceAll("\\d+", "");
            System.out.println("received [" + illegalChar + "] as input");
            return false;
        }

        //all checks passed
        return true;
    }
}
