package com.p3.poc.test.matcher;

import org.jvnet.hk2.annotations.Service;

import java.util.Objects;

@Service
public class Matcher {
  public double jaroDistance(String s1, String s2) {
    if (Objects.equals(s1, s2)) return 1.0;

    int len1 = s1.length();
    int len2 = s2.length();

    if (len1 == 0 || len2 == 0) return 0.0;

    int maxDist = (int) Math.floor((double) Math.max(len1, len2) / 2) - 1;

    int match = 0;

    int[] hashS1 = new int[s1.length()];
    int[] hashS2 = new int[s2.length()];

    for (int i = 0; i < len1; i++) {

      for (int j = Math.max(0, i - maxDist); j < Math.min(len2, i + maxDist + 1); j++)
        if (s1.charAt(i) == s2.charAt(j) && hashS2[j] == 0) {
          hashS1[i] = 1;
          hashS2[j] = 1;
          match++;
          break;
        }
    }
    if (match == 0) return 0.0;

    double t = 0;
    int point = 0;

    for (int i = 0; i < len1; i++)
      if (hashS1[i] == 1) {
        while (hashS2[point] == 0) point++;

        if (s1.charAt(i) != s2.charAt(point++)) t++;
      }
    t /= 2;
    return (((double) match) / ((double) len1)
            + ((double) match) / ((double) len2)
            + (match - t) / (match))
        / 3.0;
  }

  public double jaroWinkler(String s1, String s2) {
    double jaroDistance = jaroDistance(s1, s2);
    if (jaroDistance > 0.7) {
      int prefix = 0;
      for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
        if (s1.charAt(i) == s2.charAt(i)) prefix++;
        else break;
      }
      prefix = Math.min(4, prefix);
      jaroDistance += 0.1 * prefix * (1 - jaroDistance);
    }
    return jaroDistance;
  }
}
