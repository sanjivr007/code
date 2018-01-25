package crazystack;

import java.util.Arrays;
import java.util.Scanner;

public class StringWar {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstString = sc.nextLine();
        String secondString = sc.nextLine();

        char firstCharArray[] = firstString.toCharArray();
        char secondCharArray[] = secondString.toCharArray();
        Arrays.sort(firstCharArray);
        Arrays.sort(secondCharArray);

        boolean result = canAnyWinWar(firstCharArray, secondCharArray);
        System.out.println(result);
    }

    private static boolean canAnyWinWar(char[] firstCharArray, char[] secondCharArray) {
        int flag = -1;
        boolean result = true;
        //TODO ASSUMING BOTH STRING HAVE SAME LENGTH
        for (int itr = 0; itr < firstCharArray.length; itr++) {
            if (flag == 1) {
                if (firstCharArray[itr] < secondCharArray[itr]) {
                    result = false;
                    break;
                }
            } else if (flag == 2) {
                if (secondCharArray[itr] < firstCharArray[itr]) {
                    result = false;
                    break;
                }
            } else {
                if (firstCharArray[itr] < secondCharArray[itr]) {
                    flag = 2;
                } else {
                    if (firstCharArray[itr] > secondCharArray[itr]) {
                        flag = 1;
                    }
                }
            }


        }
        return result;
    }
}