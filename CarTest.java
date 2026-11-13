public class CarTest {

    private static void reportResult(String testName, boolean success) {
        if (success) {
            System.out.println("SUCCESS: " + testName);
        } else {
            System.out.println("FAILURE: " + testName);
        }
    }

    private static void testGasIncreasesSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double oldSpeed = saab.getCurrentSpeed();
        saab.gas(0.5);
        double newSpeed = saab.getCurrentSpeed();

        reportResult("Gas increases speed", newSpeed > oldSpeed);
    }

    private static void testBrakeDecreasesSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(0.5);

        double oldSpeed = saab.getCurrentSpeed();
        saab.brake(0.5);
        double newSpeed = saab.getCurrentSpeed();

        reportResult("Brake decreases speed", newSpeed < oldSpeed);
    }

    private static void testGasAcceptsZeroAndOne() {
    Saab95 saab = new Saab95();
    saab.startEngine();

    double speedBeforeZero = saab.getCurrentSpeed();
    saab.gas(0);
    double speedAfterZero = saab.getCurrentSpeed();

    boolean zeroAccepted = speedAfterZero == speedBeforeZero;

    double speedBeforeOne = saab.getCurrentSpeed();
    saab.gas(1);
    double speedAfterOne = saab.getCurrentSpeed();

    boolean oneAccepted = speedAfterOne > speedBeforeOne;

    boolean zeroAndOneAccepted = zeroAccepted && oneAccepted;

    reportResult("Gas accepts 0 and 1", zeroAndOneAccepted);
}

    private static void testBrakeAcceptsZeroAndOne() {
    Saab95 saab = new Saab95();
    saab.startEngine();
    saab.gas(0.5);

    double speedBeforeZero = saab.getCurrentSpeed();
    saab.brake(0);
    double speedAfterZero = saab.getCurrentSpeed();

    boolean zeroAccepted = speedAfterZero == speedBeforeZero;

    double speedBeforeOne = saab.getCurrentSpeed();
    saab.brake(1);
    double speedAfterOne = saab.getCurrentSpeed();

    boolean oneAccepted = speedAfterOne < speedBeforeOne;

    boolean zeroAndOneAccepted = zeroAccepted && oneAccepted;

    reportResult("Brake accepts 0 and 1", zeroAndOneAccepted);
}

    private static void testGasIgnoresInvalidAmounts() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        double speedBefore = saab.getCurrentSpeed();

        saab.gas(-0.1);
        double speedAfterNegative = saab.getCurrentSpeed();

        saab.gas(1.1);
        double speedAfterAboveOne = saab.getCurrentSpeed();

        boolean invalidAmountsIgnored = 
            speedAfterNegative == speedBefore 
            && speedAfterAboveOne == speedBefore;

        reportResult("Gas ignores -0.1 and 1.1", invalidAmountsIgnored);
    }

    private static void testBrakeIgnoresInvalidAmounts() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(0.5);

        double speedBefore = saab.getCurrentSpeed();

        saab.brake(-0.1);
        double speedAfterNegative = saab.getCurrentSpeed();

        saab.brake(1.1);
        double speedAfterAboveOne = saab.getCurrentSpeed();

        boolean invalidAmountsIgnored =
            speedAfterNegative == speedBefore
            && speedAfterAboveOne == speedBefore;

        reportResult("Brake ignores -0.1 and 1.1", invalidAmountsIgnored);
    }


    private static void testSpeedStaysBetweenZeroAndEnginePower() {
        Saab95 saab = new Saab95();
        saab.startEngine();

        for (int i = 0; i < 100; i++) {
            saab.gas(1);
        }

        boolean speedIsNotAboveEnginePower = 
            saab.getCurrentSpeed() <= saab.getEnginePower();
        
        reportResult("Speed does not exceed engine power", speedIsNotAboveEnginePower);

        for (int i = 0; i < 100; i++) {
            saab.brake(1);
        }

        boolean speedIsNotNegative = 
            saab.getCurrentSpeed() >= 0;
        
        reportResult("Speed is never negative", speedIsNotNegative);
    }

    private static void testTurbo() {
        Saab95 slowSaab = new Saab95();
        Saab95 fastSaab = new Saab95();

        slowSaab.setTurboOff();
        fastSaab.setTurboOn();

        slowSaab.startEngine();
        fastSaab.startEngine();

        slowSaab.gas(0.5);
        fastSaab.gas(0.5);

        reportResult(
            "Turbo gives more speed",
            fastSaab.getCurrentSpeed() > slowSaab.getCurrentSpeed()
        );
    }


    public static void main(String[] args) {
        testGasIncreasesSpeed();
        testBrakeDecreasesSpeed();
        
        testGasAcceptsZeroAndOne();
        testBrakeAcceptsZeroAndOne();

        testGasIgnoresInvalidAmounts();
        testBrakeIgnoresInvalidAmounts();

        
        testSpeedStaysBetweenZeroAndEnginePower();

        testTurbo();
    }
}