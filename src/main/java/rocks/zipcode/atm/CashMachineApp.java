package rocks.zipcode.atm;

import javafx.scene.control.*;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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

import static javax.print.attribute.standard.Chromaticity.COLOR;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();

    private TextField email = new TextField();

    private TextField deposit = new TextField();

    private TextField withdraw = new TextField();

    private CashMachine cashMachine = new CashMachine(new Bank());

    private TextField name = new TextField();

    private TextField newId = new TextField();

    private TextField newEmail = new TextField();

    private Parent createContent() {
        VBox vbox = new VBox(20);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        FlowPane flowpane = new FlowPane();
        TextArea welcome = new TextArea();
        welcome.setText("\t\t\t\t\t\t\t\t\tWelcome to ZipCloud Bank!!\n \t\t\t\t\t\t\t\t\tEnjoy your Banking with us!!");
        welcome.setPrefHeight(40);  //sets height of the TextArea to 400 pixels
        welcome.setPrefWidth(40);
        welcome.setEditable(false);
        areaInfo.setEditable(false);


        MenuItem basic =new MenuItem("Basic");
        MenuItem premium = new MenuItem("Premium");
        MenuItem ksa = new MenuItem("KSA");
        MenuButton menuButton =new MenuButton("Account Type",null,basic,premium,ksa);
        Label accountSelected = new Label("No Account selected");
        basic.setOnAction(e ->{
            accountSelected.setText("Basic Account");
            menuButton.setText("Basic");
            String emailAddress = email.getText();
            String name = cashMachine.getAccountData().getName();
            cashMachine.getAccountType(name, "Basic");
            areaInfo.setText(cashMachine.toString());

       });
        premium.setOnAction(e ->{
            accountSelected.setText("Premium Account");
            menuButton.setText("Premium");
            String emailAddress = email.getText();
            String name = cashMachine.getAccountData().getName();
            cashMachine.getAccountType(name, "Premium");
            areaInfo.setText(cashMachine.toString());

        });
        ksa.setOnAction(e ->{
            accountSelected.setText("Kids Savings Account");
            menuButton.setText("KSA");
            String emailAddress = email.getText();
            String name = cashMachine.getAccountData().getName();
            cashMachine.getAccountType(name, "Kids Savings");
            areaInfo.setText(cashMachine.toString());

        });

        Button emailSubmit = new Button("Email Login");
        Button btnSubmit = new Button("Set Account ID");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnExit = new Button("Log Out");
        Button btnNewAccount = new Button("New Account");
        Button btnSubmitNewAccount = new Button("Submit");

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
            int id;
            try {
                id = Integer.parseInt(field.getText());
            } catch (NumberFormatException ex){
                Alert noId = new Alert(Alert.AlertType.WARNING);
                noId.setContentText("Please enter a number");
                noId.showAndWait();
                field.setText("ID Login");
                return;
            }

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

            Float amount = Float.parseFloat(deposit.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        btnWithdraw.setOnAction(e -> {

            Float amount = Float.parseFloat(withdraw.getText());

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

        btnNewAccount.setOnAction(e -> {
            String emailAddress = email.getText();
            FlowPane root2 = new FlowPane();
            Label label = new Label("Please create a new account.");
            root2.getChildren().add(label);
            Scene secondScene = new Scene(root2, 500,500);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.setTitle("Open new Account");
            secondStage.show();

            root2.setHgap(50);
            root2.setVgap(50);

            name.setText("Name");
            root2.getChildren().add(name);
            newId.setText("New ID");
            root2.getChildren().add(newId);
            newEmail.setText("New Email");
            root2.getChildren().add(newEmail);

            root2.getChildren().add(btnSubmitNewAccount);

        });

        btnSubmitNewAccount.setOnAction(e ->{
            String userName = name.getText();
            String userEmailId = newEmail.getText();
            Integer userId = Integer.parseInt(newId.getText());
            cashMachine.addAccount(userName, userId, userEmailId);
        });



        email.setText("Email Address");
        flowpane.getChildren().add(emailSubmit);

        field.setText("ID Login");
        flowpane.getChildren().add(btnSubmit);

        flowpane.getChildren().add(btnExit);

        flowpane.getChildren().add(btnNewAccount);

        vbox.getChildren().addAll(welcome,field, email,flowpane, areaInfo);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, null, null)));
        flowpane.setHgap(20);

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
