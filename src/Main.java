import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import static java.lang.Integer.parseInt;

/**
 * @author Anton Ezhov
 * @version 25.12.2017.
 * @link https://github.com/m1ndz/
 */

public class Main {
    private static int indexArray1;
    private static int indexArray2;

    public static void main(String[] args) {
        String[][] notNumberArray = new String[4][4];
        String[][] wrongSizeArray = new String[5][7];
        notNumberArray = fillTheArray(notNumberArray);
        notNumberArray[2][1] = "Z";
        String[][] array = new String[4][4];
        array = fillTheArray(array);
        String[][] textArray = new String[4][4];
        try {
            textArray = fillTheArrayFromFile(textArray);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                //System.out.println(sumOfNumbers(wrongSizeArray));
                //System.out.println(sumOfNumbers(notNumberArray));
                System.out.println(sumOfNumbers(array));
                System.out.println(sumOfNumbers(textArray));
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Size of the array is not correct!");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("The program had been successfully executed.");
    }

    public static int sumOfNumbers(String[][] array) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        if (array.length != 4 || array[0].length != 4 || array[1].length != 4 || array[2].length != 4 ||
                array[3].length != 4) throw new ArrayIndexOutOfBoundsException();
        try {
            return convertToNumber(array);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("The cell has to contain a number! [" + indexArray1 + "] ["
                    + indexArray2 + "]");
        }
    }

    public static String[][] fillTheArray(String[][] arr) {
        int a = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.toString(a);
                a++;
            }
        }
        return arr;
    }

    public static int convertToNumber(String[][] arr) throws NumberFormatException {
        int amount = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                indexArray1 = i;
                indexArray2 = j;
                amount += parseInt(arr[i][j]);
            }
        }
        return amount;
    }

    public static String[][] fillTheArrayFromFile(String[][] arr) throws IOException {
        int a;
        FileReader file = new FileReader("C://IdeaProjects/hw2/src/text.txt");
        char text = ' ';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String s = "";
                while ((a = file.read()) != -1) {
                    if (text == (char) a) {
                        break;
                    } else {
                        s += Character.toString((char) a);
                    }
                }
                arr[i][j] = s;
            }
        }
        file.close();
        return arr;
    }
}