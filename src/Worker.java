import javax.swing.*;

public class Worker {

    private JTextField inputField;
    private JTextField outputField;
    private JTextField orderField;

    private String[] words;

    public Worker(JTextField inputField, JTextField outputField, JTextField orderField) {
        this.inputField = inputField;
        this.outputField = outputField;
        this.orderField = orderField;
    }

    public void nextButtonPressed() {
        String order = orderField.getText();
        int[] perm;
        if (order.length() > 0) {
            perm = nextPermutation(stringToIntArray(order));
        } else {
            perm = getNumberSet();
        }
        if (perm != null) {
            outputPermutation(perm);
        }
    }

    public void prevButtonPressed() {
        String order = orderField.getText();
        int[] perm;
        if (order.length() > 0) {
            perm = previousPermutation(stringToIntArray(order));
        } else {
            perm = getNumberSet();
        }
        if (perm != null) {
            outputPermutation(perm);
        }
    }

    public void wordsUpdated() {
        words = inputField.getText().split(" ");
    }

    private int[] stringToIntArray(String input) {
        String[] arr = input.split(" ");
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = new Integer(arr[i]);
        }
        return res;
    }

    private String intArrayToString(int[] arr) {
        String res = "";
        for (int a: arr) {
            res += a + " ";
        }
        return res.trim();
    }

    private int[] nextPermutation(int[] perm) {
        int limit = perm.length;
        for (int i = limit - 1; i > -1; i--) {
            for (int j = perm[i] + 1; j <= limit; j++) {
                if (!isUsedBefore(perm, i, j)) {
                    perm[i] = j;
                    //JOptionPane.showMessageDialog(MainForm.frame, i + " " + j + "; " +  intArrayToString(perm));
                    for (int n = i + 1; n < limit; n++) {
                        for (int m = 1; m <= limit; m++) {
                            if (!isUsedBefore(perm, n, m)) {
                                perm[n] = m;
                                break;
                            }
                        }
                    }
                    return perm;
                }
            }
        }
        return null;
    }

    private int[] previousPermutation(int[] perm) {
        int limit = perm.length;
        for (int i = limit - 1; i > -1; i--) {
            for (int j = perm[i] - 1; j > 0; j--) {
                if (!isUsedBefore(perm, i, j)) {
                    perm[i] = j;
                    //JOptionPane.showMessageDialog(MainForm.frame, i + " " + j + "; " +  intArrayToString(perm));
                    for (int n = i + 1; n < limit; n++) {
                        for (int m = limit; m > 0; m--) {
                            if (!isUsedBefore(perm, n, m)) {
                                perm[n] = m;
                                break;
                            }
                        }
                    }
                    return perm;
                }
            }
        }
        return null;
    }

    private boolean isUsedBefore(int[] arr, int index, int number) {
        for (int i = 0; i < index; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }

    private void outputPermutation(int[] order) {
        orderField.setText(intArrayToString(order));
        String res = "";
        for (int index : order) {
            res += words[index-1] + " ";
        }
        outputField.setText(res.trim());
    }

    private int[] getNumberSet() {
        int[] arr = new int[words.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }
        return arr;
    }

}
