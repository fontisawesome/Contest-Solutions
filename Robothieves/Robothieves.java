/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HECAI
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Robothieves{

    /**
     * @param args the command line arguments
     */
    public static void isBFST(ArrayList<Integer>[] a, int distance[], int s, int x, boolean[] v1) {

        LinkedList<Integer> q = new LinkedList<Integer>();

        q.add(s);
        while (!q.isEmpty()) {
            int i = q.remove();

            for (int j : a[i]) {
                if (!v1[j]) {
                    q.add(j);
                    v1[j] = true;
                    distance[j] = distance[i] + 1;
                }
            }
        }

    }
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;

    static void exit() {
        pw.close();
        System.exit(0);
    }

    static long readLong() throws IOException {
        return Long.parseLong(next());
    }

    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }

    static char readChar() throws IOException {
        return next().charAt(0);
    }

    static String nextLine() throws IOException {
        String s = br.readLine();
        if (s == null) {
            exit();
        }
        st = null;
        return s;
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(nextLine().trim());
        }
        return st.nextToken();
    }

    public static int coordX(int l, int n) {
        return l % n;
    }

    public static int coordY(int l, int n) {
        return (l - l % n) / n;
    }

    public static int to1D(int x, int y, int n) {
        return y * n + x;
    }

    public static boolean withinBounds(int x, int y, int n, int m) {
        boolean xIn = x < n && x >= 0;
        boolean yIn = y < m && y >= 0;
        return xIn && yIn;
    }
    static String convey = "DLRU";
    static int conveyX[] = {0, -1, 1, 0};
    static int conveyY[] = {1, 0, 0, -1};

    public static int resolveConveyor(char board[], int L, int n, int m) {
        boolean visited[] = new boolean [n*m];
        visited[L] = true;
        char conveyChar = board[L];
        int currentX = coordX(L, n);
        int currentY = coordY(L, n);
        int index = convey.indexOf(conveyChar);
        if (withinBounds(currentX + conveyX[index], currentY + conveyY[index], n, m)){
            currentX = currentX + conveyX [index];
            currentY = currentY + conveyY [index];
            L = to1D(currentX, currentY, n);
            conveyChar = (board[L]);
        }
        
        while (convey.contains(conveyChar+"") && !visited[L]){
            visited[L] = true;
            index = convey.indexOf(conveyChar);
            if (withinBounds(currentX + conveyX[index], currentY + conveyY[index], n, m)){
                currentX = currentX + conveyX [index];
                currentY = currentY + conveyY [index];
                L = to1D(currentX, currentY, n);
                conveyChar = (board[L]);
            }
        }
        
        return L;
    }
    static ArrayList<Integer> v = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        boolean yes = true;
        int m = readInt();
        int n = readInt();
        String convey123 = "DLRU";
        ArrayList<Integer>[] a = new ArrayList[n * m];
        for (int i = 0; i < n * m; i++) {
            a[i] = new ArrayList();
        }
        char[] board = new char[n * m];

        for (int y = 0; y < m; y++) {
            String line = nextLine();
            for (int x = 0; x < n; x++) {

                board[y * n + x] = line.charAt(x);

            }

        }
        int LofS = 0;
        boolean[] v1 = new boolean[n * m];

        ArrayList<Integer> points = new ArrayList();
        for (int L = 0; L < m * n; L++) {
            int coordXs = coordX(L, n);
            int coordYs = coordY(L, n);
            if (board[L] == '.') {
                points.add(L);
                if (withinBounds(coordXs + 1, coordYs, n, m)) {
                    int l1 = to1D(coordXs + 1, coordYs, n);
                    int l2 = coordYs;

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
                if (withinBounds(coordXs, coordYs + 1, n, m)) {
                    int l1 = to1D(coordXs, coordYs + 1, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
                if (withinBounds(coordXs - 1, coordYs, n, m)) {
                    int l1 = to1D(coordXs - 1, coordYs, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
                if (withinBounds(coordXs, coordYs - 1, n, m)) {
                    int l1 = to1D(coordXs, coordYs - 1, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
            }
            if (board[L] == 'S') {
                LofS = L;

                if (withinBounds(coordXs + 1, coordYs, n, m)) {
                    int l1 = to1D(coordXs + 1, coordYs, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (board[l1] == 'C') {
                        yes = false;
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
                if (withinBounds(coordXs, coordYs + 1, n, m)) {
                    int l1 = to1D(coordXs, coordYs + 1, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (board[l1] == 'C') {
                        yes = false;
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
                if (withinBounds(coordXs - 1, coordYs, n, m)) {
                    int l1 = to1D(coordXs - 1, coordYs, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (board[l1] == 'C') {
                        yes = false;
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
                if (withinBounds(coordXs, coordYs - 1, n, m)) {
                    int l1 = to1D(coordXs, coordYs - 1, n);

                    if (board[l1] == '.' || board[l1] == 'S') {
                        a[L].add(l1);
                    }
                    if (board[l1] == 'C') {
                        yes = false;
                    }
                    if (convey.contains(board[l1] + "")) {
                        if (resolveConveyor(board, l1, n, m) != -1) {
                            a[L].add(resolveConveyor(board, l1, n, m));
                        }
                    }
                }
            }
            if (board[L] == 'C') {
                int increment = 1;

                while (coordXs + increment < n) {

                    int l1 = to1D(coordXs + increment, coordYs, n);
                    if (board[l1] == 'W') {
                        break;
                    } else {
                        v1[l1] = true;
                    }
                    increment++;

                }
                increment = 1;
                while (coordYs + increment < m) {

                    int l1 = to1D(coordXs, coordYs + increment, n);
                    if (board[l1] == 'W') {
                        break;
                    } else {
                        v1[l1] = true;
                    }
                    increment++;
                }
                increment = 1;
                while (coordXs - increment >= 0) {

                    int l1 = to1D(coordXs - increment, coordYs, n);
                    if (board[l1] == 'W') {
                        break;
                    } else {
                        v1[l1] = true;
                    }
                    increment++;
                }
                increment = 1;
                while (coordYs - increment >= 0) {

                    int l1 = to1D(coordXs, coordYs - increment, n);
                    if (board[l1] == 'W') {
                        break;
                    } else {
                        v1[l1] = true;
                    }
                    increment++;
                }

            }

        }

        if (yes) {
            int arr[] = new int[n * m];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = -1;
            }
            arr[LofS] = 0;

            isBFST(a, arr, LofS, n * m, v1);
            for (int x : points) {

                System.out.println(arr[x]);

            }
        } else {
            for (int x : points) {

                System.out.println(-1);

            }
        }
        exit();
    }

}