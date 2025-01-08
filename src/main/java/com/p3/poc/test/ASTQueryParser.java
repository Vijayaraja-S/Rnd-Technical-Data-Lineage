package com.p3.poc.test;

import com.p3.poc.test.bean_new.ApplicationResponse;
import com.p3.poc.test.matcher.Matcher;
import com.p3.poc.test.testing_bean.ColumnInformation;
import com.p3.poc.test.testing_bean.ResultBean;
import com.p3.poc.test.testing_bean.TableInformation;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ASTQueryParser extends DefaultTraversalVisitor<ResultBean> {

  public static void main(String[] args) {
    String sql =
        "WITH regional_order_totals AS (\n"
            + "    SELECT \n"
            + "        region,\n"
            + "        customer_id,\n"
            + "        SUM(order_amount) AS total_order_amount,\n"
            + "        RANK() OVER (PARTITION BY region ORDER BY SUM(order_amount) DESC) AS region_rank\n"
            + "    FROM orders\n"
            + "    WHERE order_date BETWEEN DATE('2023-01-01') AND DATE('2023-12-31')  -- Filter by date range\n"
            + "    GROUP BY region, customer_id\n"
            + "),\n"
            + "customer_details AS (\n"
            + "    SELECT \n"
            + "        c.customer_id,\n"
            + "        c.customer_name,\n"
            + "        c.signup_date,\n"
            + "        c.loyalty_points,\n"
            + "        r.region,\n"
            + "        r.total_order_amount,\n"
            + "        r.region_rank\n"
            + "    FROM regional_order_totals r\n"
            + "    JOIN customers c\n"
            + "    ON r.customer_id = c.customer_id\n"
            + "    WHERE r.region_rank <= 10  -- Only top 10 customers per region\n"
            + ")\n"
            + "SELECT \n"
            + "    region,\n"
            + "    customer_name,\n"
            + "    signup_date,\n"
            + "    loyalty_points,\n"
            + "    total_order_amount,\n"
            + "    SUM(total_order_amount) OVER (PARTITION BY region ORDER BY total_order_amount DESC) AS running_total\n"
            + "FROM customer_details\n"
            + "ORDER BY region, running_total DESC";
    ASTQueryParser extractor = new ASTQueryParser();
    SqlParser parser = new SqlParser();
    Statement statement = parser.createStatement(sql, new ParsingOptions());

    ResultBean resultBean = ResultBean.builder().isBaseBean(true).build();
    statement.accept(extractor, resultBean);
    List<ResultBean> resultBeanList = flattenResultBeans(resultBean);
    checkAndSetAllColumns(resultBeanList);
    ResultBean collectedSingleResultBean = processResultBean(resultBeanList);
    ApplicationResponse application =
        prepareFinalResponseBean(collectedSingleResultBean, List.of("APPLICATION"));
    System.out.println(application);
  }

  private static void checkAndSetAllColumns(List<ResultBean> resultBeanList) {
    resultBeanList.forEach(
        resultBean -> {
          if (Boolean.TRUE.equals(resultBean.getHasAllColumnsSelected())
              && Boolean.TRUE.equals(!resultBean.getIsBaseBean())) {
            TableInformation tableInformation = resultBean.getTInfos().get(0);
            tableInformation.setHasAllColumnsSelected(true);
          }
        });
  }

  private static ApplicationResponse prepareFinalResponseBean(
      ResultBean collectedSingleResultBean, List<String> application) {

    List<TableInformation> tInfos = collectedSingleResultBean.getTInfos();
    Map<String, List<TableInformation>> groupedByTableFullName = tInfos.stream()
            .collect(Collectors.groupingBy(TableInformation::getTableFullName));

    return new ApplicationResponse();
  }

  private static ResultBean processResultBean(List<ResultBean> resultBeanList) {
    List<TableInformation> tInfos = new ArrayList<>();
    List<ColumnInformation> cInfos = new ArrayList<>();

    resultBeanList.forEach(
        resultBean -> {
          resultBean
              .getTInfos()
              .forEach(
                  beanTInfo -> {
                    if (hasAliasConflict(tInfos, beanTInfo)) {
                      String uniqueId = UUID.randomUUID().toString();
                      beanTInfo.setAliasNameOrUniqueId(uniqueId);
                      resultBean.getCInfos().forEach(cInfo -> cInfo.setAliasOrUniqueId(uniqueId));
                      tInfos.add(beanTInfo);
                    } else {
                      tInfos.add(beanTInfo);
                    }
                  });
          cInfos.addAll(resultBean.getCInfos());
        });

    return ResultBean.builder().tInfos(tInfos).cInfos(cInfos).build();
  }

  private static boolean hasAliasConflict(
      List<TableInformation> tInfos, TableInformation beanTInfo) {
    return tInfos.stream()
        .anyMatch(
            t ->
                !t.getTableFullName().equals(beanTInfo.getTableFullName())
                    && t.getAliasNameOrUniqueId().equals(beanTInfo.getAliasNameOrUniqueId()));
  }

  public static List<ResultBean> flattenResultBeans(ResultBean resultBean) {
      return Stream.concat(
              Stream.of(resultBean),
              resultBean.getResultBeanList().stream()
                  .flatMap(childBean -> flattenResultBeans(childBean).stream()))
          .filter(bean -> !bean.getIsBaseBean())
          .toList();
  }

  @Override
  protected Void visitTable(Table node, ResultBean context) {
    TableInformation tableInformation =
        TableInformation.builder()
            .tableFullName(node.getName().toString())
            .aliasNameOrUniqueId(context.getUniqueId())
            .build();
    if (isTableBeanPresent(tableInformation, context)) {
      context.getTInfos().add(tableInformation);
    }
    return null;
  }

  private boolean isTableBeanPresent(TableInformation tableInformation, ResultBean context) {
    return context.getTInfos().stream()
        .noneMatch(
            t ->
                t.getAliasNameOrUniqueId()
                        .equalsIgnoreCase(tableInformation.getAliasNameOrUniqueId())
                    && t.getTableFullName().equalsIgnoreCase(tableInformation.getTableFullName()));
  }

  @Override
  protected Void visitIdentifier(Identifier node, ResultBean context) {
    String columnName = node.getValue();
    columnName = columnName.replace("\"", "");
    String tableAlias = context.getUniqueId();
    ColumnInformation cInfo =
        ColumnInformation.builder().aliasOrUniqueId(tableAlias).columnName(columnName).build();

    if (isColumnBeanPresent(context.getCInfos(), cInfo)) {
      context.getCInfos().add(cInfo);
    }
    return null;
  }

  private boolean isColumnBeanPresent(
      List<ColumnInformation> columnInformationList, ColumnInformation cInfo) {
    return columnInformationList.stream()
        .noneMatch(
            c ->
                c.getColumnName().equalsIgnoreCase(cInfo.getColumnName())
                    && c.getAliasOrUniqueId().equalsIgnoreCase(cInfo.getAliasOrUniqueId()));
  }

  @Override
  protected Void visitDereferenceExpression(DereferenceExpression expression, ResultBean context) {
    final Optional<Identifier> field = expression.getField();
    final Expression base = expression.getBase();
    if (base instanceof Identifier identifier) {
      String tableAlias = identifier.getValue();
      ColumnInformation cInfo =
          ColumnInformation.builder()
              .aliasOrUniqueId(tableAlias)
              .columnName(field.map(value -> value.toString().replace("\"", "")).orElse(""))
              .build();
      if (isColumnBeanPresent(context.getCInfos(), cInfo)) {
        context.getCInfos().add(cInfo);
      }
    }
    return null;
  }

  @Override
  protected Void visitAliasedRelation(AliasedRelation node, ResultBean context) {
    TableInformation tableInformation = TableInformation.builder().build();
    if (node.getRelation() instanceof Table table) {
      tableInformation.setAliasNameOrUniqueId(node.getAlias().toString());
      tableInformation.setTableFullName(table.getName().toString());
    }
    if (isTableBeanPresent(tableInformation, context)) {
      context.getTInfos().add(tableInformation);
    }
    return null;
  }

  @Override
  protected Void visitSelect(Select node, ResultBean context) {
    context.setUniqueId(UUID.randomUUID().toString());
    return super.visitSelect(node, context);
  }

  @Override
  protected Void visitAllColumns(AllColumns node, ResultBean context) {
    context.setHasAllColumnsSelected(true);
    return super.visitAllColumns(node, context);
  }

  @Override
  protected Void visitQuerySpecification(QuerySpecification node, ResultBean context) {
    ResultBean resultBean = ResultBean.builder().build();
    context.getResultBeanList().add(resultBean);
    return super.visitQuerySpecification(node, resultBean);
  }

  private String[] extractApplicationAndSchema(
      String tableFullName, List<String> involvedApplications) {
    List<String> originalParts = Arrays.stream(tableFullName.split("\\.")).toList();

    String applicationSchemaName = getApplicationSchemaName(originalParts);
    String tableName = getTableName(originalParts);
    tableName = tableName.replace("\"", "");

    String[] appAndSchema =
        getApplicationAndSchemaNames(applicationSchemaName, involvedApplications);

    return new String[] {appAndSchema[0], appAndSchema[1], tableName};
  }

  private String getApplicationSchemaName(List<String> originalParts) {
    return originalParts.size() == 2 ? originalParts.get(0) : "";
  }

  /**
   * Retrieves the table name from the qualified name's parts.
   *
   * @param originalParts The list of identifiers from the qualified name.
   * @return The table name, or an empty string if not available.
   */
  private String getTableName(List<String> originalParts) {
    return !originalParts.isEmpty()
        ? String.valueOf(originalParts.get(originalParts.size() - 1))
        : "";
  }

  /**
   * Splits the application and schema name into separate components.
   *
   * @param applicationSchemaName The combined application and schema name.
   * @param involvedApplication Hold the parsed information details
   * @return A string array where [0] is the application name and [1] is the schema name.
   */
  private String[] getApplicationAndSchemaNames(
      String applicationSchemaName, List<String> involvedApplication) {
    applicationSchemaName = applicationSchemaName.replace("\"", "");
    final String upperCase = applicationSchemaName.toUpperCase(Locale.ROOT);
    String applicationName = getBestMatchingApplication(upperCase, involvedApplication);
    String schemaName = extractSchemaName(upperCase, applicationName + "_");
    return new String[] {applicationName, schemaName};
  }

  private String getBestMatchingApplication(
      String applicationSchemaName, List<String> involvedApplications) {
    String applicationName = "";
    double bestMatchValue = 0.0;
    Matcher matcher = new Matcher();

//    for (ApplicationEntity application : involvedApplications) {
//      double matchValue = matcher.jaroWinkler(application.getName(), applicationSchemaName);
//      if (matchValue > bestMatchValue) {
//        applicationName = application.getName();
//        bestMatchValue = matchValue;
//      }
//    }
    return applicationName;
  }

  private String extractSchemaName(String applicationAndSchemaName, String applicationName) {
    if (applicationAndSchemaName != null
        && applicationName != null
        && applicationAndSchemaName.startsWith(applicationName)) {
      return applicationAndSchemaName.substring(applicationName.length());
    }
    return "";
  }
}
