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

    public int getQPTS() {
        return Q_PTS;
    }

    public int getQUADS() {
        return QUADS;
    }

    public double getDIST() {
        return DIST;
    }

    public int getNPTS() {
        return N_PTS;
    }

    public int getKPTS() {
        return K_PTS;
    }

    public int getAPTS() {
        return A_PTS;
    }

    public int getBPTS() {
        return B_PTS;
    }

    // Private constructor, only accessible via the builder
    private Parameters(Builder builder) {
        this.LENGTH1 = builder.LENGTH1;
        this.RADIUS1 = builder.RADIUS1;
        this.EPSILON = builder.EPSILON;
        this.AREA1 = builder.AREA1;
        this.Q_PTS = builder.Q_PTS;
        this.QUADS = builder.QUADS;
        this.DIST = builder.DIST;
        this.N_PTS = builder.N_PTS;
        this.K_PTS = builder.K_PTS;
        this.A_PTS = builder.A_PTS;
        this.B_PTS = builder.B_PTS;
        this.C_PTS = builder.C_PTS;
        this.D_PTS = builder.D_PTS;
        this.E_PTS = builder.E_PTS;
        this.F_PTS = builder.F_PTS;
        this.LENGTH2 = builder.LENGTH2;
        this.RADIUS2 = builder.RADIUS2;
        this.AREA2 = builder.AREA2;
    }

    public static class Builder {
        private double LENGTH1 = 0.0; // Default value
        private double RADIUS1 = 0.0; // Default value
        private double EPSILON = 0.0; // Default value
        private double AREA1 = 0.0; // Default value
        private int Q_PTS = 0; // Default value
        private int QUADS = 0; // Default value
        private double DIST = 0.0; // Default value
        private int N_PTS = 0; // Default value
        private int K_PTS = 0; // Default value
        private int A_PTS = 0; // Default value
        private int B_PTS = 0; // Default value
        private int C_PTS = 0; // Default value
        private int D_PTS = 0; // Default value
        private int E_PTS = 0; // Default value
        private int F_PTS = 0; // Default value
        private double LENGTH2 = 0.0; // Default value
        private double RADIUS2 = 0.0; // Default value
        private double AREA2 = 0.0; // Default value

        // Method to set LENGTH1 and return the builder for method chaining
        public Builder setLENGTH1(double LENGTH1) {
            this.LENGTH1 = LENGTH1;
            return this;
        }

        // Similar methods for other fields (setRADIUS1, setEPSILON, etc.)

        public Builder setRADIUS1(double RADIUS1) {
            this.RADIUS1 = RADIUS1;
            return this;
        }

        public Builder setEPSILON(double EPSILON) {
            this.EPSILON = EPSILON;
            return this;
        }

        // ... (other setter methods for remaining fields)

        public Parameters build() {
            return new Parameters(this); // Return the fully constructed Parameters object
        }
        // Continue with other getters...
    }

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