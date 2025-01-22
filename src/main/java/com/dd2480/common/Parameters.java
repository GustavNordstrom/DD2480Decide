package com.dd2480.common;

public class Parameters {

    // Fields (final for immutability)
    private final double LENGTH1;
    private final double RADIUS1;
    private final double EPSILON;
    private final double AREA1;
    private final int Q_PTS;
    private final int QUADS;
    private final double DIST;
    private final int N_PTS;
    private final int K_PTS;
    private final int A_PTS;
    private final int B_PTS;
    private final int C_PTS;
    private final int D_PTS;
    private final int E_PTS;
    private final int F_PTS;
    private final double LENGTH2;
    private final double RADIUS2;
    private final double AREA2;

    // Constructor
    public Parameters(double LENGTH1, double RADIUS1, double EPSILON, double AREA1, int Q_PTS, int QUADS,
                      double DIST, int N_PTS, int K_PTS, int A_PTS, int B_PTS, int C_PTS,
                      int D_PTS, int E_PTS, int F_PTS, double LENGTH2, double RADIUS2, double AREA2) {
        this.LENGTH1 = LENGTH1;
        this.RADIUS1 = RADIUS1;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.Q_PTS = Q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.N_PTS = N_PTS;
        this.K_PTS = K_PTS;
        this.A_PTS = A_PTS;
        this.B_PTS = B_PTS;
        this.C_PTS = C_PTS;
        this.D_PTS = D_PTS;
        this.E_PTS = E_PTS;
        this.F_PTS = F_PTS;
        this.LENGTH2 = LENGTH2;
        this.RADIUS2 = RADIUS2;
        this.AREA2 = AREA2;
    }

    // Getters (No setters for immutability)
    public double getLENGTH1() {
        return LENGTH1;
    }

    public double getRADIUS1() {
        return RADIUS1;
    }

    public double getEPSILON() {
        return EPSILON;
    }

    public double getAREA1() {
        return AREA1;
    }

    // Continue with other getters...

    // toString method (optional)
    @Override
    public String toString() {
        return "Parameters{" +
                "LENGTH1=" + LENGTH1 +
                ", RADIUS1=" + RADIUS1 +
                ", EPSILON=" + EPSILON +
                ", AREA1=" + AREA1 +
                ", Q_PTS=" + Q_PTS +
                ", QUADS=" + QUADS +
                ", DIST=" + DIST +
                ", N_PTS=" + N_PTS +
                ", K_PTS=" + K_PTS +
                ", A_PTS=" + A_PTS +
                ", B_PTS=" + B_PTS +
                ", C_PTS=" + C_PTS +
                ", D_PTS=" + D_PTS +
                ", E_PTS=" + E_PTS +
                ", F_PTS=" + F_PTS +
                ", LENGTH2=" + LENGTH2 +
                ", RADIUS2=" + RADIUS2 +
                ", AREA2=" + AREA2 +
                '}';
    }
}