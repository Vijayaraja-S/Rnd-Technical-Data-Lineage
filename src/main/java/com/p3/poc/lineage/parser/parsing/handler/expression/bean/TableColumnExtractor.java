package com.p3.poc.lineage.parser.parsing.handler.expression.bean;

import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.*;

import java.util.HashSet;
import java.util.Set;

public class TableColumnExtractor extends DefaultTraversalVisitor<Void> {
    private final Set<String> tables = new HashSet<>();
    private final Set<String> columns = new HashSet<>();

    // Collect table names
    @Override
    protected Void visitTable(Table node, Void context) {
        tables.add(node.getName().toString());
        return null;
    }

    // Collect column names (including aliases)
    @Override
    protected Void visitIdentifier(Identifier node, Void context) {
        columns.add(node.getCanonicalValue());
        return null;
    }


    public Set<String> getTables() {
        return tables;
    }

    public Set<String> getColumns() {
        return columns;
    }

    public static void main(String[] args) {
        String sql = "SELECT \n" +
                "    customer_id,\n" +
                "    (SELECT orderid,order_date,order_status,COUNT(*) \n" +
                "     FROM orders \n" +
                "     WHERE customer_id = customers \n" +
                "       AND orders.order_date > CURRENT_DATE - INTERVAL '30 days') AS recent_orders_count,\n" +
                "    (SELECT MAX(order_total) ,order_total,max_order_total,order_date\n" +
                "     FROM orders \n" +
                "     WHERE customer_id = customer_id) AS max_order_total\n" +
                "FROM customers\n" +
                "JOIN orders ON customers.customer_id = orders.customer_id\n" +
                "WHERE customer_id IN (\n" +
                "    SELECT customer_id \n" +
                "    FROM orders \n" +
                "    WHERE order_total > 500\n" +
                "    UNION\n" +
                "    SELECT customer_id \n" +
                "    FROM orders \n" +
                "    WHERE order_date > CURRENT_DATE - INTERVAL '1 year'\n" +
                "    UNION ALL\n" +
                "    SELECT customer_id \n" +
                "    FROM orders \n" +
                "    WHERE order_status = 'Pending'\n" +
                ")\n" +
                "UNION\n" +
                "SELECT \n" +
                "    supplier_id AS customer_id,\n" +
                "    (SELECT COUNT(*) \n" +
                "     FROM supply_orders \n" +
                "     WHERE supplier_id = suppliers.supplier_id \n" +
                "       AND supply_orders.supply_order_date > CURRENT_DATE - INTERVAL '30 days') AS recent_orders_count,\n" +
                "    (SELECT MAX(supply_order_total) \n" +
                "     FROM supply_orders \n" +
                "     WHERE supply_orders.supplier_id = suppliers.supplier_id) AS max_order_total\n" +
                "FROM suppliers\n" +
                "JOIN supply_orders ON suppliers.supplier_id = supply_orders.supplier_id\n" +
                "WHERE suppliers.supplier_id IN (\n" +
                "    SELECT supplier_id \n" +
                "    FROM supply_orders \n" +
                "    WHERE supply_order_total > 1000\n" +
                "    UNION ALL\n" +
                "    SELECT supplier_id \n" +
                "    FROM supply_orders \n" +
                "    WHERE supply_order_status = 'Shipped'\n" +
                ")";

        SqlParser parser = new SqlParser();
    Statement statement = parser.createStatement(sql, new ParsingOptions());

        // Create an extractor and traverse the AST
        TableColumnExtractor extractor = new TableColumnExtractor();
        statement.accept(extractor, null);

        System.out.println("Tables: " + extractor.getTables());
        System.out.println("Columns: " + extractor.getColumns());
    }
}
