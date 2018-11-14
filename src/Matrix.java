
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Matrix implements ActionListener {
    private static int col, row;  //dimentions
    private static double matrixA[][];
    private static double matrixB[][];
    private static char matrixX[];
    private static double tempMatrix[][];
    private static JTextField inputField[][];
    private static int result;
    private static JButton minusB, plusB, inverseB,
            multiplyB, nMultiplyB, nDivisionB,
            getValueB, showMatrix, transposing,
            newMatrixA, newMatrixB, newMatrixX;
    private static JPanel choosePanel[] = new JPanel[6];
    private static int lastCol, lastRow;
    double cotaParo;

    /*
     * Methods:
     * 1- private static void getDimension()
     *    for taking matrix's dimensitions
     * 2- private static void setElements(double matrix [][], String title )
     *    for filling matrix's elements
     * 3- private static void checkTextField (JTextField field [][] )
     *     For setting spaced fields as zeors
     * 4- private void ChooseOperation () for choising an operation to be applied
     *    to the matrix
     * 5- private void actionPerformed(ActionEvent e)
     *    Output methods:
     * 6- Matrix () - constructor
     *    for invoking program's process
     * 7 - public void actionPerformed(ActionEvent e)
     *    for setting buttons performance
     * 8 - private static void showMatrix(double [][] matrix, String title )
     *    for showing the matrix (matrix) with the title (title)
     * 9 - private static void matrixPlusMatrix ()
     *    Do a plusing operation of matrix with other matrix
     * 10 - private static void matrixMinusMatrix ()
     *    Do a subtracting operation of matrix and other matrix
     * 11 - private static void multiplyByMatrix ()
     *    Do a multiplication operation between matrix and other matrix
     * 12 - private static void guiMultliplyByScaler ()
     *    Do a multiplication operation between matrix and a nnumber
     *    by using multliplyByScaler method and show that for the user
     * 13 - private static double [][] multliplyByScaler (double [][] matrix , double x)
     *    but only inner the program
     * 14 - private static void divideByScaler ()
     *      Do a dividing operation of matrix and other matrix
     * 15 - private static void guiGetValue ()
     *      getting determinaiton's values by using getValue method
     *      and show it to the user
     * 16 - private static double getValue (double [][] matrix)
     *      return determination's values but only inner the program
     * 17 - private static void guiTransporter (double [][] matrix)
     *      getting determination's values by using transporter method
     *      and show it the user
     * 18 - private static double [][] transporter (double [][] matrix)
     *      return determinations's transporter but only inner the program
     * 19 - getMinor return the minor of cofactors in order to get
     *      inversing matrix
     * 20 - private static void swap (double [] res1, double [] res2)
     *      for swaping two rows in order using to to get determination's value
     * 21 - private static void  inverse  ()
     *      Do inversing operation for determinations
     * 22-  private static void newMatrixA ()
     *      Enable the user to enter a new matrix and use the program's
     *      features on it
     * 23-  public static void main (String [] args)
     *      For invoking the program
     */

    Matrix() {
        col = row = 0;
        matrixA = new double[0][0];
        ChooseOperation();
    }


    //prompting for matrix's dimensions
    private static void getDimension() {
        JTextField lField = new JTextField(5); //lenght field
        JTextField wField = new JTextField(5); //col field

        //design input line
        JPanel choosePanel[] = new JPanel[2];
        choosePanel[0] = new JPanel();
        choosePanel[1] = new JPanel();
        choosePanel[0].add(new JLabel("Enter Dimensitions"));
        choosePanel[1].add(new JLabel("Rows:"));
        choosePanel[1].add(lField);
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        choosePanel[1].add(new JLabel("Cols:"));
        choosePanel[1].add(wField);

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        //save last dimensions
        lastCol = col;
        lastRow = row;

        //ok option
        if (result == 0) {

            if (wField.getText().equals(""))
                col = 0;
            else {
                if (isInt(wField.getText())) {
                    col = Integer.parseInt(wField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                    col = lastCol;
                    row = lastRow;
                    return;
                }

                if (isInt(lField.getText())) {
                    row = Integer.parseInt(lField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                    col = lastCol;
                    row = lastRow;
                    return;
                }

            }
            if (col < 1 || row < 1) {
                JOptionPane.showConfirmDialog(null, "You entered wrong dimensions",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                col = lastCol;
                row = lastRow;

            } else {
                tempMatrix = matrixA;
                matrixA = new double[row][col];
                if (!setElements(matrixA, "Fill your new matrix")) //filling the new matrix
                {
                    //backup

                    matrixA = tempMatrix;
                }
            }
        } else if (result == 1) {
            col = lastCol;
            row = lastRow;
        }
    }//end get Dimension

    //setting a matrix's elementis
    private static boolean setElements(double matrix[][], String title) {
        int temp, temp1;             //temprature variable
        String tempString;

        JPanel choosePanel[] = new JPanel[row + 2];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new Label(title));
        choosePanel[choosePanel.length - 1] = new JPanel();
        choosePanel[choosePanel.length - 1].add(new Label("consider space field as zeros"));
        inputField = new JTextField[matrix.length][matrix[0].length];


        //lenght loop
        for (temp = 1; temp <= matrix.length; temp++) {
            choosePanel[temp] = new JPanel();


            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                inputField[temp - 1][temp1] = new JTextField(3);
                choosePanel[temp].add(inputField[temp - 1][temp1]);

                if (temp1 < matrix[0].length - 1) {
                    choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
                }

            }//end col loop

        }//end row loop

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);


        if (result == 0) {
            checkTextField(inputField);
            for (temp = 0; temp < matrix.length; temp++) {
                for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                    tempString = inputField[temp][temp1].getText();


                    if (isDouble(tempString)) {
                        matrix[temp][temp1] = Double.parseDouble(inputField[temp][temp1].getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "You entered wrong elements");

                        //backup
                        col = lastCol;
                        row = lastRow;

                        return false;
                    }
                }
            }
            return true;
        } else
            return false;


    }//end get Inputs

    //for setting spaced fields as zeros
    private static void checkTextField(JTextField field[][]) {
        for (int temp = 0; temp < field.length; temp++) {
            for (int temp1 = 0; temp1 < field[0].length; temp1++) {
                if (field[temp][temp1].getText().equals(""))
                    field[temp][temp1].setText("0");
            }
        }
    }//end reset

    private void ChooseOperation() {
        int temp;


        for (temp = 0; temp < choosePanel.length; temp++) {
            choosePanel[temp] = new JPanel();
        }


        choosePanel[0].add(Box.createHorizontalStrut(30)); // a spacer

//        choosePanel[6].add(Box.createHorizontalStrut(15)); // a spacer


        // Boton para setear la matriz A
        newMatrixA = new JButton("Nueva Matriz A");
        newMatrixA.setPreferredSize(new Dimension(175, 35));
        newMatrixA.addActionListener(this);
        choosePanel[1].add(newMatrixA);

        // Boton para setear la matriz B
        newMatrixB = new JButton("Nueva Matriz B");
        newMatrixB.setPreferredSize(new Dimension(175, 35));
        newMatrixB.addActionListener(this);
        choosePanel[1].add(newMatrixB);

        // Boton para cambiar los  valores default de la Matriz X
        newMatrixX = new JButton("Setear Matriz X");
        newMatrixX.setPreferredSize(new Dimension(175, 35));
        newMatrixX.addActionListener(this);
        choosePanel[1].add(newMatrixX);


        showMatrix = new JButton("Mostrar la matriz A");
        showMatrix.setPreferredSize(new Dimension(175, 35));
        showMatrix.addActionListener(this);
        choosePanel[2].add(showMatrix);

        plusB = new JButton("Mostrar la matriz X");
        plusB.setPreferredSize(new Dimension(175, 35));
        plusB.addActionListener(this);
        choosePanel[2].add(plusB);

        minusB = new JButton("Mostrar la matriz B");
        minusB.setPreferredSize(new Dimension(175, 35));
        minusB.addActionListener(this);
        choosePanel[2].add(minusB);

        multiplyB = new JButton("Multiplying by matrix");
        multiplyB.setPreferredSize(new Dimension(175, 35));
        multiplyB.addActionListener(this);
        choosePanel[3].add(multiplyB);

        nMultiplyB = new JButton("Multiplying by scaler");
        nMultiplyB.setPreferredSize(new Dimension(175, 35));
        nMultiplyB.addActionListener(this);
        choosePanel[3].add(nMultiplyB);

        nDivisionB = new JButton("Dividing by scaler");
        nDivisionB.setPreferredSize(new Dimension(175, 35));
        nDivisionB.addActionListener(this);
        choosePanel[3].add(nDivisionB);


        transposing = new JButton("Transposing");
        transposing.setPreferredSize(new Dimension(175, 35));
        transposing.addActionListener(this);
        choosePanel[4].add(transposing);


        if (col == row) {
            getValueB = new JButton("GET Value");
            getValueB.setPreferredSize(new Dimension(175, 35));
            getValueB.addActionListener(this);
            choosePanel[4].add(getValueB);

            inverseB = new JButton("Inversing");
            inverseB.setPreferredSize(new Dimension(175, 35));
            inverseB.addActionListener(this);
            choosePanel[4].add(inverseB);
        }


        JOptionPane.showConfirmDialog(null, choosePanel, "SIEL",
                JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showMatrix) {
            showMatrix(matrixA, "Matriz A");
        } else if (e.getSource() == newMatrixA) {
            newMatrix();
        } else if (e.getSource() == newMatrixB) {
            newMatrixB();
        } else if (e.getSource() == multiplyB) {
            multiplyByMatrix();
        } else if (e.getSource() == inverseB) {
            inverse();
        } else if (e.getSource() == nMultiplyB) {
            guiMultliplyByScaler();
        } else if (e.getSource() == nDivisionB) {
            divideByScaler();
        } else if (e.getSource() == transposing) {
            guiTransposing(matrixA);
        } else if (e.getSource() == getValueB) {
            guiGetValue();
        } else if (e.getSource() == newMatrixA) {

        }
    }//end action performed


    private static void showMatrix(double[][] matrix, String title) {
        int temp, temp1;             //temprature variable

        JPanel choosePanel[] = new JPanel[matrix.length + 1];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new JLabel(title));

        for (temp = 1; temp <= matrix.length; temp++) {
            choosePanel[temp] = new JPanel();


            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                if (matrix[temp - 1][temp1] == -0) {
                    matrix[temp - 1][temp1] = 0;
                }
                choosePanel[temp].add(new JLabel(String.format("%.2f", matrix[temp - 1][temp1])));

                if (temp1 < matrix[0].length - 1) {
                    choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
                }

            }//end col loop

        }//end row loop

        if (col == 0 || row == 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una matriz primero.");
        } else {

            JOptionPane.showMessageDialog(null, choosePanel, null,
                    JOptionPane.PLAIN_MESSAGE, null);
        }
    }//end show Matrix

    private static void matrixPlusMatrix() {
        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        } else {
            double m2[][] = new double[row][col];
            double sum[][] = new double[row][col];

            if (setElements(m2, "Fill Aditional Matrix")) {

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        sum[i][j] = matrixA[i][j] + m2[i][j];
                    }
                }
                showMatrix(sum, "Summition Result");
            }
        }//end else checking
    }//end plus matrix

    private static void matrixMinusMatrix() {

        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        } else {
            double m2[][] = new double[row][col];
            double sub[][] = new double[row][col];
            double temp[][] = new double[row][col];


            if (setElements(m2, "Fill Subtracting Matrix")) {

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        temp[i][j] = (-1 * m2[i][j]);
                        sub[i][j] = matrixA[i][j] + temp[i][j];
                    }
                }
                showMatrix(sub, "Subtracting Result");
            }
        }//end else of checking
    }


    private static void multiplyByMatrix() {

        JTextField wField = new JTextField(5); //col field
        int col2 = 0;
        double m2[][], results[][];
        int sum;

        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        } else {

            //design input line
            JPanel choosePanel[] = new JPanel[2];
            choosePanel[0] = new JPanel();
            choosePanel[1] = new JPanel();

            choosePanel[0].add(new JLabel("Enter Dimensitions"));

            choosePanel[1].add(new JLabel("Rows:"));
            choosePanel[1].add(new JLabel("" + col));
            choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
            choosePanel[1].add(new JLabel("Cols:"));
            choosePanel[1].add(wField);


            result = JOptionPane.showConfirmDialog(null, choosePanel,
                    null, JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == 0) {
                if (wField.getText().equals("")) {
                    col2 = 0;
                } else {
                    if (isInt(wField.getText())) {
                        col2 = Integer.parseInt(wField.getText());

                    }
                }

                m2 = new double[col][col2];
                results = new double[row][col2];
                if (setElements(m2, "Fill multiplying matrix")) {

                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col2; j++) {
                            sum = 0;
                            for (int k = 0; k < col; k++) {
                                sum += matrixA[i][k] * m2[k][j];
                            }

                            results[i][j] = sum;

                        }
                    }

                    showMatrix(results, "Mulitiplication Result");
                }//elements checking
            }//dimensions checking
            else
                return;
        }//end else of checking
    }//end multiply by matrix method

    private static void guiMultliplyByScaler() {

        double[][] results = new double[row][col];
        double x;
        String tempString;

        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return;
        }

        tempString = JOptionPane.showInputDialog(null,
                "Enter the scaler number for multiplying");

        if (tempString == null) //cancel option
        {
            return;
        } else if (!tempString.equals(""))
            x = Double.parseDouble(tempString);
        else {
            JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
            return;
        }
        results = multliplyByScaler(matrixA, x);
        showMatrix(results, "Multiplication Result");

    }//end multiply by number


    private static double[][] multliplyByScaler(double[][] matrix, double x) {

        double[][] results = new double[row][col];
        int i, j;

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                results[i][j] = x * matrix[i][j];
            }
        }
        return results;
    }//end multiply by number


    private static void divideByScaler() {
        double[][] results = new double[row][col];
        int i, j;
        double x;
        String tempString;

        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return;
        }

        //prompting for the scaler
        tempString = JOptionPane.showInputDialog("Enter the scaler number for dividing");


        if (tempString == null) //cancel option
        {
            return;
        } else if (!tempString.equals(""))
            x = Double.parseDouble(tempString);

        else {
            JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
            return;
        }

        if (x == 0) {
            JOptionPane.showMessageDialog(null, "Excuse me we can't divid by 0");
            return;
        }

        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                results[i][j] = matrixA[i][j] / x;
            }
        }
        showMatrix(results, "Dividing Result");


    }//end dividing by number

    private static void guiGetValue() {
        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        } else if (col != row) {
            JOptionPane.showMessageDialog(null, "You must enter square matrix");
        } else {
            double result = getValue(matrixA);

            JOptionPane.showMessageDialog(null, String.format("Determination's Value = %.2f",
                    getValue(matrixA)), null,
                    JOptionPane.PLAIN_MESSAGE, null);
        }
    }//end gui get Value

    private static void swap(double[] res1, double[] res2) {
        int temp;
        double tempDouble;

        for (temp = 0; temp < res1.length; temp++) {
            tempDouble = res1[temp];
            res1[temp] = res2[temp];
            res2[temp] = tempDouble;
        }

    }

    private static double getValue(double[][] matrix) {
        int temp, temp1, temp2;
        double coeficient;
        double result = 1;
        int sign = 1;       //1 for positive
        //-1 for negative
        int zeroCounter;

        double res[][] = new double[matrix.length][matrix[0].length];

        //copy matrix
        for (temp = 0; temp < matrix.length; temp++) {
            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                res[temp][temp1] = matrix[temp][temp1];
                ;
            }

        }

        //rearrange rows
        for (temp = 0; temp < res.length; temp++) {
            if (res[temp][temp] != 0)
                continue;

            for (temp1 = 1; temp1 < res.length - 1; temp1++) {
                if (res[(temp1 + temp) % matrix.length][temp] != 0) {       //swapping
                    swap(res[temp], res[(temp1 + temp) % res.length]);
                    sign *= -1;
                    break;
                }
            }
        }


        //starting simplifing with gaues method
        for (temp = 1; temp < res.length; temp++) {
            for (temp1 = 0; temp1 < temp; temp1++) {
                //if required element = 0 || division = 0
                if (res[temp][temp1] == 0 || res[temp1][temp1] == 0)
                    continue;
                else {
                    zeroCounter = 0;
                    coeficient = res[temp][temp1] / res[temp1][temp1];
                }
                for (temp2 = 0; temp2 < res.length; temp2++) {
                    res[temp][temp2] = res[temp][temp2]
                            - res[temp1][temp2] * coeficient;

                    if (res[temp][temp2] == 0)
                        zeroCounter++;
                }
                //over flow zeros
                if (temp < res.length - 1 && zeroCounter > temp) {
                    swap(res[temp], res[temp + 1]);
                    sign *= -1;
                    temp--;
                }
            }
        }

        for (temp = 0; temp < res.length; temp++) {
            result *= res[temp][temp];
        }
        return result * sign;
    }//end getValue


    private static void guiTransposing(double[][] matrix) {
        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return;
        }


        double[][] transMatrix = new double[matrix[0].length][matrix.length];

        transMatrix = transposing(matrix);

        showMatrix(transMatrix, "Trasnposing Matrix");
    }

    private static double[][] transposing(double[][] matrix) {
        double[][] transportMatrix = new double[matrix[0].length][matrix.length];
        int temp1, temp; //termprature variable

        for (temp = 0; temp < matrix[0].length; temp++) {
            for (temp1 = 0; temp1 < matrix.length; temp1++) {
                transportMatrix[temp][temp1] =
                        matrix[temp1][temp]; //swap (temp, temp1)
            }
        }
        return transportMatrix;
    }


    private static double[][] getMinor(int i, int j) {
        // i for order in row
        //j for order in col
        double[][] results = new double[row - 1][col - 1];
        int row_count = 0, col_count = 0;
        int temp, temp1;

        for (temp = 0; temp < row; temp++) {
            for (temp1 = 0; temp1 < col; temp1++) {
                if (temp != i && temp1 != j) {
                    results[row_count][col_count] = matrixA[temp][temp1];
                    col_count++;
                }
            }//end col loop
            col_count = 0;
            if (i != temp)
                row_count++;
        }//end row loop

        return results;
    }


    private static void inverse() {
        if (matrixA.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return;
        } else if (col != row) {
            JOptionPane.showMessageDialog(null, "You must enter square matrix");
            return;
        } else if (getValue(matrixA) == 0) {
            JOptionPane.showMessageDialog(null, "Your Matrix "
                    + "hasn't an inverse one\n\n"
                    + "Because its value = 0");
            return;
        }

        double[][] inverseMatrix = new double[row][col];
        double[][] minor = new double[row - 1][col - 1];
        double[][] cofactor = new double[row][col];
        double delta; //matrixA value
        int temp, temp1;


        //get cofactor
        for (temp = 0; temp < row; temp++) {
            for (temp1 = 0; temp1 < col; temp1++) {
                minor = getMinor(temp, temp1);
                double minorValue = getValue(minor);
                cofactor[temp][temp1] = Math.pow(-1.0, temp + temp1) * getValue(minor);
            }
        }//end cofactor looping

        //transport cofactor to get ADJ
        cofactor = transposing(cofactor);
        delta = getValue(matrixA);       //count Matrix's

        for (temp = 0; temp < row; temp++) {
            for (temp1 = 0; temp1 < col; temp1++) {
                inverseMatrix[temp][temp1] = cofactor[temp][temp1] / delta;
            }
        }

        showMatrix(inverseMatrix, "Inversing Matrix");

    }//end inverse

    private static boolean isInt(String str) {
        int temp;
        if (str.length() == '0')
            return false;

        for (temp = 0; temp < str.length(); temp++) {
            if (str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && !Character.isDigit(str.charAt(temp))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDouble(String str) {
        int temp;
        if (str.length() == '0')
            return false;

        for (temp = 0; temp < str.length(); temp++) {
            if (str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && str.charAt(temp) != '.'
                    && !Character.isDigit(str.charAt(temp))
            ) {
                return false;
            }
        }
        return true;
    }

    private static void newMatrix() {
        getDimension();
    }

    private static void newMatrixB() {
        if (row != 0 && col != 0){
            matrixB = new double[row][1];
            setElements(matrixB, "aaa");
        }
    }

    public static void main(String[] args) {
        Matrix m1 = new Matrix();

    }

    public boolean isStrictlyDiagonalyDominant() {
        double sum = 0;
        boolean result = true;
        if (row != col) {
            return false;
        }
        for (int i = 0; i < row; i++) {
            for (int it = 0; it < col; it++) {
                if (i != it) {
                    double aux = matrixA[i][it];
                    if (aux < 0) {
                        sum += (-1) * aux;
                    } else {
                        sum += aux;
                    }
                }
            }
            if (sum > matrixA[i][i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isDiagonalyDominant() {
        double sum = 0;
        boolean result = true;
        if (row != col) {
            return false;
        }
        for (int i = 0; i < row; i++) {
            for (int it = 0; it < col; it++) {
                if (i != it) {
                    double aux = matrixA[i][it];
                    if (aux < 0) {
                        sum += (-1) * aux;
                    } else {
                        sum += aux;
                    }
                }
            }
            if (sum >= matrixA[i][i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public double getInfiniteNorm() {
        ArrayList<Double> norma = new ArrayList<Double>();
        for (int i = 0; i < row; i++) {
            double sum = 0;
            for (int it = 0; it < col; it++) {
                double aux = matrixA[i][it];
                if (aux < 0) {
                    sum += (-1) * aux;
                } else {
                    sum += aux;
                }
            }
            norma.add(sum);
        }
        double aux = 0;
        for (int i = 0; i < norma.size(); i++) {
            if (norma.get(i) > aux) {
                aux = norma.get(i);
            }
        }
        return aux;
    }

    public double getOneNorm() {
        ArrayList<Double> norma = new ArrayList<Double>();
        for (int i = 0; i < col; i++) {
            double sum = 0;
            for (int it = 0; it < row; it++) {
                double aux = matrixA[it][i];
                if (aux < 0) {
                    sum += (-1) * aux;
                } else {
                    sum += aux;
                }
            }
            norma.add(sum);
        }
        double aux = 0;
        for (int i = 0; i < norma.size(); i++) {
            if (norma.get(i) > aux) {
                aux = norma.get(i);
            }
        }
        return aux;
    }

    public void gaussSeidel(double[] vectorInicial){
        int iterations = 0;
        double epsilon = cotaParo;

        double[][] M = new double[row][col+1];
        for(int i = 0; i < row; i++) {
            for (int it = 0; it < col; it++){
                M[i][it] = matrixA[i][it];
            }
            M[i][col+1] = matrixB[i][1];
        }

        int n = M.length;
        double[] X = vectorInicial; // Approximations
        double[] P = new double[n]; // Prev

        while (true)
        {
            for (int i = 0; i < n; i++)
            {
                double sum = M[i][n]; // b_n

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * X[j];

                // Update x_i to use in the next row calculation
                X[i] = 1/M[i][i] * sum;
            }

            System.out.print("X_" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");

            iterations++;
            if (iterations == 1)
                continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;

            if (stop ) break;
            P = X.clone();
        }
    }
}//end class

