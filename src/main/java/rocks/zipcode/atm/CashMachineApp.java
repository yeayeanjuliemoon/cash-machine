package rocks.zipcode.atm;

import javafx.scene.control.*;
import rocks.zipcode.atm.bank.AccountData;
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

        MenuItem basic =new MenuItem("Basic");
        MenuItem premium = new MenuItem("Premium");
        MenuItem ksa = new MenuItem("KSA");
        MenuButton menuButton =new MenuButton("Account Type",null,basic,premium,ksa);
        Label accountSelected = new Label("No Account selected");
        basic.setOnAction(e ->{
            accountSelected.setText("Basic Account");
            menuButton.setText("Basic");
            String emailAddress = email.getText();
            String name = cashMachine.getAccountData(emailAddress).getName();
            cashMachine.getAccountType(name, "Basic");
            areaInfo.setText(cashMachine.toString());

       });
        premium.setOnAction(e ->{
            accountSelected.setText("Premium Account");
            menuButton.setText("Premium");
            String emailAddress = email.getText();
            String name = cashMachine.getAccountData(emailAddress).getName();
            cashMachine.getAccountType(name, "Premium");
            areaInfo.setText(cashMachine.toString());

        });
        ksa.setOnAction(e ->{
            accountSelected.setText("Kids Savings Account");
            menuButton.setText("KSA");
            String emailAddress = email.getText();
            String name = cashMachine.getAccountData(emailAddress).getName();
            cashMachine.getAccountType(name, "Kids Savings");
            areaInfo.setText(cashMachine.toString());

        });

        Button emailSubmit = new Button("Email Login");
        Button btnSubmit = new Button("Set Account ID");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnExit = new Button("Exit");

        emailSubmit.setOnAction(e -> {
            String emailAddress = email.getText();
            cashMachine.login(emailAddress);
            areaInfo.setText(cashMachine.toString());

            if(!cashMachine.isAccount()) {
                return;
            }

            flowpane.getChildren().add(btnDeposit);
            flowpane.getChildren().add(btnWithdraw);
            flowpane.getChildren().remove(btnSubmit);
            flowpane.getChildren().remove(emailSubmit);
            flowpane.getChildren().add(menuButton);


            deposit.setText("Deposit Amount");
            vbox.getChildren().add(deposit);

            withdraw.setText("Withdraw Amount");
            vbox.getChildren().add(withdraw);

            vbox.getChildren().remove(field);
            vbox.getChildren().remove(email);

        });


        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
            areaInfo.setText(cashMachine.toString());

            if(!cashMachine.isAccount()) {
                return;
            }

            flowpane.getChildren().add(btnDeposit);
            flowpane.getChildren().add(btnWithdraw);
            flowpane.getChildren().remove(btnSubmit);
            flowpane.getChildren().remove(emailSubmit);
            flowpane.getChildren().add(menuButton);

            deposit.setText("Deposit Amount");
            vbox.getChildren().add(deposit);

            withdraw.setText("Withdraw Amount");
            vbox.getChildren().add(withdraw);

            vbox.getChildren().remove(field);
            vbox.getChildren().remove(email);
        });

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
            cashMachine.setAccountFlag(false);
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());

            flowpane.getChildren().add(btnSubmit);
            flowpane.getChildren().add(emailSubmit);
            flowpane.getChildren().remove(btnWithdraw);
            flowpane.getChildren().remove(btnDeposit);
            flowpane.getChildren().remove(menuButton);

            vbox.getChildren().remove(deposit);
            vbox.getChildren().remove(withdraw);
            field.setText("ID Login");
            vbox.getChildren().add(field);
            email.setText("Email");
            vbox.getChildren().add(email);


        });

        email.setText("Email Address");
        flowpane.getChildren().add(emailSubmit);

        field.setText("ID Login");
        flowpane.getChildren().add(btnSubmit);

        flowpane.getChildren().add(btnExit);

        vbox.getChildren().addAll(field, email,flowpane, areaInfo);

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
