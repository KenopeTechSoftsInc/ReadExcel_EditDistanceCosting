/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab8;

/**
 *
 * @author opeol
 * @author Opeyemi
 */
/*MAIN CLASS
 *Program Title: A program that implement Dynamic Programming
 *AUTHOR: OLATUNJI, OPEYEMI MSc IT
 *INSTRUCTOR: Prof Olac Fuentes
 *TA: Zakia Kadri, Adul Kader
 *
 *LAST DATE OF MODIFICATION: April 28 2017, 11.15pm
 */
/**
 *
 * Opeyemi Olatunji, CS 2302 Spring 2017
 */
public
        class EditDistanceWithCost {

    // method to solve COIN PROBLEM
    public static
            int[] change(int[] v, int C) {
        //dynamic programming implementation of an algorithm to give change for amount C
        // using the minimum number of coins, which have denominations v[0]...v[i],...
        // One of v[0], v[1]....must be equal to zero
        //M[i] contains the minimum number of coins necessary to give change for i cents
        int[] M = new int[C + 1];
        int[] lastCoin = new int[C + 1];
        int[] coins = new int[v.length];

        M[0] = 0;
        for (int i = 1; i < M.length; i++) {
            M[i] = C;
            for (int j = 0; j < v.length; j++) {
                if (i - v[j] >= 0) {
                    // Coin's value is not larger than remaining change to give
                    if (1 + M[i - v[j]] <= M[i]) {
                        M[i] = 1 + M[i - v[j]];
                        lastCoin[i] = j;
                        System.out.println("last coin is: " + lastCoin[i] + " "
                                           + "M is :" + M[i] + " array number: " + i);
                    }
                }
            }
        }
        int D = C;
        while (D > 0) {
            coins[lastCoin[D]]++;
            System.out.println("coin is: " + lastCoin[D]);

            D = D - v[lastCoin[D]];
            System.out.println("D is: " + D);
        }

        return coins;
    }

    // dynamic programming edit Distance function
    public static int editDistance(String s1, String s2) {
        int[][] d = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            d[i][0] = i; // deletion

            // System.out.print(d[i][0] + " ");
            // System.out.println();
        }

        for (int j = 0; j <= s1.length(); j++) {
            d[0][j] = j; // insertion

            // System.out.print(d[j][0] + " ");
        }

        for (int j = 0; j < s2.length(); j++) {
            for (int i = 0; i < s1.length(); i++) {

                if (s1.charAt(i) == s2.charAt(j)) // match
                {
                    System.out.println();
                    d[i + 1][j + 1] = d[i][j];
                    //System.out.print(" ");
                    System.out.print(d[i + 1][j + 1] + " ");
                    //System.out.println();
                }
                else {

                    d[i + 1][j + 1] = 1 + Math.min(Math.min(d[i][j + 1], d[i + 1][j]),
                                                   d[i][j]);
                    //System.out.print(" ");
                    //System.out.print(d[i + 1][j + 1] + " ");
                    //System.out.println();
                }
            }
        }
        return d[s1.length()][s2.length()];

    }

    // dynamic programming edit Distance function with costs
    public static int editDistanceCost(String s1, String s2, int delCost,
                                 int insCost, int repCost) {
        int[][] d = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            d[i][0] = i; // deletion
        }
        for (int j = 0; j <= s1.length(); j++) {
            d[0][j] = j; // insertion
        }
        for (int j = 0; j < s2.length(); j++) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(j)) // match
                {
                    d[i + 1][j + 1] = d[i][j];
                }
                else {
                    d[i + 1][j + 1] = 1 + Math.min(Math.min(insCost * d[i][j + 1],
                                                            delCost * d[i + 1][j]), repCost * d[i][j]);
                }
            }
        }
        return d[s1.length()][s2.length()];

    }

    // dynamic programming edit Distance function with array/matrix of cost
    public static double editDistanceCostArray(String s1, String s2, int delCost,
                                         int insCost, double[][] repCost, char[] charOrder) {
        double replacementCost = 0;

        for (int k = 0; k < repCost.length; k++) {
            for (int m = 0; m < repCost.length; m++) {
                replacementCost = repCost[k][m];
            }
        }

        double[][] d = new double[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            d[i][0] = i; // deletion
        }
        for (int j = 0; j <= s2.length(); j++) {
            System.out.println(" s1 is : loop " + j + " " + s1);
            System.out.println(" s2 is : loop " + j + " " + s2);
            d[0][j] = j; // insertion
        }
        for (int j = 0; j < s2.length(); j++) {
            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);
                int idx1 = indexOf(charOrder, c1);
                int idx2 = indexOf(charOrder, c2);
                if (idx1 == -1 || idx2 == -1) {
                    System.out.println("error");
                }
                double replCost = repCost[idx1][idx2];

                if (s1.charAt(i) == s2.charAt(j)) // match
                {
                    d[i + 1][j + 1] = d[i][j];
                }
                else {
                    d[i + 1][j + 1] = 1 + Math.min(Math.min(insCost + d[i][j + 1],
                                                            delCost+ d[i][j]), replCost+ d[i + 1][j]);
                }
            }
        }
        return d[s1.length()][s2.length()]; 

    }

    public static int indexOf(char[] arr, char searching) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searching) {
                return i;
            }
        }
        return -1;
    }

    // method subset sum in DP
    public static boolean subSetSum(int[] mySet, int n, int goal) {
        if (goal == 0) {
            return true;
        }
        if ((goal < 0) || (n < 0)) {
            return false;
        }
        if (subSetSum(mySet, n - 1, goal - mySet[n])) {
            System.out.println(mySet[n] + " ");
            return true;
        }

        return subSetSum(mySet, n - 1, goal);
    }

}
