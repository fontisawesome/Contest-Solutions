/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HECAI
 */
import java.util.Scanner;
import java.text.DecimalFormat;
public class Supermoon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DecimalFormat df2 = new DecimalFormat("0.0000");
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int r1 = input.nextInt();
        int x2 = input.nextInt();
        int y2 = input.nextInt();
        int r2 = input.nextInt();
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if (d == 0) {
            if (r2 >= r1){
                double area1 = Math.PI * r1 * r1;
                System.out.println(df2.format(area1));
            }
            else{
                double area2 = Math.PI *r2 * r2;
                System.out.println(df2.format(area2));
            }
        }
        else if (d >= r1+r2){
            System.out.println(0);
        }
        else if (d <= (r1-r2) && r1 > r2){
             double area2 = Math.PI *r2 * r2;
                System.out.println(df2.format(area2));
        }
        else if (d <= r2-r1 && r2 > r1){
            double area1 = Math.PI * r1 * r1;
                System.out.println(df2.format(area1));
        }
        else {
            if (r2 > r1){
                int tempr1 = r1;
                r1 = r2;
                r2 = tempr1;
            }
            double c1 = (d * d - r2 * r2 + r1 * r1) / (2 * d);
            double c2 = (d * d + r2 * r2 - r1 * r1) / (2 * d);
            double angle1Radians = Math.acos(c1 / r1) * 2;
            double angle1 = Math.toDegrees(angle1Radians);
            double ratio1 = angle1 / 360;
            double area1 = Math.PI * r1 * r1 * ratio1;
            double chordLength1 = 2*r1*Math.sqrt(1-((c1/r1)*(c1/r1)));
            double triangleArea1 = c1 * chordLength1 / 2;
            double smallArea1 = area1 - triangleArea1;
            double angle2Radians = Math.acos(c2 / r2) * 2;
            double angle2 = Math.toDegrees(angle2Radians);
            double ratio2 = angle2 / 360;
            double area2 = Math.PI * r2 * r2 * ratio2;
            double triangleArea2 = c2 * chordLength1 / 2;
            double smallArea2 = area2 - triangleArea2;
            double finalArea = smallArea1 + smallArea2;
            System.out.println(df2.format(finalArea));
        }
    }

}