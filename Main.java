package com.company;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static BigInteger[] bellNumbersTriangle(int n)
    {
        BigInteger[][] bellNumbers = new BigInteger[n][n];
        bellNumbers[0] = new BigInteger[1];
        bellNumbers[0][0] = BigInteger.valueOf(1);
        for(int i = 1;i<n;i++)
        {
            bellNumbers[i][0] = bellNumbers[i-1][i-1];
            for(int j = 1; j < i + 1; j++)
            {
                bellNumbers[i][j] = bellNumbers[i][j-1].add(bellNumbers[i-1][j-1]);
            }
        }
        BigInteger [] result = new BigInteger[n];

        for(int i = 0; i<n;i++)
        {
            result[i] = bellNumbers[i][i];
        }

        return result;
    }

    public static ArrayList<Integer> currentPrimes;
    public static BigInteger[] bellNumbersRecursiveFormula(int n)
    {
        BigInteger [] bellNumbersArray = new BigInteger[n];
        bellNumbersArray[0] = bellNumbersArray[1] = BigInteger.valueOf(1);
        currentPrimes = generatePrimes(n);

        for(int i = 2; i < n;i++)
        {
            bellNumbersArray[i] = BigInteger.valueOf(0);
            for(int j = 0; j<i; j++)
            {
                bellNumbersArray[i] = bellNumbersArray[i].add(bellNumbersArray[j].multiply(combinations(i-1,j)));
            }
         }

        return bellNumbersArray;
    }

    public static BigInteger combinations(int n, int k)
    {
        return factorial(n).divide(factorial(k).multiply(factorial(n-k)));
    }


    public static BigInteger factorial(int n)
    {
        if(n<2)
        {
            return BigInteger.valueOf(1);
        }
        else
        {
            BigInteger f = factorial(n/2);
            BigInteger ps = primeSwing(n);

            return f.multiply(f).multiply(ps);
        }
    }


    public static BigInteger primeSwing(int n)
    {
        BigInteger result = BigInteger.valueOf(1);
        for (Integer currentPrime : currentPrimes) {
            if (currentPrime <= n) {
                int lP = 0;
                for (int j = 1; j <= Math.log(n) / Math.log(currentPrime); j++) {
                    lP += (n / Math.pow(currentPrime, j)) % 2;
                }
                result = result.multiply(BigInteger.valueOf(currentPrime).pow(lP));
            }
        }

        return result;
    }

    public static ArrayList<Integer> generatePrimes(int n)
    {
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i = 2;i<=n;i++)
        {
            if(isPrime(i))
            {
                primes.add(i);
            }
        }

        return primes;

    }

    public static boolean isPrime(int n)
    {
        if(n==2)
        {
            return true;
        }

        for(int i = 2;i<Math.sqrt(n)+1;i++)
        {
            if(n%i==0)
            {
                return false;
            }
        }

        return true;
    }




    public static void main(String[] args)
    {
	    int n = 400;


	    long start = System.nanoTime();

	    BigInteger[] bellNumbers = bellNumbersRecursiveFormula(n);

	    long end = System.nanoTime();

        System.out.format("Computed %d first Bell numbers in %f seconds using Recursive formula\n",n,(end-start)/1e9);

        start = System.nanoTime();

        bellNumbers = bellNumbersTriangle(n);

        end = System.nanoTime();

        System.out.format("Computed %d first Bell numbers in %f seconds using Bell Triangle\n",n,(end-start)/1e9);


    }
}
