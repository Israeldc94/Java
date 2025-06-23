package org.example;

import java.util.ArrayList;

public class StringCalculator {
    ArrayList<String> negativeNumbers = new ArrayList<>();
    char delimiter;
    String[] nums;

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (numbers.length() == 1) {
            return Integer.parseInt(numbers);
        } else if (numbers.startsWith("//")) {
            String temp = numbers.substring(3);
            delimiter = numbers.charAt(2);
            String sDelimiter = String.valueOf(delimiter);
            String finalDelimiter = String.format(",|\n|" + sDelimiter);
            nums = temp.split(finalDelimiter);
            if (numbers.contains("[")) {
            int firstIndex = numbers.indexOf("[");
            int lastIndex = numbers.indexOf("]");
            String delimiter2 =  numbers.substring(firstIndex, lastIndex);
            String finalDelimiter2 = String.format(",|\n|" + delimiter2);
            nums = temp.split(finalDelimiter2);
                return getSum(nums);
            }

        } else {
            nums = numbers.split(",|\n");
            return getSum(nums);
        }
        return getSum(nums);
    }

    private int getSum(String[] nums) {
        boolean hasNegative = false;
        int sum = 0;

        for (String num : nums) {
            int number = Integer.parseInt(num);
            if (number > 1000) {
                number = 0;
            }
            sum += number;
            if (number < 0) {
                negativeNumbers.add(num);
                hasNegative = true;
            }
        }
        if (hasNegative) {
            throw new IllegalArgumentException("Negative numbers aren't allowed negative numbers: " + negativeNumbers);
        }
        return sum;
    }

}
