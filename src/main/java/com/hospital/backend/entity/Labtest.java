package com.hospital.backend.entity;

public class Labtest {

    private int testId;
    private String testName;
    private double testFee;

    public Labtest() {}

    public Labtest(int testId, String testName, double testFee) {
        this.testId = testId;
        this.testName = testName;
        this.testFee = testFee;
    }

    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }

    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }

    public double getTestFee() { return testFee; }
    public void setTestFee(double testFee) { this.testFee = testFee; }

    @Override
    public String toString() {
        return "Labtest{" +
                "testId=" + testId +
                ", testName='" + testName + '\'' +
                ", testFee=" + testFee +
                '}';
    }
}
