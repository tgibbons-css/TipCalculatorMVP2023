package cis3334.tipcalculatormvp2023;

public class TipCalculator implements CalculatorInterface{

    private updateViewInterface mainView;
    private Double bill;
    private Integer numPeople;
    private Double totalTip;
    private Double tipPerPerson;
    public static final Double LOW_TIP_RATE = 0.10;
    public static final Double HIGH_TIP_RATE = 0.20;


    TipCalculator(updateViewInterface mainView){
        this.mainView = mainView;
    }

    @Override
    public void calculate(Double bill, Integer numPeople, boolean goodService) {
        this.bill = bill;
        this.numPeople = numPeople;
        if (goodService) {
            totalTip = HIGH_TIP_RATE * bill;
            tipPerPerson = totalTip / numPeople;
        } else {
            totalTip = LOW_TIP_RATE * bill;
            tipPerPerson = totalTip / numPeople;
        }
        mainView.updateView(totalTip,tipPerPerson);
    }


}
