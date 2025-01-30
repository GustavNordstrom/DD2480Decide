package com.dd2480.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Parameters.Builder.class)
public class Parameters {

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
    private final int G_PTS;
    private final double LENGTH2;
    private final double RADIUS2;
    private final double AREA2;

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
        this.G_PTS = builder.G_PTS;
        this.LENGTH2 = builder.LENGTH2;
        this.RADIUS2 = builder.RADIUS2;
        this.AREA2 = builder.AREA2;
    }

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

    public int getCPTS() {
        return C_PTS;
    }

    public int getDPTS() {
        return D_PTS;
    }

    public int getEPTS() {
        return E_PTS;
    }

    public int getFPTS() {
        return F_PTS;
    }

    public int getGPTS() {
        return G_PTS;
    }

    public double getLENGTH2() {
        return LENGTH2;
    }

    public double getRADIUS2() {
        return RADIUS2;
    }

    public double getAREA2() {
        return AREA2;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private double LENGTH1;
        private double RADIUS1;
        private double EPSILON;
        private double AREA1;
        private int Q_PTS;
        private int QUADS;
        private double DIST;
        private int N_PTS;
        private int K_PTS;
        private int A_PTS;
        private int B_PTS;
        private int C_PTS;
        private int D_PTS;
        private int E_PTS;
        private int F_PTS;
        private int G_PTS;
        private double LENGTH2;
        private double RADIUS2;
        private double AREA2;

        @JsonProperty("LENGTH1")
        public Builder setLENGTH1(double LENGTH1) {
            this.LENGTH1 = LENGTH1;
            return this;
        }

        @JsonProperty("RADIUS1")
        public Builder setRADIUS1(double RADIUS1) {
            this.RADIUS1 = RADIUS1;
            return this;
        }

        @JsonProperty("EPSILON")
        public Builder setEPSILON(double EPSILON) {
            this.EPSILON = EPSILON;
            return this;
        }

        @JsonProperty("AREA1")
        public Builder setAREA1(double AREA1) {
            this.AREA1 = AREA1;
            return this;
        }

        @JsonProperty("Q_PTS")
        public Builder setQPTS(int Q_PTS) {
            this.Q_PTS = Q_PTS;
            return this;
        }

        @JsonProperty("QUADS")
        public Builder setQUADS(int QUADS) {
            this.QUADS = QUADS;
            return this;
        }

        @JsonProperty("DIST")
        public Builder setDIST(double DIST) {
            this.DIST = DIST;
            return this;
        }

        @JsonProperty("N_PTS")
        public Builder setNPTS(int N_PTS) {
            this.N_PTS = N_PTS;
            return this;
        }

        @JsonProperty("K_PTS")
        public Builder setKPTS(int K_PTS) {
            this.K_PTS = K_PTS;
            return this;
        }

        @JsonProperty("A_PTS")
        public Builder setAPTS(int A_PTS) {
            this.A_PTS = A_PTS;
            return this;
        }

        @JsonProperty("B_PTS")
        public Builder setBPTS(int B_PTS) {
            this.B_PTS = B_PTS;
            return this;
        }

        @JsonProperty("C_PTS")
        public Builder setCPTS(int C_PTS) {
            this.C_PTS = C_PTS;
            return this;
        }

        @JsonProperty("D_PTS")
        public Builder setDPTS(int D_PTS) {
            this.D_PTS = D_PTS;
            return this;
        }

        @JsonProperty("E_PTS")
        public Builder setEPTS(int E_PTS) {
            this.E_PTS = E_PTS;
            return this;
        }

        @JsonProperty("F_PTS")
        public Builder setFPTS(int F_PTS) {
            this.F_PTS = F_PTS;
            return this;
        }

        @JsonProperty("G_PTS")
        public Builder setGPTS(int G_PTS) {
            this.G_PTS = G_PTS;
            return this;
        }

        @JsonProperty("LENGTH2")
        public Builder setLENGTH2(double LENGTH2) {
            this.LENGTH2 = LENGTH2;
            return this;
        }

        @JsonProperty("RADIUS2")
        public Builder setRADIUS2(double RADIUS2) {
            this.RADIUS2 = RADIUS2;
            return this;
        }

        @JsonProperty("AREA2")
        public Builder setAREA2(double AREA2) {
            this.AREA2 = AREA2;
            return this;
        }

        public Parameters build() {
            return new Parameters(this);
        }
    }
}