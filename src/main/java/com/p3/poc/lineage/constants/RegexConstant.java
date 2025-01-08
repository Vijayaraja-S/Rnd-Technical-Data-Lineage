package com.p3.poc.lineage.constants;

public class RegexConstant {
  public static final String VALIDATE_NAME_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})";
  public static final String EMAIL_PATTERN_REGEX =
      "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([A-Za-z]{2,6}(?:\\.[A-Za-z]{2,6})?)$";
  public static final String NAME_PATTERN_REGEX = "^[a-zA-Z0-9_]{5,30}$";
  public static final String PATTERN_NAME_REGEX = "^[a-zA-Z_]{1,30}$";
  public static final String FIELD_NAME_REGEX = "^[a-zA-Z]\\w*$";
  public static final String VALID_NAME_REGEX =
      "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,15}$";
  public static final String HOLD_NAME_REGEX = "\\/\\w+(\\/)?$";
  public static final String RETEN_NAME_REGEX = "\\/\\w+(\\/)?$";
  public static final String VALIDATION_REGEX =
      "/^[^* | \\ \" : < > [ ] { } ` \\ ( ) '' ; @ . _ - & $]+$/";
  public static final String LAST_NAME_REGEX =
      "/^[^* | \\ \" : < > [ ] { } ` \\ ( ) '' ; @ . _ - & $]+$/";
  public static final String UPDATE_SPARK_REGEX = "(?<=message\":\").*(?=\",\"error)";
  public static final String INPUT_REPLACER_REGEX = "(?<=@@)([\\S\\s]*?)(?=@@)";
  public static final String INPUT_FIELD_REGEX = "\\$\\{(.*?)}";
  public static final String ERROR_REGEX = "(?s).*(?=line\\s*\\d+:\\d+:\\s*)";
  public static final String REGEX = "line\\s*\\d+:\\d+:\\s*";








 
}
