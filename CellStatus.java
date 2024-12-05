/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #13
 * 1 - 5026231067 - Yudhistira Armico Fidly
 * 2 - 5026231093 - Vanya Patia Vinauli Gultom
 * 3 - 5026231104 - Raffi Deva Anargya
 */

//package sudoku;
/**
 * An enumeration of constants to represent the status
 * of each cell.
 */
public enum CellStatus {
    GIVEN,         // clue, no need to guess
    TO_GUESS,      // need to guess - not attempted yet
    CORRECT_GUESS, // need to guess - correct guess
    WRONG_GUESS    // need to guess - wrong guess
    // The puzzle is solved if none of the cells have
    //  status of TO_GUESS or WRONG_GUESS
}