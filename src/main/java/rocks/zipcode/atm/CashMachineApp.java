package rocks.zipcode.atm;

import javafx.scene.control.*;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();

    private TextField email = new TextField();

    private TextField deposit = new TextField();

    private TextField withdraw = new TextField();

    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(20);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        FlowPane flowpane = new FlowPane();

        Button emailSubmit = new Button("Email Login");
        Button btnSubmit = new Button("Set Account ID");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnExit = new Button("Exit");

        emailSubmit.setOnAction(e -> {
            String emailAddress = email.getText();
            cashMachine.login(emailAddress);

            areaInfo.setText(cashMachine.toString());

            flowpane.getChildren().add(btnDeposit);
            flowpane.getChildren().add(btnWithdraw);
            flowpane.getChildren().remove(btnSubmit);
            flowpane.getChildren().remove(emailSubmit);

            deposit.setText("Deposit Amount");
            vbox.getChildren().add(deposit);

            withdraw.setText("Withdraw Amount");
            vbox.getChildren().add(withdraw);

        });


        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());

            flowpane.getChildren().add(btnDeposit);
            flowpane.getChildren().add(btnWithdraw);
            flowpane.getChildren().remove(btnSubmit);
            flowpane.getChildren().remove(emailSubmit);

            deposit.setText("Deposit Amount");
            vbox.getChildren().add(deposit);

            withdraw.setText("Withdraw Amount");
            vbox.getChildren().add(withdraw);
        });

        MenuItem basic =new MenuItem("Basic");
        MenuItem premium = new MenuItem("Premium");
        MenuItem ksa = new MenuItem("KSA");
        MenuButton menuButton =new MenuButton("Account Type",null,basic,premium,ksa);
        Label accountselected = new Label("No Account selected");
        basic.setOnAction(e ->{
            accountselected.setText("Basic Account");


        });
        premium.setOnAction(e ->{
            accountselected.setText("Premium Account");

        });
        ksa.setOnAction(e ->{
            accountselected.setText("Kids Savings Account");

        });



        //Button btnDeposit = new Button("Deposit");

        btnDeposit.setOnAction(e -> {

            int amount = Integer.parseInt(deposit.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        btnWithdraw.setOnAction(e -> {

            int amount = Integer.parseInt(withdraw.getText());

            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());

            flowpane.getChildren().add(btnSubmit);
            flowpane.getChildren().add(emailSubmit);
            flowpane.getChildren().remove(btnWithdraw);
            flowpane.getChildren().remove(btnDeposit);

            vbox.getChildren().remove(deposit);
            vbox.getChildren().remove(withdraw);

        });

        email.setText("Email Address");
        flowpane.getChildren().add(emailSubmit);

        field.setText("ID Login");
        flowpane.getChildren().add(btnSubmit);

        flowpane.getChildren().add(btnExit);

        flowpane.getChildren().add(menuButton);
        vbox.getChildren().addAll(field, email,flowpane,accountselected, areaInfo);

        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
