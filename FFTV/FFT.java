/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author HECAI
 */
public class FFT {

    /**
     * @param args the command line arguments
     */
    public static double multiplicationr (double real, double imaginary, double real1, double imaginary1){
        real += 998244353;
        imaginary += 998244353;
        real1 += 998244353;
        imaginary1 += 998244353;
        return (((real%998244353)*(real1%998244353))%998244353 - ((imaginary%998244353)*(imaginary1%998244353))%998244353)%998244353 ;
    }
    public static double multiplicationi(double real, double imaginary, double real1, double imaginary1){
        real += 998244353;
        imaginary += 998244353;
        real1 += 998244353;
        imaginary1 += 998244353;
        
        return (((real%998244353)*(imaginary1%998244353))%998244353 + ((real1%998244353)*(imaginary%998244353))%998244353)%998244353;
    }
    public static double [] evalr (double [] polyr, double polyi[], double realw, double imaginaryw, int m){
    
        //System.out.println("test " + realw + " " + imaginaryw + " " + m);
        if (m == 1){
            double [] f = {polyr[0]};
            return f;
        }
         double [] evenr = new double[m/2];
        double [] oddr = new double[m/2];
        double [] eveni = new double[m/2];
         double [] oddi = new double[m/2];
        for (int i = 0; i < m; i++){
            if (i % 2 == 0){
                evenr[i/2] = polyr[i];
                eveni [i/2] = polyi[i];
            }
            else{
                oddr[i/2] = polyr[i];
                oddi[i/2] = polyi[i];
            }
        }
        double reale [] = evalr(evenr, eveni, Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double imaginarye [] = evali(evenr,eveni, Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double realo [] = evalr(oddr, oddi,  Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double imaginaryo [] = evali(oddr, oddi, Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double fr [] = new double [m];
        double fi [] = new double[m];
        
        for (int i = 0; i < m/2; i++){
            double xr = Math.cos(2*Math.PI*i/m);
            double xi = Math.sin(2*Math.PI*i/m);
            fr[i] = reale[i]%998244353 + (multiplicationr(xr, xi, realo[i], imaginaryo[i]))%998244353;
            //fi[i] = imaginarye[i] + multiplicationi(xr, xi, realo[i], imaginaryo[i]);
            fr[i+m/2] = ((reale[i]%998244353 - (multiplicationr(xr, xi, realo[i], imaginaryo[i]))%998244353) + 998244353)%998244353;
            //fi[i+m/2] = imaginarye[i] - multiplicationi(xr, xi, realo[i], imaginaryo[i]);
            
            //System.out.println(xr + " " + xi);
        }
        return fr;
    }
     public static double [] evali (double [] polyr, double polyi[], double realw, double imaginaryw, int m){
         
        //System.out.println("test " + realw + " " + imaginaryw + " " + m);
        if (m == 1){
            double [] f = {polyi[0]%998244353};
            return f;
        }
        double [] evenr = new double[m/2];
        double [] oddr = new double[m/2];
        double [] eveni = new double[m/2];
         double [] oddi = new double[m/2];
        for (int i = 0; i < m; i++){
            if (i % 2 == 0){
                evenr[i/2] = polyr[i];
                eveni [i/2] = polyi[i];
            }
            else{
                oddr[i/2] = polyr[i];
                oddi[i/2] = polyi[i];
            }
        }
        double reale [] = evalr(evenr, eveni, Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double imaginarye [] = evali(evenr,eveni, Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double realo [] = evalr(oddr, oddi,  Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double imaginaryo [] = evali(oddr, oddi, Math.pow(realw,2)-Math.pow(imaginaryw,2), 2*realw*imaginaryw, m/2);
        double fr [] = new double [m];
        double fi [] = new double[m];
        
        for (int i = 0; i < m/2; i++){
            double xr = Math.cos(2*Math.PI*i/m);
            double xi = Math.sin(2*Math.PI*i/m);
//            if (i == 0){
//                System.out.println(imaginarye[i] + " " + xr + " " + xi + " " + imaginaryo[i]);
//            }
            //fr[i] = reale[i] + (multiplicationr(xr, xi, realo[i], imaginaryo[i]));
            fi[i] = imaginarye[i]%998244353 + multiplicationi(xr, xi, realo[i], imaginaryo[i])%998244353;
            //fr[i+m/2] = reale[i] - (multiplicationr(xr, xi, realo[i], imaginaryo[i]));
            fi[i+m/2] = ((imaginarye[i]%998244353 - multiplicationi(xr, xi, realo[i], imaginaryo[i])%998244353) + 998244353)%998244353;
            
        }
        return fi;
    }
    public static double[] pad (double arr[],int n){
        double arr2 [] = new double [(int)Math.pow(2, Math.ceil((Math.log(n)/Math.log(2))))];
        for (int i = 0; i < arr.length; i++){
            arr2[i] = arr[i];
        }
        
       
        return arr2;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = input.nextInt();
        }
        double ans [] = {arr[0],1};
        for (int i = 1; i < arr.length; i++){
            double next [] = {arr[i],1};
            double padans [] = pad(ans, i+2);
            double padnext [] = pad(next, i+2);
            //System.out.println(Arrays.toString(padans));
            double zeros [] = new double[padans.length];
            double fr [] = evalr(padans, zeros, Math.cos(2*Math.PI/padans.length), Math.sin(2*Math.PI/padans.length), padans.length);
            double fi [] = evali(padans, zeros, Math.cos(2*Math.PI/padans.length), Math.sin(2*Math.PI/padans.length), padans.length);
            double fr1 [] = evalr(padnext, zeros, Math.cos(2*Math.PI/padans.length), Math.sin(2*Math.PI/padans.length), padans.length);
            double fi1 [] = evali(padnext, zeros, Math.cos(2*Math.PI/padans.length), Math.sin(2*Math.PI/padans.length), padans.length);
            
            double cr [] = new double [fr.length];
            double ci [] = new double [fr.length];
            for (int j = 0; j < fr.length; j++){
                cr [j] = multiplicationr(fr[j], fi[j], fr1[j], fi1[j]);
                ci [j] = multiplicationi(fr[j], fi[j], fr1[j], fi1[j]);
            }
//            System.out.println(Arrays.toString(cr));
//            System.out.println(Arrays.toString(ci));
            double tempans [] = evalr(cr, ci, Math.cos(-2*Math.PI/padans.length), Math.sin(-2*Math.PI/padans.length), padans.length);
            ans = new double [tempans.length];
            for (int j = 0; j < tempans.length; j++){
                ans[j] = (int)Math.round((tempans[j]/padans.length));
            }
//            System.out.println(i + " ------");
//            for (int j = 0; j < ans.length; j++){
//                System.out.println(ans[j]);
//            }
        }
        for (int i = n-1; i >= 0; i--){
            System.out.println((int)(ans[i]+998244353)%998244353);
        }
        
    }
    
}