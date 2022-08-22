import java.util.*;

class Solution {




    public boolean isValidSudoku(char[][] board) {
        if(!checkHorizontal(board)){
            return false;
        }
        if(!checkVertical(board)){
            return false;
        }
//----------------------------SQUARES-CHECK--------------------------------------------

        if(!checkASquare(board,0,3,0,3)){
            return false;
        }

        if(!checkASquare(board,0,3,3,6)){
            return false;
        }
        if(!checkASquare(board,0,3,6,9)){
            return false;
        }
        if(!checkASquare(board,3,6,0,3)){
            return false;
        }
        if(!checkASquare(board,3,6,3,6)){
            return false;
        }
        if(!checkASquare(board,3,6,6,9)){
            return false;
        }
        if(!checkASquare(board,6,9,0,3)){
            return false;
        }
        if(!checkASquare(board,6,9,3,6)){
            return false;
        }
        return checkASquare(board, 6, 9, 6, 9);
    }
    private boolean isRepeat(List<Integer> numbers){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <numbers.size() ; i++) {
            if(map.containsKey(numbers.get(i)) || String.valueOf(numbers.get(i)).length() == 2){
                return false;
            }else{
                map.put(numbers.get(i), 1);
            }
        }
        return true;
    }
    private boolean checkASquare(char[][] board, int rowStart,int rowEnd, int columnStart,int columnEnd){
        List<Integer> currentNumbers = new ArrayList<>();
        for (int row = rowStart; row < rowEnd; row++) {
            for ( int column = columnStart; column < columnEnd; column++) {
                if(!(board[row][column] == '.')) {
                    currentNumbers.add(Integer.parseInt(String.valueOf(board[row][column])));
                }
            }
        }
        return isRepeat(currentNumbers);
    }
    private boolean checkHorizontal(char[][] board){
        for (int row = 0; row < board.length ; row++) {
            List<Integer> currentColumn = new ArrayList<>();
            for (int column = 0; column < board[row].length; column++){
                if(!(board[row][column] == '.')) {
                    currentColumn.add(Integer.parseInt(String.valueOf(board[row][column])));
                }
            }
            if(!isRepeat(currentColumn)){
                return false;
            }
        }
        return true;
    }
    private boolean checkVertical(char[][] board){
        for (int column = 0; column < board[0].length; column++) {
            List<Integer> currentNumbers = new ArrayList<>();
            for (int row = 0; row < board.length ; row++) {
                if(!(board[row][column] == '.')) {
                    currentNumbers.add(Integer.parseInt(String.valueOf(board[row][column])));
                }
            }
            if(!isRepeat(currentNumbers)){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        String[] words = s.split("[^a-zA-Z\\d]");
        StringBuilder currentWord = new StringBuilder();
        for (String word : words) {
            currentWord.append(word);
        }
        currentWord = new StringBuilder(currentWord.toString().toLowerCase());
        return currentWord.toString().equals(currentWord.reverse().toString());

    }



    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;

        HashMap<String, Integer > currentLetters = getAllLetter(s);


        for (int i = 0; i <t.length() ; i++) {

            String currentCharacter = String.valueOf(t.charAt(i));
            if(currentLetters.containsKey(currentCharacter)){
                currentLetters.put(currentCharacter,currentLetters.get(currentCharacter) - 1);
            }else{
                return false;
            }


        }

        if(haveValueDifferentByZero(currentLetters)){
            return false;
        }



        return true;
    }
    private HashMap<String, Integer > getAllLetter(String word){
        HashMap<String, Integer > map = new HashMap<>();
        for (int i = 0; i <word.length() ; i++) {
            if(map.containsKey(String.valueOf(word.charAt(i)))){
                map.put(String.valueOf(word.charAt(i)),map.get(String.valueOf(word.charAt(i))) + 1);
            }else{
                map.put(String.valueOf(word.charAt(i)),1);
            }
        }
        return map;
    }
    private boolean haveValueDifferentByZero(  HashMap<String, Integer > map){


        for (String key : map.keySet()) {
            if(map.get(key) != 0){
                return true;
            }
        }

        return false;
    }
}

