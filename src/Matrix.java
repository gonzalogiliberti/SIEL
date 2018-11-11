import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Matrix implements ActionListener {

    int col = 0 , row = 0;
    int newCols, newRows;
    JOptionPane window;
    JTextField rowsField;
    JTextField colsField;
    JPanel mainPanel;
    JPanel matrixPanel, matrixAPanel;
    JPanel[] rowsPanels = null;
    JPanel[] newRowsPanels = null;
    JTextField[][] matrixAField, matrixXField, matrixBField;

    private double matrixA, matrixB;


    @Override
    public void actionPerformed(ActionEvent e) {

//        if (e.getSource() == showMatrix) {
//            showMatrix(myMatrix, "Your Matrix");
//        }
//        if (e.getSource() == plusB) {
//            matrixPlusMatrix();
//        } else if (e.getSource() == minusB) {
//            matrixMinusMatrix();
//        } else if (e.getSource() == multiplyB) {
//            multiplyByMatrix();
//        } else if (e.getSource() == inverseB) {
//            inverse();
//        } else if (e.getSource() == nMultiplyB) {
//            guiMultliplyByScaler();
//        } else if (e.getSource() == nDivisionB) {
//            divideByScaler();
//        } else if (e.getSource() == transposing) {
//            guiTransposing(myMatrix);
//        } else if (e.getSource() == getValueB) {
//            guiGetValue();
//        } else if (e.getSource() == newMatrix) {
//            newMatrix();
//        }
    }//end action performed


    Matrix() {
//        super("Matrix");
//        setLayout(new BorderLayout());
        mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        add(mainPanel,BorderLayout.CENTER);

        rowsField = new JTextField("2",3); //lenght field
        colsField = new JTextField("2",3); //col field
        JButton createMatrix = new JButton("Setear Tamaño de la matriz");
        createMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(colsField.getText()) < 0 || Integer.parseInt(rowsField.getText()) < 0){
                    //TODO mostrar error
                    return;
                }
                newCols = Integer.parseInt(colsField.getText());
                newRows = Integer.parseInt(rowsField.getText());
                rowsPanels = new JPanel[newRows];
//                matrixAPanel = new JPanel(new GridLayout(newRows, newCols));
                matrixAField = new JTextField[newRows][newCols];

                matrixPanel.add(Box.createVerticalBox());
                for(int i = 0; i< newRows; i++) {
                    rowsPanels[i] = new JPanel();
                    for(int j = 0; j < newRows; j++) {
                        matrixAField[i][j] = new JTextField(5);
                        rowsPanels[i].add(matrixAField[i][j]);
                        rowsPanels[i].add(Box.createVerticalStrut(2));
                    }
                    matrixPanel.add(rowsPanels[i]);
                    matrixPanel.add(Box.createVerticalBox());
                }
                matrixPanel.revalidate();
                matrixPanel.repaint();
                mainPanel.revalidate();
                mainPanel.revalidate();
//                validate();

            }
        });

        //Select matrixAField size panel
        JPanel choosePanel[] = new JPanel[3];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new JLabel("Enter Dimensitions"));
        mainPanel.add(choosePanel[0]);
        choosePanel[1] = new JPanel();
        choosePanel[1].add(new JLabel("Rows:"));
        choosePanel[1].add(rowsField);
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        choosePanel[1].add(new JLabel("Cols:"));
        choosePanel[1].add(colsField);
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        choosePanel[1].add(createMatrix); // a spacer
        mainPanel.add(choosePanel[1]);

        matrixPanel = new JPanel();
        choosePanel[2] = matrixPanel;
        mainPanel.add(choosePanel[2]);
//        choosePanel[2].add(new JLabel("Matriz A"));
//        choosePanel[2].add(Box.createHorizontalStrut(15)); // a spacer
//        choosePanel[2].add(new JLabel("Matriz X"));
//        choosePanel[2].add(Box.createHorizontalStrut(15)); // a spacer
//        choosePanel[2].add(new JLabel("Matriz B"));

        JOptionPane.showConfirmDialog(null, mainPanel, "SIEL",
                JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

//        add(mainPanel,BorderLayout.CENTER);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(500,500);
//        setVisible(true);
    }



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

    public static void main(String[] args) {

        Matrix m1 = new Matrix();

        System.out.println("Hello World!");
    }
}
