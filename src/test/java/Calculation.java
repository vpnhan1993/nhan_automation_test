public class Calculation {

    private String operation;
    private String number1;
    private String number2;
    private String result;

    public String getResult() {
        return result;
    }

    public Calculation(String operation, String number1, String number2) {
        this.operation = operation;
        this.number1 = number1;
        this.number2 = number2;
    }


    public void doCalculate() {
        this.result = String.valueOf(Integer.parseInt(number1) + Integer.parseInt(number2));
    }
}
