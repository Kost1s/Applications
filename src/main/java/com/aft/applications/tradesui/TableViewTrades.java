package com.aft.applications.tradesui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kostis on 3/5/2017.
 */
public class TableViewTrades extends Application {

    @Override
    public void start(Stage stage) {

        TableView<Trade> table = new TableView<>();

        TableColumn<Trade, Integer> tradeNoCol = new TableColumn<>("Trade No");
        TableColumn<Trade, Timestamp> tradeTimeCol = new TableColumn<>("Trade Time");
        TableColumn<Trade, BigDecimal> tradePriceCol = new TableColumn<>("Trade Price");
        TableColumn<Trade, BigDecimal> tradeQtyCol = new TableColumn<>("Trade Volume");

        tradeNoCol.setCellValueFactory(new PropertyValueFactory<>("tradeNo"));
        tradeTimeCol.setCellValueFactory(new PropertyValueFactory<>("tradeTime"));
        tradePriceCol.setCellValueFactory(new PropertyValueFactory<>("tradePrice"));
        tradeQtyCol.setCellValueFactory(new PropertyValueFactory<>("tradeQty"));

        // Set Sort type for userName column
        tradeNoCol.setSortType(TableColumn.SortType.DESCENDING);
        tradeTimeCol.setSortable(false);

        // Display row data
        ObservableList<Trade> tradesData = getTradesData();
        table.setItems(tradesData);

        table.getColumns().add(tradeNoCol);
        table.getColumns().add(tradeTimeCol);
        table.getColumns().add(tradePriceCol);
        table.getColumns().add(tradeQtyCol);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        stage.setTitle("Trades Data");

        Scene scene = new Scene(root, 450, 300);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Trade> getTradesData() {

        String csvFile = "/6CH6.txt";
        List<Trade> tradesList = new TradesList().getTrades(csvFile);

        ObservableList<Trade> tradesData = FXCollections.observableArrayList(tradesList);
        return tradesData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
